package com.example.bill4self.system.controller;

import com.example.bill4self.base.vo.Result;
import com.example.bill4self.system.dto.LoginDto;
import com.example.bill4self.system.dto.LoginRequest;
import com.example.bill4self.system.dto.ResourceVo;
import com.example.bill4self.system.entity.Account;
import com.example.bill4self.system.service.AccountService;
import com.example.bill4self.system.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @author Josh-ZJUT
 * @date 2022/8/26 21:58
 * @email dujianghui537885@163.com
 */

@RestController
@RequestMapping("auth")
@Api(tags = {"登录controller"})
public class LoginController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;


    @PostMapping("login")
    @ApiOperation(value = "用户登陆")
    public Result login(@RequestBody @ApiParam(name = "loginRequest", value = "用户名密码", required = true) LoginRequest loginRequest,
                        HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        final LoginDto loginDTO = accountService.login(loginRequest);
        final String error = loginDTO.getError();
        final Account account = loginDTO.getAccount();
        if (error == null) {
            session.setAttribute("account", account);
            final List<ResourceVo> resourceVos = resourceService.listResourceByRoleId(account.getRoleId());
            final Set<String> convert = resourceService.convert(resourceVos);
            session.setAttribute("module", convert);
            return Result.success(resourceVos);
        } else {
            return Result.error(error);
        }
    }


    @GetMapping("logout")
    @ApiOperation(value = "登出")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
