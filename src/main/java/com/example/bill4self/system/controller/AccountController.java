package com.example.bill4self.system.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bill4self.base.constant.ResultEnum;
import com.example.bill4self.base.vo.Result;
import com.example.bill4self.base.vo.ResultUtil;
import com.example.bill4self.system.dto.AccountQuery;
import com.example.bill4self.system.entity.Account;
import com.example.bill4self.system.service.AccountService;
import com.example.bill4self.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 设置加密密码和加密盐
     *
     * @param account
     */
    private static void setPasswordAndSalt(Account account) {
        final String salt = UUID.fastUUID().toString().replaceAll("-", "");
        account.setSalt(salt);

        final String password = account.getPassword();
        MD5 md5 = new MD5(salt.getBytes());
        // 十六进制摘要
        final String digestHex = md5.digestHex(password);
        account.setPassword(digestHex);
    }

    @GetMapping("list")
    @ApiOperation(value = "条件查询列表")
    public Result list(@RequestBody AccountQuery query) {

        QueryWrapper<Account> wrapper = Wrappers.query();
        wrapper.like(StrUtil.isNotBlank(query.getRealName()), "a.real_name", query.getRealName())
                .like(StrUtil.isNotBlank(query.getEmail()), "a.email", query.getEmail());
        final String createTimeRange = query.getCreateTimeRange();
        if (StrUtil.isNotBlank(createTimeRange)) {
            final String[] timeArray = createTimeRange.split(" - ");
            wrapper.ge("a.create_time", timeArray[0])
                    .le("a.create_time", timeArray[1]);
        }
        // 需要手动添加逻辑删除判断
        wrapper.eq("a.deleted", 0).orderByDesc("a.create_time");
        return ResultUtil.buildPageResult(accountService.accountPage(new Page<>(query.getPage(), query.getLimit()), wrapper));
    }

    @PostMapping("add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody Account account) {
        if (countUsername(account.getUsername(), null) > 0) {
            return Result.error(ResultEnum.NOT_ALLOWED.getValue(), "用户名已存在");
        }

        setPasswordAndSalt(account);

        return ResultUtil.buildResult(accountService.save(account));
    }

    @GetMapping("detail/{id}")
    @ApiOperation(value = "查询")
    public Result detail(@PathVariable Long id) {
        return Result.success(accountService.getAccountById(id));
    }

    @PutMapping("update")
    @ApiOperation(value = "修改")
    public Result update(@RequestBody Account account, HttpSession session) {
        final Account current = (Account) session.getAttribute("account");
        if (current != null && countUsername(account.getUsername(), current.getAccountId()) > 0) {
            return Result.error(ResultEnum.NOT_ALLOWED.getValue(), "用户名已存在");
        }

        if (StrUtil.isNotBlank(account.getPassword())) {
            setPasswordAndSalt(account);
        } else {
            account.setPassword(null);
        }
        return ResultUtil.buildResult(accountService.updateById(account));
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id, HttpSession session) {
        final Account current = (Account) session.getAttribute("account");
        if (current != null && id.equals(current.getAccountId())) {
            return Result.error(ResultEnum.NOT_ALLOWED.getValue(), "不能删除自己哦");
        }
        return ResultUtil.buildResult(accountService.removeById(id));
    }

    @GetMapping({"count/{username}", "count/{username}/{accountId}"})
    @ApiOperation(value = "检验用户名存在个数")
    public Result checkUsername(@PathVariable String username, @PathVariable(required = false) Long accountId) {
        return Result.success(countUsername(username, accountId));
    }

    /**
     * 检验用户名是否重复（新增、修改）
     *
     * @param username  用户名
     * @param accountId 新增时传null，修改时传已登录的 account id
     * @return
     */
    private Integer countUsername(String username, Long accountId) {
        return accountService.lambdaQuery()
                .eq(Account::getUsername, username)
                .ne(accountId != null, Account::getAccountId, accountId)
                .count();
    }
}
