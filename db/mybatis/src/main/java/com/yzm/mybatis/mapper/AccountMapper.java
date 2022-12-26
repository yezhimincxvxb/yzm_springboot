package com.yzm.mybatis.mapper;

import com.yzm.mybatis.entity.table.Account;
import com.yzm.mybatis.entity.vo.AccountVo;
import com.yzm.mybatis.mapper.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper extends BaseMapper<Account, AccountVo> {
}
