package com.yzm.plus.service.impl;

import com.yzm.plus.entity.Account;
import com.yzm.plus.mapper.AccountMapper;
import com.yzm.plus.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号资金表 服务实现类
 * </p>
 *
 * @author Yzm
 * @since 2023/01/03
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
