package com.example.bill4self.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bill4self.base.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("role_resource")
@ApiModel(value = "RoleResource对象", description = "角色资源表")
public class RoleResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "role_resource_id", type = IdType.AUTO)
    private Long roleResourceId;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("资源id")
    private Long resourceId;


}
