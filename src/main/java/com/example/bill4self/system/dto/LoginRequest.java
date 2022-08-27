package com.example.bill4self.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Josh-ZJUT
 * @date 2022/8/27 10:02
 * @email dujianghui537885@163.com
 */
@Data
@ApiModel(value = "登陆请求", description = "账号密码")
public class LoginRequest {


    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
