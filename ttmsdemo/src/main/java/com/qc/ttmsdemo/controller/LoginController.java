package com.qc.ttmsdemo.controller;


import com.qc.ttmsdemo.domain.dto.LoginDto;
import com.qc.ttmsdemo.domain.dto.RegisterDto;
import com.qc.ttmsdemo.domain.vo.LoginVo;
import com.qc.ttmsdemo.domain.vo.Result;
import com.qc.ttmsdemo.service.IUserService;
import com.qc.ttmsdemo.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:27
 * @version: 1.0
 */
@Tag(name = "login")
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @Operation(summary = "用户登录",description = "用户登录")
    public Result register (@Valid @RequestBody LoginDto loginDto) {
        LoginVo loginVo = userService.login(loginDto);
        log.info("{}尝试登录", loginDto);
        return ResultUtil.success(loginVo);
    }

    @PostMapping("/refreshToken")
    @Operation(summary = "刷新token", description = "使用refreshToken刷新tokens")
    public Result refreshToken(@RequestHeader("refreshToken")String refreshToken) {
        LoginVo vo = userService.refreshToken(refreshToken);
        return ResultUtil.success(vo);
    }

    @PostMapping("/register")
    @Operation(summary = "注册", description = "注册")
    public Result register (@Valid @RequestBody RegisterDto registerdto) {
        userService.register(registerdto);
        return ResultUtil.success();
    }
}
