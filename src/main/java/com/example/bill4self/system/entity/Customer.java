package com.example.bill4self.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.bill4self.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Customer对象", description = "客户表")
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Long customerId;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("地址")
    private String address;


}
