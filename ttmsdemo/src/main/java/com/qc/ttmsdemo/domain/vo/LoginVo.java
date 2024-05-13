package com.qc.ttmsdemo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:47
 * @version: 1.0
 */
@Data
@Schema(description = "用户登录VO")
public class LoginVo {
    @Schema(description = "每次请求都必须在header中携带accessToken")
    private String accessToken;

    @Schema(description = "accessToken过期时间")
    private Integer accessTokenExpiresIn;

    @Schema(description = "accessToken过期后，使用refreshToken换取新的token")
    private String refreshToken;

    @Schema(description = "refreshToken过期时间")
    private Integer refreshTokenExpiresIn;

}