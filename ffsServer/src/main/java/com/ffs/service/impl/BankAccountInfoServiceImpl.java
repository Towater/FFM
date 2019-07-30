package com.ffs.service.impl;

import com.ffs.entity.BankAccountInfo;
import com.ffs.mapper.BankAccountInfoMapper;
import com.ffs.service.IBankAccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BankAccountInfoServiceImpl extends ServiceImpl<BankAccountInfoMapper, BankAccountInfo> implements IBankAccountInfoService {

}
