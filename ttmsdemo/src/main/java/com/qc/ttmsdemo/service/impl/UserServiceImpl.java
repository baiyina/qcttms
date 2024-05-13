package com.qc.ttmsdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qc.ttmsdemo.contant.Constant;
import com.qc.ttmsdemo.domain.dto.LoginDto;
import com.qc.ttmsdemo.domain.dto.RegisterDto;
import com.qc.ttmsdemo.domain.po.User;
import com.qc.ttmsdemo.domain.session.UserSession;
import com.qc.ttmsdemo.domain.vo.LoginVo;
import com.qc.ttmsdemo.enums.ResultEnum;
import com.qc.ttmsdemo.mapper.UserMapper;
import com.qc.ttmsdemo.service.IUserService;
import com.qc.ttmsdemo.util.JwtUtil;
import com.qc.ttmsdemo.util.exception.GlobalException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:29
 * @version: 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        User user = findUserByName(loginDto.getUserName());
        if (null == user) {
            throw new GlobalException(ResultEnum.PROGRAM_ERROR, "用户不存在");
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new GlobalException(ResultEnum.PASSWORD_ERROR);
        }
        //生成Token
        UserSession session = BeanUtil.copyProperties(user, UserSession.class);
        String strJson = JSONUtil.toJsonStr(session);
        String accessToken = JwtUtil.sign(user.getId(), strJson, Constant.ACCESS_TOKEN_EXPIRE, Constant.ACCESS_TOKEN_SECRET);
        String refreshToken = JwtUtil.sign(user.getId(), strJson, Constant.REFRESH_TOKEN_EXPIRE, Constant.REFRESH_TOKEN_SECRET);
        LoginVo vo = new LoginVo();
        vo.setAccessToken(accessToken);
        vo.setAccessTokenExpiresIn(Constant.ACCESS_TOKEN_EXPIRE);
        vo.setRefreshToken(refreshToken);
        vo.setRefreshTokenExpiresIn(Constant.REFRESH_TOKEN_EXPIRE);
        return null;
    }

    @Override
    public User findUserByName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName, userName);
        return this.getOne(queryWrapper);
    }

    @Override
    public LoginVo refreshToken(String refreshToken) {
        try{
            JwtUtil.checkSign(refreshToken, Constant.REFRESH_TOKEN_SECRET);
            String strJson = JwtUtil.getInfo(refreshToken);
            Long userId = JwtUtil.getUserId(refreshToken);
            String accessToken = JwtUtil.sign(userId, strJson, Constant.ACCESS_TOKEN_EXPIRE, Constant.REFRESH_TOKEN_SECRET);
            String newRefreshToken = JwtUtil.sign(userId, strJson, Constant.REFRESH_TOKEN_EXPIRE, Constant.REFRESH_TOKEN_SECRET);
            LoginVo loginVo = new LoginVo();
            loginVo.setRefreshTokenExpiresIn(Constant.REFRESH_TOKEN_EXPIRE);
            loginVo.setAccessTokenExpiresIn(Constant.ACCESS_TOKEN_EXPIRE);
            loginVo.setAccessToken(accessToken);
            loginVo.setRefreshToken(newRefreshToken);
            return loginVo;
        } catch (JWTVerificationException e) {
            throw new GlobalException("refreshToken已失效");
        }
    }

    @Override
    public void register(RegisterDto registerdto) {
        User user = findUserByName(registerdto.getUserName());
        if (null != user) {
            throw new GlobalException(ResultEnum.REGISTER_ERROR);
        }
        user = BeanUtil.copyProperties(registerdto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedTime(new Date());
        this.save(user);
        log.info("注册用户，用户id：{}，用户名：{}， 昵称：{}", user.getId(), user.getUserName(), user.getNickName());
    }
}
