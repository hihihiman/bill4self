package com.example.bill4self.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 09:35
 * @email dujianghui537885@163.com
 */
@Data
@ApiModel(value = "账号查询条件", description = "分页查询")
public class AccountQuery {

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private String createTimeRange;

    @ApiModelProperty("第几页")
    private Long page;

    @ApiModelProperty("每页几条")
    private Long limit;
}
