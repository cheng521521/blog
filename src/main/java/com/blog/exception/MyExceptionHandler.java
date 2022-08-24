package com.blog.exception;

import com.blog.result.Result;
import com.blog.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionHandler
 * @Author jackchen
 * @Date 2022/8/24 1MY:58
 * @Description 全局异常处理类
 **/
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        log.info("全局异常捕获" + e);
        return ResultResponse.getFailResult("服务器错误");
    }
}
