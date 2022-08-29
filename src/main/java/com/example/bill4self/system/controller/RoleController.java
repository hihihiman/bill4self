package com.example.bill4self.system.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bill4self.base.vo.Result;
import com.example.bill4self.base.vo.ResultUtil;
import com.example.bill4self.system.dto.TreeVo;
import com.example.bill4self.system.entity.Customer;
import com.example.bill4self.system.entity.Role;
import com.example.bill4self.system.service.ResourceService;
import com.example.bill4self.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

//    @GetMapping("list")
//    @ApiOperation(value = "条件查询列表")
//    public Result list(@RequestParam(required = false) String realName,
//                       @RequestParam(required = false) String phone,
//                       @RequestParam Long page,
//                       @RequestParam Long limit) {
//
//        Page<Customer> customerPage = customerService.lambdaQuery()
//                .like(StrUtil.isNotBlank(realName), Customer::getRealName, realName)
//                .like(StrUtil.isNotBlank(phone), Customer::getPhone, phone)
//                .orderByDesc(Customer::getCustomerId)
//                .page(new Page<>(page, limit));
//        return ResultUtil.buildPageResult(customerPage);
//    }

    @PostMapping("add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody Role role) {
        return ResultUtil.buildResult(roleService.save(role));
    }


    @GetMapping("detail/{id}")
    @ApiOperation(value = "查询")
    public Result detail(@PathVariable Long id) {
        return Result.success(roleService.getById(id));
    }

    @PutMapping("update")
    @ApiOperation(value = "修改")
    public Result update(@RequestBody Role role) {
        return ResultUtil.buildResult(roleService.updateById(role));
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id) {
        return ResultUtil.buildResult(roleService.removeById(id));
    }

    @GetMapping("list-resource")
    @ApiOperation(value = "查询所有资源")
    public Result<List<TreeVo>> listResource() {
        return Result.success(resourceService.listResource());
    }

}
