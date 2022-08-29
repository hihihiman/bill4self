package com.example.bill4self.base.handler;

import com.example.bill4self.base.constant.ResultEnum;
import com.example.bill4self.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
//
//    @ExceptionHandler(AuthException.class)
//    public String ErrorHandler(AuthorizationException e) {
//        log.error("没有通过权限验证！", e);
//        return "没有通过权限验证！";
//    }

    @ExceptionHandler(Exception.class)
    public Result execption(Exception e) {
        log.error("未知异常！", e);
        return Result.error(ResultEnum.SERVER_BUSY.getValue(),ResultEnum.SERVER_BUSY.getName());
    }
}