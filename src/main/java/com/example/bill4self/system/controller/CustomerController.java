package com.example.bill4self.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("toList")
    @ApiOperation(value = "进入列表页")
    public String toList() {
        return "customer/customerList";
    }


}
