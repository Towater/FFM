package com.ffs.service;

import com.ffs.entity.PayInfo;

import java.util.Date;
import java.util.List;

public interface IPrintPayInfoService {
    //根据时间模糊查询支出表
    public List<PayInfo> findByTime(String time,Long id);
    public List<PayInfo> findInfoByTime(String time,Long id,int pc,int ps);
    //根据支出用途模糊查询支出表
    public List<PayInfo> findByPayfor(String payfor,Long id,int pc,int ps);
    //查询出所有的支出信息表,并且先显示最近的支出信息
    public List<PayInfo> findAll(Long id);
    public List<PayInfo> findInfoAll(Long id,int pc,int ps);
    //查询表中一共有多少条记录
    public Integer getCount();
    //判断记录中是否有查询的userid
    public boolean isNor(Long userid);

}
