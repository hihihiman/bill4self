package com.example.bill4self.system.entity;

import com.example.bill4self.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Resource对象", description = "资源表")
public class Resource  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long resourceId;

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("资源类型(0、目录 1、菜单 2、按钮)")
    private Integer resourceType;

    @ApiModelProperty("请求地址")
    private String url;

    @ApiModelProperty("权限标识码")
    private String code;

    @ApiModelProperty("排序")
    private Integer sort;


}
