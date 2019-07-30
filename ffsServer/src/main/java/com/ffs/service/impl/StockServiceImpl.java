package com.ffs.service.impl;

import com.ffs.entity.Stock;
import com.ffs.mapper.StockMapper;
import com.ffs.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {


    @Autowired
    StockMapper stockMapper;

    public List<Stock> findByID(long id){
        List<Stock> stock=stockMapper.selectList(new QueryWrapper<Stock>().eq("userid",id));
        return stock;
    }

    public Integer delByCode(long id,String code){
        Integer result =stockMapper.delete(new QueryWrapper<Stock>().eq("userid",id).eq("stockCode",code));
        return result;
    }

    public int insert(Stock stock){
        int result = stockMapper.insert(stock);
        return result;
    }

}
