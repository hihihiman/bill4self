package com.example.bill4self.base.vo;

import com.example.bill4self.base.constant.ResultEnum;
import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultEnum.OK.getValue());
        result.setMessage(ResultEnum.OK.getName());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result(code, message);
    }
    public static <T> Result<T> error( String message) {
        Result<T> result = new Result<T>();
        result.setCode(ResultEnum.ERROR.getValue());
        result.setMessage(message);
        return result;
    }
}