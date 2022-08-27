package com.example.bill4self.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Josh-ZJUT
 * @date 2022/8/27 10:33
 * @email dujianghui537885@163.com
 */
@Data
@ApiModel(value = "ResourceVo", description = "资源表")
public class ResourceVo {

    @ApiModelProperty("主键")
    private Long resourceId;


    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("请求地址")
    private String url;

    @ApiModelProperty("下级资源")
    private List<ResourceVo> subs;
}
