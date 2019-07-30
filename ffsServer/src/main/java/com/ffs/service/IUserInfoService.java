package com.ffs.service;

import com.ffs.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
public interface IUserInfoService extends IService<UserInfo> {
    String Login(String account,String password);
}
