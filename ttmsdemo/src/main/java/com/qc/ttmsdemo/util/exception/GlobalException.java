package com.qc.ttmsdemo.util.exception;



import com.qc.ttmsdemo.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/3/28 22:23
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException implements Serializable {
    private Integer code;
    private String message;

    public GlobalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalException(ResultEnum resultType, String message) {
        this.code = resultType.getType();
        this.message = message;
    }

    public GlobalException(ResultEnum resultEnum) {
        this.code = resultEnum.getType();
        this.message = resultEnum.getMean();
    }

    public GlobalException(String message) {
        this.code = ResultEnum.PROGRAM_ERROR.getType();
        this.message = message;
    }
}
