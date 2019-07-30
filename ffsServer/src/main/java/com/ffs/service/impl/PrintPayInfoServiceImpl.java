package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffs.entity.IncomeInfo;
import com.ffs.entity.PayInfo;
import com.ffs.mapper.PayInfoMapper;
import com.ffs.service.IPrintPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Component
public class PrintPayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPrintPayInfoService {
    @Autowired
    PayInfoMapper payInfoMapper;
    //根据时间模糊查询支出表
    public List<PayInfo> findByTime(String time,Long id){
        List<PayInfo> payInfoList=payInfoMapper.selectList(new QueryWrapper<PayInfo>().eq("userid",id).like("payDate",time));
        return payInfoList;
    }
    public List<PayInfo> findInfoByTime(String time,Long id,int pc,int ps){
        Page<PayInfo> pages = new Page<>(pc,ps);
        IPage<PayInfo> ipage=payInfoMapper.selectPage(pages,new QueryWrapper<PayInfo>().eq("userid",id).like("payDate",time));
        List<PayInfo> infos = ipage.getRecords();
        return infos;
    }
    //根据支出用途模糊查询支出表
    public List<PayInfo> findByPayfor(String payfor,Long id,int pc,int ps){
        Page<PayInfo> pages = new Page<>(pc,ps);
        IPage<PayInfo> ipage=payInfoMapper.selectPage(pages,new QueryWrapper<PayInfo>().eq("userid",id).like("payFor",payfor));
        List<PayInfo> infos = ipage.getRecords();
        return infos;
    }
    //查询出该账户下所有的支出信息表,并且先显示最近的支出信息
    public List<PayInfo> findAll(Long id){
        List<PayInfo> payInfoList=payInfoMapper.selectList(new QueryWrapper<PayInfo>().eq("userid",id).orderByDesc("payDate"));
        return payInfoList;
    }
    public List<PayInfo> findInfoAll(Long id,int pc,int ps){
        Page<PayInfo> pages = new Page<>(pc,ps);
        IPage<PayInfo> ipage=payInfoMapper.selectPage(pages,new QueryWrapper<PayInfo>().eq("userid",id).orderByDesc("payDate"));
        List<PayInfo> infos = ipage.getRecords();
        return infos;
    }
    //查询表中一共有多少条记录
    public Integer getCount(){
        Integer count=payInfoMapper.selectCount(new QueryWrapper<PayInfo>());
        return count;
    }
    //判断记录中是否有查询的userid
    public boolean isNor(Long userid){
        Integer count =  payInfoMapper.selectCount(new QueryWrapper<PayInfo>().eq("userid",userid));
        return count!=0;
    }
}

