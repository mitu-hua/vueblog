package com.mituh.vueblog.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author
 * @Date 2020/11/25 17:46
 */

@Slf4j
@RestControllerAdvice     //s
public class GlobalExceptionHandler {
    //全局异常处理

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e){
        log.error("运行时异常--------{}",e);
        return new Result(401,e.getMessage(),null);
    }

    //捕获实体校验异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体校验异常------{}",e);

        // 简化输出错误信息
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        return new Result().fail(objectError.getDefaultMessage());
    }

    //捕获断言异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("断言异常--------{}",e);
        return new Result().fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常--------{}",e);
        return new Result().fail(e.getMessage());
    }
}
