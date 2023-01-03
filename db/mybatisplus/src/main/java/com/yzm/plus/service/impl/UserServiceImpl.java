package com.yzm.plus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzm.plus.common.PageResult;
import com.yzm.plus.entity.User;
import com.yzm.plus.mapper.UserMapper;
import com.yzm.plus.service.IUserService;
import com.yzm.plus.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Yzm
 * @since 2022/12/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public PageResult<UserVo> pageUser(IPage<?> page, boolean deleted) {
        IPage<UserVo> iPage = this.baseMapper.pageUser(page, deleted);
        return new PageResult<>(iPage);
    }

    @Override
    public PageResult<UserVo> pageUser2(IPage<UserVo> page, boolean deleted) {
        List<UserVo> userVos = this.baseMapper.pageUser2(page, deleted);
        return new PageResult<>(page, userVos);
    }
}
