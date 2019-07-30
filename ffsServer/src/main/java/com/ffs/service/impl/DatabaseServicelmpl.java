package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ffs.entity.FamilyInfo;
import com.ffs.entity.IncomeInfo;
import com.ffs.entity.PayInfo;
import com.ffs.entity.UserInfo;
import com.ffs.mapper.*;
import com.ffs.service.jwt.TokenManage;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DatabaseServicelmpl {


    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    IncomeInfoMapper incomeInfoMapper;
    @Autowired
    PayInfoMapper payInfoMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    FamilyInfoMapper familyInfoMapper;

    /**
     * 数据备份
     * @return
     * @throws IOException
     */
    public boolean AddDBbackups() throws IOException {

        String backName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+".sql";
        String pathSql = "D://大三学习内容//大三暑假实训//ffs//ffsapp//data//"+backName;//window系统上的目录
        //String pathSql ="/home/root/mysqlData/"+backName;//linux系统下的目录
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        //mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        //sb.append(" -h"+"47.106.179.61");
        sb.append(" "+"dbsms");
        sb.append(" -u"+"root");
        sb.append(" -p"+"root > ");
        //sb.append(" "+"D:/大三学习内容/大三暑假实训/ffs/ffsapp/data");

        sb.append(pathSql);
        System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份："+"ffs");
        //Process process = runtime.exec("cmd /d"+sb.toString());
        Process process = runtime.exec("cmd /c"+sb.toString());

        System.out.println("备份成功!");
        return true;
    }

    /**
     * 获取与管理员相关的所有信息
     * 用户数据备份
     * @param id
     * @return
     */
    public String GetAllDataById(Long id) throws IOException {
        //生成保存文件
        String backName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+".txt";
        String path = "D://大三学习内容//大三暑假实训//ffs//data//"+backName;//window系统上的目录
        //String pathSql ="/home/root/mysqlData/"+backName;//linux系统下的目录
        File file = new File(path);
        //创建备份sql文件
        if (!file.exists()){
            file.createNewFile();
        }

        //备份数据========================================
        String allData = null;
        //通过家人获取用户信息
        UserInfo manager = userInfoMapper.selectById(id);
        Long familyId = manager.getFamilyId();
        if(familyId == null){
            return "此管理员不存在!";
        }else if(manager.getRoleId()==2){
            List<UserInfo> allUser = userInfoMapper.selectList(new QueryWrapper<UserInfo>().eq("familyId",familyId));

            List<List<IncomeInfo>> allIncome = new ArrayList<List<IncomeInfo>>();
            List<List<PayInfo>> allPay = new ArrayList<List<PayInfo>>();

            for(int i=0;i<allUser.size();i++){
                Long userId = allUser.get(i).getId();
                //收入
                List<IncomeInfo> userIncome = incomeInfoMapper.selectList(new QueryWrapper<IncomeInfo>().eq("userid",userId));
                allIncome.add(userIncome);
                //支出
                List<PayInfo> userPay = payInfoMapper.selectList(new QueryWrapper<PayInfo>().eq("userid",userId));
                allPay.add(userPay);
            }
            allData = allUser.toString()+allIncome.toString()+allPay.toString();
            PrintWriter pfp= new PrintWriter(file);
            pfp.print(TokenManage.decodeShiLiu(allData));
            //pfp.print(allData);
            pfp.close();
            return (path+backName);
        }else {
            return ("此用户不是管理员!");
        }
    }

    /**
     * 恢复数据
     * @param data
     * @return
     */
    @Transactional
    public String PullAllDataByFile(String data){
        //用于获取管理员
        UserInfo manger = new UserInfo();
        //1. 出去所有[]
        //String regex = "(\\[)(\\])";
        data = data.replace("[","").replace("]","");
        //2. split拆分没一个对象
        String[] userArray = data.split("UserInfo");
        String[] incomeArray = userArray[userArray.length-1].split("IncomeInfo");
        String[] payArray = incomeArray[incomeArray.length-1].split("PayInfo");
        //=======================整理数据==============================
        String[] userData = new String[userArray.length-1];
        String[] incomeData = new String[incomeArray.length-1];
        String[] payData = new String[payArray.length-1];
        for(int i = 1;i<userArray.length-1;i++){
            userData[i-1] = userArray[i];
        }
        userData[userArray.length-2] = incomeArray[0];

        for(int i = 1;i<incomeArray.length-1;i++){
            incomeData[i-1] = incomeArray[i];
        }
        incomeData[incomeArray.length-2] = payArray[0];

        for(int i = 1;i<payArray.length;i++){
            payData[i-1] = payArray[i];
        }

        //=======================恢复User数据:account,password,name,age,FC,wages,roleId,FID=================
        UserInfo[] users = new UserInfo[userData.length];
        FamilyInfo family = new FamilyInfo();
        Long oldFamilyId = (long)0;
        Long newManagerId= (long)0;
        //获取用户对象到users
        for(int i = 0;i<userData.length;i++){

            String strId= userData[i].substring(userData[i].indexOf("id")+3);
            Long id = Long.parseLong(strId.substring(0,strId.indexOf(",")));

            String account = userData[i].substring(userData[i].indexOf("account")+8);
            account  = account.substring(0,account.indexOf(","));

            String password = userData[i].substring(userData[i].indexOf("password")+9);
            password = password.substring(0,password.indexOf(","));

            String name = userData[i].substring(userData[i].indexOf("name")+5);
            name = name.substring(0,name.indexOf(","));

            String strAge = userData[i].substring(userData[i].indexOf("age")+4);
            int age = Integer.parseInt(strAge.substring(0,strAge.indexOf(",")));

            String familyCall = userData[i].substring(userData[i].indexOf("familyCall")+11);
            familyCall = familyCall.substring(0,familyCall.indexOf(","));

            String gongzi = userData[i].substring(userData[i].indexOf("wages")+6);
            gongzi = gongzi.substring(0,gongzi.indexOf(","));
            BigDecimal wages = new BigDecimal(gongzi);

            String strRoleId = userData[i].substring(userData[i].indexOf("roleId")+7);
            Long roleId = Long.parseLong(strRoleId.substring(0,strRoleId.indexOf(",")));

            String strFamilyId= userData[i].substring(userData[i].indexOf("familyId")+9);
            Long familyId = Long.parseLong(strFamilyId.substring(0,strFamilyId.indexOf(")")));

            oldFamilyId = familyId;
            users[i] = new UserInfo();
            users[i].setRoleId(roleId);
            users[i].setAccount(account);
            users[i].setPassword(password);
            users[i].setName(name);
            users[i].setAge(age);
            users[i].setFamilyCall(familyCall);
            users[i].setWages(wages);
            if(userInfoMapper.insert(users[i])<=0){
                return "备份用户失败！";
            }
        }
        //创建家庭并删除原来的数据
        for(int i = 0;i<userData.length;i++){
            //创建家庭、删除以前旧的数据
            if(users[i].getRoleId() == 2){
                family.setFamilyMembers(userData.length);
                if(manger == null){
                    return "数据中无管理员，创建家庭失败！";
                }
                family.setManagerId(users[i].getId());
                if(familyInfoMapper.insert(family)<=0) {
                    return "创建家庭失败！";
                }
                newManagerId = users[i].getId();
                userInfoMapper.delete(new QueryWrapper<UserInfo>().eq("familyId",oldFamilyId));
            }
        }
        //更新用户的familyID
        FamilyInfo fmy = familyInfoMapper.selectOne(new QueryWrapper<FamilyInfo>().eq("managerId",newManagerId));
        for(int i = 0;i<userData.length;i++){
            users[i].setFamilyId(fmy.getId());
            userInfoMapper.updateById(users[i]);
        }
        //=======================恢复Income数据:quota,type,userid,incomeDate,incomeRoot,remakers=================
        for(int i = 0;i<incomeData.length;i++){

            IncomeInfo income = new IncomeInfo();

            String jine = incomeData[i].substring(incomeData[i].indexOf("quota")+6);
            jine = jine.substring(0,jine.indexOf(","));
            BigDecimal quota = new BigDecimal(jine);

            String type = incomeData[i].substring(incomeData[i].indexOf("type")+5);
            type = type.substring(0,type.indexOf(","));

            String strUserId = incomeData[i].substring(incomeData[i].indexOf("userid")+7);
            Long userid = Long.parseLong(strUserId.substring(0,strUserId.indexOf(",")));

            String strIncomeDate = incomeData[i].substring(incomeData[i].indexOf("incomeDate")+11);
            strIncomeDate = strIncomeDate.substring(0,strIncomeDate.indexOf(","));
            Date incomeDate = new Date(strIncomeDate);

            String incomeRoot = incomeData[i].substring(incomeData[i].indexOf("incomeRoot")+11);
            incomeRoot = incomeRoot.substring(0,incomeRoot.indexOf(","));

            String remakers = incomeData[i].substring(incomeData[i].indexOf("remakers")+9);
            remakers = remakers.substring(0,remakers.indexOf(","));

            income.setQuota(quota);
            income.setType(type);
            income.setUserid(userid);
            income.setIncomeDate(incomeDate);
            income.setIncomeRoot(incomeRoot);
            income.setRemakers(remakers);

            if(incomeInfoMapper.insert(income)<=0){
                return "收入备份失败！";
            }
        }
        //=======================恢复Income数据:quota,type,userid,payDate,payFor,remakers=======
        for(int i = 0;i<payData.length;i++){

            PayInfo pay = new PayInfo();

            String jine = payData[i].substring(payData[i].indexOf("quota")+6);
            jine = jine.substring(0,jine.indexOf(","));
            BigDecimal quota = new BigDecimal(jine);

            String type = payData[i].substring(payData[i].indexOf("type")+5);
            type = type.substring(0,type.indexOf(","));

            String strUserId = payData[i].substring(payData[i].indexOf("userid")+7);
            Long userid = Long.parseLong(strUserId.substring(0,strUserId.indexOf(",")));

            String strPayDate = payData[i].substring(payData[i].indexOf("payDate")+11);
            strPayDate = strPayDate.substring(0,strPayDate.indexOf(","));
            Date payDate = new Date(strPayDate);

            String payFor = payData[i].substring(payData[i].indexOf("payFor")+11);
            payFor = payFor.substring(0,payFor.indexOf(","));

            String remakers = payData[i].substring(payData[i].indexOf("remakers")+9);
            remakers = remakers.substring(0,remakers.indexOf(","));

            pay.setQuota(quota);
            pay.setType(type);
            pay.setUserid(userid);
            pay.setPayDate(payDate);
            pay.setPayFor(payFor);
            pay.setRemakers(remakers);


            if(payInfoMapper.insert(pay)<=0){
                return "支出备份失败！";
            }
        }

        return "备份成功！";
    }

    /**
     * 格式化数据
     * @param id
     * @return
     */
    public boolean Formatting(Long id){
        UserInfo manager = userInfoMapper.selectById(id);
        if(manager == null){
            return false;
        }
        List<UserInfo> allUsers = userInfoMapper.selectList(new QueryWrapper<UserInfo>().eq("familyId",manager.getFamilyId()));
        if(allUsers==null||allUsers.size()<=0)
        {
            return false;
        }
        for(int i=0;i<allUsers.size();i++){
            incomeInfoMapper.delete(new QueryWrapper<IncomeInfo>().eq("userid",allUsers.get(i).getId()));
            payInfoMapper.delete(new QueryWrapper<PayInfo>().eq("userid",allUsers.get(i).getId()));
        }
        return true;
    }

}
