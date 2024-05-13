package com.qc.ttmsdemo.interceptor;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.qc.ttmsdemo.contant.Constant;
import com.qc.ttmsdemo.domain.session.UserSession;
import com.qc.ttmsdemo.enums.ResultEnum;
import com.qc.ttmsdemo.util.JwtUtil;
import com.qc.ttmsdemo.util.exception.GlobalException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/4/10 20:46
 * @version: 1.0
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String accessToken = request.getHeader("accessToken");
        if (accessToken == null) {
            log.info("未登录 url: {}", request.getRequestURI());
            throw new GlobalException(ResultEnum.NO_LOGIN);
        }

        try {
            JwtUtil.checkSign(accessToken, Constant.ACCESS_TOKEN_SECRET);
        } catch (JWTVerificationException e) {
            log.info("token已失效，url: {}", request.getRequestURI());
            throw new GlobalException(ResultEnum.TOKEN_ERROR);
        }

        String strJson = JwtUtil.getInfo(accessToken);
        UserSession userSession = JSONUtil.toBean(strJson, UserSession.class);
        request.setAttribute("session", userSession);

        return true;
    }
}
