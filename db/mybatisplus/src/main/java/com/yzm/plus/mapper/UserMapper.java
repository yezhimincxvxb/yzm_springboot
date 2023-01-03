package com.yzm.plus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yzm.plus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzm.plus.vo.UserVo;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Yzm
 * @since 2023/01/03
 */
public interface UserMapper extends BaseMapper<User> {
    // 如果返回类型是 IPage 则入参的 IPage 不能为null,因为 返回的IPage == 入参的IPage;
    // 如果想临时不分页,可以在初始化IPage时size参数传 <0 的值;
    IPage<UserVo> pageUser(IPage<?> page, boolean deleted);

    // 如果返回类型是 List 则入参的 IPage 可以为 null(为 null 则不分页),但需要你手动 入参的IPage.setRecords(返回的 List);
    List<UserVo> pageUser2(IPage<UserVo> page, boolean deleted);

}
