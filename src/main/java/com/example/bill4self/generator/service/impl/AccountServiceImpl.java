package com.example.bill4self.generator.service.impl;

import com.example.bill4self.generator.entity.Account;
import com.example.bill4self.generator.mapper.AccountMapper;
import com.example.bill4self.generator.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
