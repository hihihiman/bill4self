package com.example.bill4self.base.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Josh-ZJUT
 * @date 2022/7/3 20:09
 * @email dujianghui537885@163.com
 * <p>
 * 参考：<a href="https://www.runoob.com/http/http-status-codes.html">...</a>
 */
public enum ResultEnum implements BaseEnum {

    /**
     * 1** 信息，服务器收到请求，需要请求者继续执行操作
     */

    /**
     * 2** 请求成功
     */
    OK(200, "成功"),

    /**
     * 3** 重定向：资源（网页等）被永久转移到其它URL
     */


    /**
     * 4** 	客户端错误：请求的资源（网页等）不存在
     */
    BAD_REQUEST(400, "客户端请求的语法错误，服务器无法理解"),
    UNAUTHORIZED(401, "用户身份认证失败"),

    /**
     * 5** 服务器错误：内部服务器错误、网络异常
     */
    ERROR(500, "服务器内部错误，无法完成请求");

    @EnumValue
    private final int code;
    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    @Override
    public String getName() {
        return msg;
    }
}