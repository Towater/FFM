package com.ffs.api.controller;

import com.ffs.api.CommonResult;
import com.ffs.commen.Query;
import com.ffs.service.impl.PrintIncomeInfoServiceImpl;
import com.ffs.service.impl.PrintPayInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping
public class PrintInfoController {
    @Autowired
    PrintPayInfoServiceImpl printInfoService;
    @Autowired
    PrintIncomeInfoServiceImpl printIncomInfoService;
    //根据消费项目查询
    @PostMapping(value="/findByPayfor")
    public CommonResult findByPayfor(String payfor,HttpServletRequest request,int pc){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
        return Query.queryList(printInfoService.findByPayfor(payfor,uid,pc,10),"找不到指定内容！");
    }
    //根据消费时间模糊查询支出表
    @PostMapping(value="/findbytTime")
    public CommonResult findByPayTime(String time,HttpServletRequest request,int pc){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
        return Query.queryList(printInfoService.findInfoByTime(time,uid,pc,10),"找不到指定内容！");
    }
    //查询出当前用户下所有的消费信息
    @PostMapping(value="/findAll")
    public CommonResult findPayAll(HttpServletRequest request,int pc){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
        return Query.queryList(printInfoService.findInfoAll(uid,pc,10),"找不到指定内容！");
    }
    //根据支出时间模糊查询收入表
    @PostMapping(value="/findByIncomeTime")
    public CommonResult findByIncomTime(String time,HttpServletRequest request,int pc){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
        return  Query.queryList(printIncomInfoService.findInfoByTime(time,uid,pc,10),"找不到指定内容！");
    }
    //根据收入来源查询
    @PostMapping(value="/findByIncomRoot")
    public CommonResult findByIncomRoot(String incomRoot,HttpServletRequest request,int pc){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
        return  Query.queryList(printIncomInfoService.findByIncomeRoot(incomRoot,uid,pc,10),"找不到指定内容！");
    }
    //查询出当前用户下所有的收入信息
    @PostMapping(value="/findIncomAll")
    public CommonResult findIncomAll(HttpServletRequest request,int pc){
        Long uid = Long.parseLong(request.getAttribute("id").toString());
        return Query.queryList(printIncomInfoService.findInfoAll(uid,pc,10),"找不到指定内容！");
    }
    //得到收入表的记录数
    @GetMapping("/getIncomCount")
    public CommonResult getIncomCount(){
        return Query.queryClass(printIncomInfoService.getCount(),"可能因为表中无记录！");
    }
    //得到消费表中的记录数
    @GetMapping("/getPayCount")
    public CommonResult getPayCount(){
        return Query.queryClass(printInfoService.getCount(),"可能因为表中无记录！");
    }
    @GetMapping("/getCount")
    public CommonResult getCount(){
        return Query.queryClass(printInfoService.getCount()>printIncomInfoService.getCount()?
                printInfoService.getCount():printIncomInfoService.getCount(),"可能因为表中无记录！");
    }
}
