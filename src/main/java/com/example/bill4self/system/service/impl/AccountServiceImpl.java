package com.example.bill4self.system.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bill4self.system.dto.LoginDto;
import com.example.bill4self.system.dto.LoginRequest;
import com.example.bill4self.system.entity.Account;
import com.example.bill4self.system.mapper.AccountMapper;
import com.example.bill4self.system.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public LoginDto login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        LoginDto loginDTO = new LoginDto()
                // 重定向到根目录
                .setPath("redirect:/");
        final Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (account == null) {
            loginDTO.setError("用户名不存在");
            return loginDTO;
        }
        MD5 md5 = new MD5(account.getSalt().getBytes());
        final String digestHex = md5.digestHex(password);
        if (!digestHex.equals(account.getPassword())) {
            loginDTO.setError("密码错误");
            return loginDTO;
        }
        loginDTO.setAccount(account);
        // 成功的跳转路径
        loginDTO.setPath("login/main");
        return loginDTO;
    }

    @Override
    public IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper) {
        return baseMapper.accountPage(page, wrapper);
    }

    @Override
    public Account getAccountById(Long id) {
        return baseMapper.selectAccountById(id);
    }
}
