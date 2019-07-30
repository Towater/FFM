package com.ffs.api.controller;

import com.ffs.api.CommonResult;
import com.ffs.service.impl.DatabaseServicelmpl;
import com.ffs.service.jwt.TokenManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
@RequestMapping("/database")
@ResponseBody
public class DatebaseController {

    @Autowired
    DatabaseServicelmpl databaseServicelmpl;

    @GetMapping("/backup")
    public CommonResult AddDBbackups() throws IOException {
        CommonResult re = new CommonResult();
        if(databaseServicelmpl.AddDBbackups()){
            re.success("备份成功！");
            return re;
        }else{
            re.badRequest("备份失败！");
            return re;
        }
    }

    /**
     * 获取管理员家庭的所有信息并加密保存在一个txt文件
     * 此处用户数据备份
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/getAllDataById")
    public CommonResult GetAllDataById(Long id) throws IOException {
        CommonResult re = new CommonResult();
        String info = databaseServicelmpl.GetAllDataById(id);
        if(info==null)
        {
            re.badRequest("无任何数据可以备份");
        }else if(info == "此管理员不存在!"){
            re.badRequest(info);
        }else if(info == "此用户不是管理员!"){
            re.badRequest(info);
        } else{
            re.success(info);
        }
        return re;
    }

    /**
     * 解密并恢复数据
     * @param txt
     * @return
     */
    @PostMapping("/pullAllDataByFile")
    public CommonResult PullAllDataByFile(File txt) throws IOException {
        CommonResult re = new CommonResult();
        //读取文件
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(txt))); //这里可以控制编码
            sb = new StringBuffer();
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String res  = databaseServicelmpl.PullAllDataByFile(TokenManage.decodeString(sb.toString()));
        if(res == "备份成功！"){
            re.success(res);
        }else{
            re.badRequest(res);
        }
        return re;
    }

    @GetMapping("/huifutest")
    public CommonResult huifutest(String data){
        CommonResult re = new CommonResult();
        String res  = databaseServicelmpl.PullAllDataByFile(data);
        if(res == "备份成功！"){
            re.success(res);
        }else{
            re.badRequest(res);
        }
        return re;
    }

    @GetMapping("/format")
    public CommonResult Formatting(Long id){
       CommonResult re = new CommonResult();
       if(databaseServicelmpl.Formatting(id))
       {
           re.success("数据格式化成功！");
       }else{
           re.badRequest("数据格式化失败！");
       }
       return re;
    }
}