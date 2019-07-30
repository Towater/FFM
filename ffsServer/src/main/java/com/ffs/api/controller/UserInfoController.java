package com.ffs.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.ffs.api.CommonResult;
import com.ffs.service.impl.UserInfoServiceImpl;
import com.ffs.service.jwt.TokenManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@RestController
@RequestMapping
@ResponseBody
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    /**
     * 通过Id查询个人用户信息
     * @param request
     * @return
     */
    @GetMapping("/selectUserByid")
    public CommonResult SelectUserById(HttpServletRequest request) {
        CommonResult re = new CommonResult();
        Long id = Long.parseLong(request.getAttribute("id").toString());
        re.success(userInfoService.SelectUserById((long)id));
        return re;
    }

    /**
     * 通过管理员Id查询家庭所有成员
     * @param request
     * @return
     */
    @GetMapping("/selectFamiliesById")
    public  CommonResult SelectFamiliesById(HttpServletRequest request){
        CommonResult re = new CommonResult();
        Long id = Long.parseLong(request.getAttribute("id").toString());
        re.success(userInfoService.SelectFamiliesById(id));
        return re;
    }
    /**
     * 控制登录请求
     * @param account
     * @param password
     * @return
     */
    @GetMapping("/login")
    public CommonResult Login(String account,String password){
        CommonResult re = new CommonResult();
        //生成一个token
        String token = userInfoService.Login(account,password);
        if(token.equals("-1")) {
            re.badRequest("用户名或密码错误！请从新输入！");
            return re;
        }else {
            re.success(token);
            return re;
        }
    }

    /**
     * 控制注册业务实现
     * @param account
     * @param password
     * @param name
     * @param age
     * @param familyCall
     * @param wages
     * @return
     */
    @GetMapping("/register")
    public CommonResult Registe(String account, String password, String name, Integer age, String familyCall, BigDecimal wages){
        CommonResult re = new CommonResult();
        if(account==null||password==null||name==null||familyCall==null||wages==null)
        {
            re.badRequest("注册失败");
            return re;
        }else if(userInfoService.Register(account,password,name,age,familyCall,wages)){
            re.success("注册成功");
            return re;
        }
        else {
            re.badRequest("注册失败");
            return re;
        }
    }

    /**
     * 修改用户信息
     * @param id
     * @param account
     * @param name
     * @param age
     * @param familyCall
     * @param wages
     * @return
     */
    @GetMapping("/updateUser")
    public CommonResult UpdateUserInfo(Long id, String account, String name, Integer age, String familyCall, BigDecimal wages){
        CommonResult re = new CommonResult();
        if(userInfoService.UpdateUserInfo(id,account,name,age,familyCall,wages)){
            re.success("修改成功！");
            return re;
        }else {
            re.badRequest("修改失败！");
            return re;
        }
    }

    /**
     * 修改用户密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirm
     * @return
     */
    @GetMapping("/updatePassword")
    public CommonResult UpdatePassword(HttpServletRequest request,String oldPassword,String newPassword,String confirm){
        CommonResult re = new CommonResult();
        Long id = Long.parseLong(request.getAttribute("id").toString());
        if(!newPassword.equals(confirm)){
            re.badRequest("两次输入的密码不一致，修改失败！");
            return re;
        }
        if(userInfoService.UpdatePassword(id,oldPassword,newPassword)){
            re.success("修改成功！");
            return re;
        }else {
            re.badRequest("修改失败！");
            return re;
        }
    }

    /**
     * 通过Id删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteUserById")
    public CommonResult DeleteUserById(Long id){
        CommonResult re = new CommonResult();
        return userInfoService.DeleteUserById(id);
    }

    /**
     * 通过ID 批量删除用户 必须是同一个管理员创的个人用户
     * @param request 管理员的id
     * @param idList 要删除的个人用户id
     * @return
     */
    @GetMapping("/deleteUsersByIdList")
    public CommonResult DeleteUsersByIdList(HttpServletRequest request,String idList){
        CommonResult re = new CommonResult();
        Long id = Long.parseLong(request.getAttribute("id").toString());
        List<Long> idlist = new ArrayList<Long>();
        for(int i =0;i<idList.length();i++){
            idlist.add((long)idList.charAt(i)-48);
        }
        if(userInfoService.DeleteUsersByIdList(id,idlist)){
            re.success("批量删除成功！");
            return re;
        }
        else {
            re.badRequest("批量删除失败");
            return re;
        }
    }

    /**
     * 新增用户
     * @param request 创建者Id，即管理员Id
     * @param account
     * @param password
     * @param name
     * @param age
     * @param familyCall
     * @param wages
     * @return
     */
    @GetMapping("/addUser")
    public CommonResult AddUser(HttpServletRequest request,String account, String password, String name,Integer age, String familyCall, BigDecimal wages){
        CommonResult re = new CommonResult();
        Long id = Long.parseLong(request.getAttribute("id").toString());
        if(userInfoService.AddUser(id,account,password,name,age,familyCall,wages)){
            re.success("创建成功！");
            return re;
        }else {
            re.badRequest("创建失败！");
            return re;
        }
    }
}
