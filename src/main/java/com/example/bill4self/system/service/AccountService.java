package com.example.bill4self.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bill4self.system.dto.LoginDto;
import com.example.bill4self.system.dto.LoginRequest;
import com.example.bill4self.system.entity.Account;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
public interface AccountService extends IService<Account> {

    LoginDto login(LoginRequest loginRequest);

}
