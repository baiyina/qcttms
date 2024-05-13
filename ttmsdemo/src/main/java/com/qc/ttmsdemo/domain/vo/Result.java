package com.qc.ttmsdemo.domain.vo;

import com.qc.ttmsdemo.enums.ResultEnum;
import lombok.Data;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:37
 * @version: 1.0
 */
@Data
public class Result<T> {
    private ResultEnum type;

    private String msg;

    private T data;
}
