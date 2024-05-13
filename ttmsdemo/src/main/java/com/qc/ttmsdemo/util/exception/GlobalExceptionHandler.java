package com.qc.ttmsdemo.util.exception;



import com.qc.ttmsdemo.domain.vo.Result;
import com.qc.ttmsdemo.enums.ResultEnum;
import com.qc.ttmsdemo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/4/10 23:06
 * @version: 1.0
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handleException (Exception e) {
        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            log.error("全局异常捕获 msg:{}", exception.getMessage());
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        } else if (e instanceof UndeclaredThrowableException) {
            GlobalException ex = (GlobalException) e.getCause();
            log.error("全局异常捕获:msg:{}" , ex.getMessage());
            return ResultUtil.error(ex.getCode(), ex.getMessage());

        } else {
            log.error("全局异常捕获:msg:{}" , e.getMessage());
            return ResultUtil.error(ResultEnum.PROGRAM_ERROR);
        }
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result handleMessageNotReadableException (HttpMessageNotReadableException e) {
        log.error("全局捕获异常:{}", e.getMessage());
        Throwable t = e.getCause();
        if (t instanceof JSONException) {
            t = t.getCause();
            if (t instanceof DateTimeParseException) {
                return ResultUtil.error(ResultEnum.PROGRAM_ERROR, "日期格式不正确");
            }
            return ResultUtil.error(ResultEnum.PROGRAM_ERROR, "数据格式不正确");
        }
        return ResultUtil.error(ResultEnum.PROGRAM_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleValidationExceptionHandler (MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg;
        if (bindingResult != null && bindingResult.hasErrors()) {
            msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            if (msg != null) {
                if (msg.contains("NumberFormatException")) {
                    msg = "参数类型错误！";
                }
            }
        } else  {
            msg = "系统繁忙，请稍后重试";
        }

        return ResultUtil.error(ResultEnum.PROGRAM_ERROR,msg);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleBindException (BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        String defaultMessage = objectError.getDefaultMessage();
        return ResultUtil.error(ResultEnum.PROGRAM_ERROR, defaultMessage);
    }
}
