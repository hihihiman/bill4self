package com.example.bill4self.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bill4self.base.mapper.MyMapper;
import com.example.bill4self.system.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
public interface AccountMapper extends MyMapper<Account> {

    /**
     * 分页查询账号
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, @Param(Constants.WRAPPER) Wrapper<Account> wrapper);

    /**
     * 根据 account_id 查询账号信息
     *
     * @param id
     * @return
     */
    Account selectAccountById(Long id);
}
