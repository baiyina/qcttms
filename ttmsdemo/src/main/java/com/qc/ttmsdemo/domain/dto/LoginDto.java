package com.qc.ttmsdemo.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/3/28 20:09
 * @version: 1.0
 */
@Data
@Schema(description = "用户登录dto")
public class LoginDto {
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "密码")
    private String password;
}
