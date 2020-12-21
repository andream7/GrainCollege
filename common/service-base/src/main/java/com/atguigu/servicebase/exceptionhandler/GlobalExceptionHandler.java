package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Res error(Exception e) {
        e.printStackTrace();
        return Res.error().message("执行全局异常处理");
    }

    // 自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Res error(GuliException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return Res.error().code(e.getCode()).message(e.getMsg());
    }

}
