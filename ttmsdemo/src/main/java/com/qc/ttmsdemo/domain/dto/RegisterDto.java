package com.qc.ttmsdemo.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/3/29 11:32
 * @version: 1.0
 */
@Schema(description = "注册dto")
@Data
public class RegisterDto {
 //   Long userid;
    @Schema(description = "用户名/账号")
    @NotEmpty(message = "用户名不可为空")
    @Length(max = 64,message = "用户名不能大于64字符")
    String userName;

    @Schema(description = "昵称")
    @NotEmpty(message = "昵称不可为空")
    @Length(max = 64,message = "昵称不能大于64字符")
    String nickName;

    @Schema(description = "密码")
    @Length(min = 5, max = 20, message = "密码长度必须在5-20个字符之间")
    @NotEmpty(message = "密码不可为空")
    String password;
}
