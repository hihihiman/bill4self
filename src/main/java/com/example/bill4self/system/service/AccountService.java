package com.example.bill4self.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    LoginDto login(LoginRequest loginRequest);

    /**
     * 分页查询账号
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);

    /**
     * 根据 account_id 查询账号信息
     *
     * @param id
     * @return
     */
    Account getAccountById(Long id);
}
