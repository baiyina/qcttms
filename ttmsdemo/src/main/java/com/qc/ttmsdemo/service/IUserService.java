package com.qc.ttmsdemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qc.ttmsdemo.domain.dto.LoginDto;
import com.qc.ttmsdemo.domain.dto.RegisterDto;
import com.qc.ttmsdemo.domain.po.User;
import com.qc.ttmsdemo.domain.vo.LoginVo;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:28
 * @version: 1.0
 */
public interface IUserService extends IService<User> {
    LoginVo login(LoginDto loginDto);

    User findUserByName(String userName);

    LoginVo refreshToken(String refreshToken);

    void register(RegisterDto registerdto);
}
