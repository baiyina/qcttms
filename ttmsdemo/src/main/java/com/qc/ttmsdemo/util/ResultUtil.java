package com.qc.ttmsdemo.util;


import com.qc.ttmsdemo.domain.vo.Result;
import com.qc.ttmsdemo.enums.ResultEnum;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:48
 * @version: 1.0
 */
public class ResultUtil {
    public static final <T> Result<T> success () {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(ResultEnum.SUCCESS.getMean());
        return result;
    }
    public static final <T> Result<T> success (T data) {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(ResultEnum.SUCCESS.getMean());
        result.setData(data);
        return result;
    }
    public static final <T> Result<T> success (T data, String message) {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public static final <T> Result<T> success (String message) {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(message);
        return result;
    }

    public static final <T> Result<T> success (ResultEnum type, String message) {
        Result result = new Result();
        result.setType(type);
        result.setMsg(message);
        return result;
    }
    public static final <T> Result<T> error () {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(ResultEnum.SUCCESS.getMean());
        return result;
    }
    public static final <T> Result<T> error (T data) {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(ResultEnum.SUCCESS.getMean());
        result.setData(data);
        return result;
    }
    public static final <T> Result<T> error (T data, String message) {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public static final <T> Result<T> error (String message) {
        Result result = new Result();
        result.setType(ResultEnum.SUCCESS);
        result.setMsg(message);
        return result;
    }

    public static final <T> Result<T> error (ResultEnum type, String message) {
        Result result = new Result();
        result.setType(type);
        result.setMsg(message);
        return result;
    }
}
