package com.ffs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ffs.entity.IncomeInfo;
import com.ffs.mapper.IncomeInfoMapper;
import com.ffs.service.IIncomeInfoService;
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
public class IncomeInfoServiceImpl extends ServiceImpl<IncomeInfoMapper, IncomeInfo> implements IIncomeInfoService {

    @Autowired
    IncomeInfoMapper im;

    /**
     * 获取全部收入信息
     * 2019/7/8
     */
    public List<IncomeInfo> getAllIncomeInfo(){

        List<IncomeInfo> infos = im.selectList(null);
        return infos;
    }

    /**
     * 获取该userid的全部收入信息
     * 2019/7/8
     */
    public List<IncomeInfo> getIncomeByUserid(long userid){

        List<IncomeInfo> infos = im.selectList(new QueryWrapper<IncomeInfo>().eq("userid",userid));
        return infos;
    }

    /**
     *  分页查询
     */
    public List<IncomeInfo> getPage(int page,int num) {

        Page<IncomeInfo> pages = new Page<>(page,num);

        IPage<IncomeInfo> ipage =im.selectPage(pages,null);
        List<IncomeInfo> infos = ipage.getRecords();

        return infos;
    }

    public long totalPage(int page,int num){
        Page<IncomeInfo> pages = new Page<>(page,num);
        IPage<IncomeInfo> ipage =im.selectPage(pages,null);
        return ipage.getPages();
    }

    public List<IncomeInfo> getPageById(int page,int num,long userid){
        Page<IncomeInfo> pages = new Page<>(page,num);

        IPage<IncomeInfo> ipage =im.selectPage(pages,new QueryWrapper<IncomeInfo>().eq("userid",userid));
        List<IncomeInfo> infos = ipage.getRecords();

        return infos;
    }

    public long totalPageById(int page,int num,long userid){
        Page<IncomeInfo> pages = new Page<>(page,num);
        IPage<IncomeInfo> ipage =im.selectPage(pages,new QueryWrapper<IncomeInfo>().eq("userid",userid));
        return ipage.getPages();
    }
    /**
     * 删除userid的所有数据
     * 2019/7/8
     */
    public Integer delAllByUserid(long userid){
        Integer result =im.deleteById(userid);
        return result;
    }

    /**
     * 通过主键id删除
     */
    public Integer delById(int id){
        Integer result =im.delete(new QueryWrapper<IncomeInfo>().eq("id",id));
        return result;
    }

    /**
     * 通过多个主键id删除
     */
    public Integer delByIdS(List<Integer> ids){

        Integer result = im.deleteBatchIds(ids);
        return result;

    }

    /**
     * 插入一条新的数据
     * @param incomeInfo
     * @return
     */
    public int insert(IncomeInfo incomeInfo){
        int result = im.insert(incomeInfo);
        return result;
    }

    /**
     * 通过主键id更新数据
     * @return
     */
    public int updateByid(IncomeInfo incomeInfo){
        int result = im.updateById(incomeInfo);
        return result;
    }
}
