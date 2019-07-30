package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ffs.api.CommonResult;
import com.ffs.entity.FamilyInfo;
import com.ffs.entity.RoleInfo;
import com.ffs.entity.UserInfo;
import com.ffs.mapper.FamilyInfoMapper;
import com.ffs.mapper.RoleInfoMapper;
import com.ffs.mapper.UserInfoMapper;
import com.ffs.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffs.service.jwt.TokenManage;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@Service
@Component
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    TokenManage tokenManage;
    @Autowired
    RoleInfoMapper roleInfoMapper;
    @Autowired
    FamilyInfoMapper familyInfoMapper;

    @Value("${jwt.secret}")
    private  String SECRET;

    /**
     * 获取个人用户信息
     * @param id
     * @return
     */
    public UserInfo SelectUserById(Long id){
        return userInfoMapper.selectById(id);
    }

    public List<UserInfo> SelectFamiliesById(Long id){//管理员的ID
        UserInfo manager = userInfoMapper.selectById(id);
        if(manager==null){
            return null;
        }
        return userInfoMapper.selectList(new QueryWrapper<UserInfo>().eq("familyId",manager.getFamilyId()));
    }

    /**
     * 用户登录
     * 成功返回token，失败返回-1
     * @param account
     * @param password
     * @return
     */
    public String Login(String account,String password){
        String ps = TokenManage.sha256_HMAC(password,SECRET);
        //获取这个用户
        UserInfo user = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("account",account).eq("password",ps));
        if(user == null){
            return "-1";
        }
        //获取用户roleId
        String roleId = (user.getRoleId()).toString();
        //获取用户角色类
        RoleInfo role = roleInfoMapper.selectOne(new QueryWrapper<RoleInfo>().eq("id",roleId));
        //获取用户角色
        String type = role.getRoleName();

        if(user!=null){
            return tokenManage.creactToken(user.getId(),type);
        }else{
            return "-1";
        }
    }

    /**
     * 实现 注册 业务
     * @param account
     * @param password
     * @param name
     * @param age
     * @param familyCall
     * @param wages
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public boolean Register(String account, String password, String name, Integer age, String familyCall, BigDecimal wages){
        //生成用户
        UserInfo user = new UserInfo();
        user.setRoleId((long)2);//默认为管理员
        user.setAccount(account);
        user.setPassword(TokenManage.sha256_HMAC(password,SECRET));
        user.setName(name);
        user.setAge(age);
        user.setFamilyCall(familyCall);
        user.setWages(wages);
        if(userInfoMapper.insert(user)<=0){
            return false;
        }
        //创建家庭
        FamilyInfo family = new FamilyInfo();
        family.setFamilyMembers(1);
        family.setManagerId(user.getId());
        if(familyInfoMapper.insert(family)<=0) {
            return false;
        }

        //将新管理员匹配到家庭中
        FamilyInfo newfamily = familyInfoMapper.selectById(family.getId());
        UserInfo newUser = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("id",newfamily.getManagerId()));
        newUser.setFamilyId(newfamily.getId());
        if(userInfoMapper.updateById(newUser)<=0) {
            return false;
        }
        return true;
    }

    /**
     * 更新用户信息
     * @param id
     * @param account
     * @param name
     * @param age
     * @param familyCall
     * @param wages
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public boolean UpdateUserInfo(Long id,String account, String name, Integer age, String familyCall, BigDecimal wages){
        UserInfo user = new UserInfo();
        user = userInfoMapper.selectById(id);
        //String psw = TokenManage.sha256_HMAC(password,SECRET);
        //如果user不存在
        if(user==null||user.equals(null))
        {
            return  false;
        }else{
            user.setAccount(account);
            user.setName(name);
            user.setAge(age);
            user.setFamilyCall(familyCall);
            user.setWages(wages);
            userInfoMapper.updateById(user);
            return true;
        }
    }

    /**
     * 实现修改密码功能
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public boolean UpdatePassword(Long id,String oldPassword,String newPassword){
        //旧密码
        String oPwd = TokenManage.sha256_HMAC(oldPassword,SECRET);
        //新密码
        String nPwd = TokenManage.sha256_HMAC(newPassword,SECRET);
        UserInfo user = userInfoMapper.selectById(id);
        if (user==null){
            return false;
        }
        if(user.getPassword().equals(oPwd)){
            user.setPassword(nPwd);
            userInfoMapper.updateById(user);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 通过Id删除用户
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public CommonResult DeleteUserById(Long id) {
        //state = 0--失败
        //state = 1--成功
        //state = 2--失败 用户为管理员，且存在家庭成员
        //state = 3--失败 id对应的用户不存在
        CommonResult re = new CommonResult();
        int state;
        //如果是管理员，则查看家庭是否有其他家庭成员，没有才能删除
        UserInfo user = userInfoMapper.selectById(id);
        FamilyInfo family = familyInfoMapper.selectById(user.getFamilyId());
        if(user == null){
            re.badRequest("删除对应的用户不存在！");
            return re;
        }
        if(user.getRoleId()==2){
            if(family.getFamilyMembers()>1){
                re.badRequest("必须删除其他家庭个人用户才可以删除管理员账号！");
                return re;
            }else{
                //删除成员
                userInfoMapper.deleteById(id);
                //删除家庭
                familyInfoMapper.deleteById(family.getId());
                re.success("删除管理员成功！");
                return re;
            }
        }else{
            //删除成员
            userInfoMapper.deleteById(id);
            //家庭成员数-1
            family.setFamilyMembers(family.getFamilyMembers()-1);
            familyInfoMapper.updateById(family);
            re.success("删除个人用户成功！");
            return re;
        }
    }

    /**
     * 通过ID 批量删除用户 必须是用一个管理员创的个人用户
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public boolean DeleteUsersByIdList(Long id,List<Long> idList){
        if(userInfoMapper.deleteBatchIds(idList)<=0){
            return false;
        }
        UserInfo user = userInfoMapper.selectById(id);
        FamilyInfo family = familyInfoMapper.selectById(user.getFamilyId());
        family.setFamilyMembers(family.getFamilyMembers()-idList.size());
        return true;
    }

    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public boolean AddUser(Long id,String account, String password, String name, Integer age, String familyCall, BigDecimal wages){
        UserInfo manager = userInfoMapper.selectById(id);
        UserInfo user = new UserInfo();
        String pwd = TokenManage.sha256_HMAC(password,SECRET);
        if(manager==null){
            return false;
        }
        FamilyInfo family = familyInfoMapper.selectById(manager.getFamilyId());
        user.setAccount(account);
        user.setPassword(pwd);
        user.setName(name);
        user.setAge(age);
        user.setFamilyCall(familyCall);
        user.setWages(wages);
        user.setRoleId((long)1);
        user.setFamilyId(family.getId());
        if(userInfoMapper.insert(user)<=0){
            return false;
        }
        family.setFamilyMembers(family.getFamilyMembers()+1);
        familyInfoMapper.updateById(family);
        return true;
    }

}
