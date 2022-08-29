package com.example.bill4self.system.dto;

import com.example.bill4self.system.entity.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Josh-ZJUT
 * @date 2022/8/26 22:05
 * @email dujianghui537885@163.com
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "登录dto", description = "重定向、错误提示、登陆人信息")
public class LoginDto {

    @ApiModelProperty("重定向跳转的路径")
    private String path;

    @ApiModelProperty("错误提示信息")
    private String error;

    @ApiModelProperty("当前登陆人信息")
    private Account account;
}
