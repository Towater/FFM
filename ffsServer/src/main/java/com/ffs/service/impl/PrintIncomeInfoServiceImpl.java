package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffs.entity.IncomeInfo;
import com.ffs.entity.PayInfo;
import com.ffs.mapper.IncomeInfoMapper;
import com.ffs.service.IIncomeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Component
public class PrintIncomeInfoServiceImpl extends ServiceImpl<IncomeInfoMapper, IncomeInfo> implements IIncomeInfoService {
    @Autowired
    IncomeInfoMapper incomeInfoMapper;
    //根据时间模糊查询收入表
    public List<IncomeInfo> findByTime(String time,Long id){
        List<IncomeInfo> incomeInfoList=incomeInfoMapper.selectList(new QueryWrapper<IncomeInfo>().eq("userid",id).like("incomeDate",time));
        return  incomeInfoList;
    }
    public List<IncomeInfo> findInfoByTime(String time,Long id,int pc,int ps){
        Page<IncomeInfo> pages = new Page<>(pc,ps);
        IPage<IncomeInfo> ipage=incomeInfoMapper.selectPage(pages,new QueryWrapper<IncomeInfo>().eq("userid",id).like("incomeDate",time));
        List<IncomeInfo> infos = ipage.getRecords();
        return infos;
    }
    //根据支出用途模糊查询收入表
    public List<IncomeInfo> findByIncomeRoot(String incomeRoot,Long id,int pc,int ps){
        Page<IncomeInfo> pages = new Page<>(pc,ps);
        IPage<IncomeInfo> ipage=incomeInfoMapper.selectPage(pages,new QueryWrapper<IncomeInfo>().eq("userid",id).like("incomeRoot",incomeRoot));
        List<IncomeInfo> infos = ipage.getRecords();
        return infos;
    }
    //查询出该账户下所有的收入信息表,并且先显示最近的收入信息
    public List<IncomeInfo> findAll(Long id){
        List<IncomeInfo> incomeInfoList=incomeInfoMapper.selectList(new QueryWrapper<IncomeInfo>().eq("userid",id).orderByDesc("incomeDate"));
        return  incomeInfoList;
    }
    public List<IncomeInfo> findInfoAll(Long id,int pc,int ps){
        Page<IncomeInfo> pages = new Page<>(pc,ps);
        IPage<IncomeInfo> ipage=incomeInfoMapper.selectPage(pages,new QueryWrapper<IncomeInfo>().eq("userid",id).orderByDesc("incomeDate"));
        List<IncomeInfo> infos = ipage.getRecords();
        return infos;
    }
    //查询表中一共有多少条记录
    public Integer getCount(){
        Integer count=incomeInfoMapper.selectCount(new QueryWrapper<IncomeInfo>());
        return count;
    }
    //判断记录中是否有查询的userid
    public boolean isNor(Long userid){
        Integer count =  incomeInfoMapper.selectCount(new QueryWrapper<IncomeInfo>().eq("userid",userid));
        return count!=0;
    }

}
