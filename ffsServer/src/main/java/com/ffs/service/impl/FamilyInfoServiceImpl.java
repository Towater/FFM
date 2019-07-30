package com.ffs.service.impl;

import com.ffs.entity.FamilyInfo;
import com.ffs.mapper.FamilyInfoMapper;
import com.ffs.service.IFamilyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-10
 */
@Service
public class FamilyInfoServiceImpl extends ServiceImpl<FamilyInfoMapper, FamilyInfo> implements IFamilyInfoService {

}
