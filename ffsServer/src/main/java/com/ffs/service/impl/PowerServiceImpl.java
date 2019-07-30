package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ffs.entity.Power;
import com.ffs.entity.RoleInfo;
import com.ffs.entity.RoleMappingPower;
import com.ffs.entity.UserInfo;
import com.ffs.mapper.PowerMapper;
import com.ffs.mapper.RoleInfoMapper;
import com.ffs.mapper.RoleMappingPowerMapper;
import com.ffs.mapper.UserInfoMapper;
import com.ffs.service.IPowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.jsqlparser.statement.select.SetOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-08
 */
@Service
public class PowerServiceImpl extends ServiceImpl<PowerMapper, Power> implements IPowerService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Value("${userRedis}")
    private String USERREDISTIME;
    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private RoleMappingPowerMapper roleMappingPowerMapper;
    @Autowired
    private RoleInfoMapper roleInfoMapper;

    /**
     * 输入用户id，Url,判断是否有权限
     */
    public boolean authorizationDecide(Long userId,String url){
        //获取用户的角色和响应的权利
        Set userRoleIds = setMembers(userId.toString());
        Set powerRoleIds = setMembers(url);

        //装载角色和权利
        List<String> userRole  =new ArrayList<String>();
        List<String> powerRole  =new ArrayList<String>();
        if(userRoleIds.size()==0){
            //通过userId在redis中没拿到roleId的值，去数据库
            UserInfo user = userInfoMapper.selectById(userId);
            Long roleId = user.getRoleId();
            if(roleId==null){
                //数据库也没有，这个用户没有角色
                return false;
            }
            userRole.add(roleId.toString());
            //把user对应的角色缓存三天
            redisCreatUser(userId);
        }
        if(userRoleIds.size()>0){
            //通过userId从redis拿到了roleId的值
            Iterator<String> it=userRoleIds.iterator();
            while (it.hasNext()) {
                String i = it.next();
                userRole.add(i.toString());
            }
        }
        if(powerRoleIds.size()==0){
            //redis没拿到，去数据库
            Power power = powerMapper.selectOne(new QueryWrapper<Power>().eq("url",url));
            //url不存在
            if(power==null){
                return false;
            }
            List<RoleMappingPower> roleMappingPowers = roleMappingPowerMapper.selectList(new QueryWrapper<RoleMappingPower>().eq("powerId",power.getId()));
            //该url没有关联角色
            if(roleMappingPowers==null){
                return false;
            }
            for (RoleMappingPower i:roleMappingPowers) {
                String roleId=i.getRoleId().toString();
                powerRole.add(roleId);
            }
            //该url放入缓存
            redisCreatPower(power);
        }
        if(powerRoleIds.size()>0){
            Iterator<String> it=powerRoleIds.iterator();
            while (it.hasNext()) {
                String i = it.next();
                powerRole.add(i.toString());
            }
        }
        powerRole.retainAll(userRole);
        if(powerRole.size()>0){
            return true;
        }
        else{
            return false;
        }
}

    /**
     * 获取Set类型的数据
     * @param key
     * @return
     */
    public Set<String> setMembers(String key){
        SetOperations<String,String> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 把userId对应的角色缓存三天
     * redis格式 user.id->role.id
     * @param userId
     */
    public boolean redisCreatUser(Long userId){
        UserInfo user=userInfoMapper.selectById(userId);
        Long roleId = user.getRoleId();
        if(user==null||roleId==null){
            return false;
        }
        else {
            redisTemplate.opsForSet().add(user.getId().toString(),roleId.toString());
            redisTemplate.expire(user.getId().toString(),Long.parseLong(USERREDISTIME), TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 输入权限对象，创建该权限的在redis中对角色的映射
     * redis格式 power.url->role.id 减少匹配时间复杂度
     *
     */
    public boolean redisCreatPower(Power power){
        List<RoleInfo> roles = selectRoleListByPowerId(power.getId());
        if(roles==null){
            return false;
        }
        else {
            for (RoleInfo i:roles) {
                redisTemplate.opsForSet().add(power.getUrl(),i.getId().toString());
            }
        }
        return true;
    }

    /**
     * 通过PowerId获取角色
     * @param powerId
     * @return
     */
    public List<RoleInfo> selectRoleListByPowerId(Long powerId){
        List<RoleMappingPower> roleMappingPowers=roleMappingPowerMapper.selectList(new QueryWrapper<RoleMappingPower>().eq("powerId",powerId));
        List<RoleInfo> roles=new ArrayList<RoleInfo>();
        for (RoleMappingPower i:roleMappingPowers) {
            RoleInfo role = roleInfoMapper.selectById(i.getRoleId());
            roles.add(role);
        }
        return roles;
    }
}
