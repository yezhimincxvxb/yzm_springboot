package com.yzm.plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzm.plus.common.PageResult;
import com.yzm.plus.entity.User;
import com.yzm.plus.vo.UserVo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Yzm
 * @since 2022/12/30
 */
public interface IUserService extends IService<User> {

    PageResult<UserVo> pageUser(IPage<?> page, boolean deleted);

    PageResult<UserVo> pageUser2(IPage<UserVo> page, boolean deleted);

}
