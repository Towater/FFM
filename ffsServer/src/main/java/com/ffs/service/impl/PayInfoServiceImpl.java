package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ffs.entity.IncomeInfo;
import com.ffs.entity.PayInfo;
import com.ffs.mapper.PayInfoMapper;
import com.ffs.service.IPayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@Service
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPayInfoService {

    @Autowired
    PayInfoMapper py;

    /**
     * 获取全部支出的信息
     * @return
     */
    public List<PayInfo> getAllInfo(){

        List<PayInfo> infos = py.selectList(null);
        return infos;
    }

    /**
     * 获取该userid的全部支出信息
     * @param userid
     * @return
     */
    public List<PayInfo> getInfoByUserid(long userid){

        List<PayInfo> infos = py.selectList(new QueryWrapper<PayInfo>().eq("userid",userid));
        return infos;
    }

    /**
     * 删除该userid的全部信息
     * @param userid
     * @return
     */
    public Integer delAllByUserid(long userid){
        Integer result =py.deleteById(userid);
        return result;
    }

    /**
     * 通过主键id删除
     */
    public Integer delById(int id){
        Integer result =py.delete(new QueryWrapper<PayInfo>().eq("id",id));
        return result;
    }

    /**
     * 通过多个主键id删除
     */
    public Integer delByIdS(List<Integer> ids){

        Integer result = py.deleteBatchIds(ids);
        return result;

    }

    /**
     * 插入一条新信息
     * @param payInfo
     * @return
     */
    public int insert(PayInfo payInfo){
        int result = py.insert(payInfo);
        return result;
    }

    /**
     * 通过主键id更新数据
     * @param payInfo
     * @return
     */
    public int updateByid(PayInfo payInfo){
        int result = py.updateById(payInfo);
        return result;
    }

    /**
     *  分页查询
     */
    public List<PayInfo> getPage(int page,int num) {

        Page<PayInfo> pages = new Page<>(page,num);

        IPage<PayInfo> ipage =py.selectPage(pages,null);
        List<PayInfo> infos = ipage.getRecords();

        return infos;
    }

    public long totalPage(int page,int num){
        Page<PayInfo> pages = new Page<>(page,num);
        IPage<PayInfo> ipage =py.selectPage(pages,null);
        return ipage.getPages();
    }

    public List<PayInfo> getPageById(int page,int num,long userid){
        Page<PayInfo> pages = new Page<>(page,num);

        IPage<PayInfo> ipage =py.selectPage(pages,new QueryWrapper<PayInfo>().eq("userid",userid));
        List<PayInfo> infos = ipage.getRecords();

        return infos;
    }

    public long totalPageById(int page,int num,long userid){
        Page<PayInfo> pages = new Page<>(page,num);
        IPage<PayInfo> ipage =py.selectPage(pages,new QueryWrapper<PayInfo>().eq("userid",userid));
        return ipage.getPages();
    }

}
