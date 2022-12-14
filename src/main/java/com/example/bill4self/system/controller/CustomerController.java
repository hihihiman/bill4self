package com.example.bill4self.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bill4self.base.util.MyQuery;
import com.example.bill4self.base.util.QueryUtil;
import com.example.bill4self.base.util.ResultUtil;
import com.example.bill4self.base.vo.Result;
import com.example.bill4self.system.entity.Customer;
import com.example.bill4self.system.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/customer")
@Api(tags = {"客户controller"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("to-list")
    @ApiOperation(value = "进入列表页")
    public String toList() {
        return "customer/customerList";
    }

    @GetMapping("list")
    @ApiOperation(value = "条件查询列表")
    public Result list(@RequestParam Map<String, String> param) {

        final MyQuery<Customer> myQuery = QueryUtil.buildMyQuery(param);
        final Page<Customer> page = customerService.page(myQuery.getPage(), myQuery.getWrapper().orderByDesc("create_time"));

//        Page<Customer> customerPage = customerService.lambdaQuery()
//                .like(StrUtil.isNotBlank(realName), Customer::getRealName, realName)
//                .like(StrUtil.isNotBlank(phone), Customer::getPhone, phone)
//                .orderByDesc(Customer::getCustomerId)
//                .page(new Page<>(page, limit));
        return ResultUtil.buildPageResult(page);
    }

    @PostMapping("add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody Customer customer) {
        return ResultUtil.buildResult(customerService.save(customer));
    }


    @GetMapping("detail/{id}")
    @ApiOperation(value = "查询")
    public Result detail(@PathVariable Long id) {
        final Customer customer = customerService.getById(id);
        return Result.success(customer);
    }

    @PutMapping("update")
    @ApiOperation(value = "修改")
    public Result update(@RequestBody Customer customer) {
        return ResultUtil.buildResult(customerService.updateById(customer));
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id) {
        Customer customer = new Customer();
        customer.setCustomerId(id);
        return ResultUtil.buildResult(customerService.removeByIdWithFill(customer));
    }


}
