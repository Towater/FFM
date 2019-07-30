package com.ffs.service;


import com.ffs.entity.IncomeInfo;

import java.util.Date;
import java.util.List;

public interface IPrintIncomeInfoService {
    //根据时间模糊查询收入表
    public List<IncomeInfo> findByTime(String time,Long id);
    public List<IncomeInfo> findInfoByTime(String time,Long id,int pc,int ps);
    //根据支出用途模糊查询收入表
    public List<IncomeInfo> findByIncomeRoot(String incomeRoot,Long id,int pc,int ps);
    //查询出所有的收入信息表,并且先显示最近的收入信息
    public List<IncomeInfo> findAll(Long id);
    public List<IncomeInfo> findInfoAll(Long id,int pc,int ps);
    //查询表中一共有多少条记录
    public Integer getCount();
    //判断记录中是否有查询的userid
    public boolean isNor(Long userid);
}
