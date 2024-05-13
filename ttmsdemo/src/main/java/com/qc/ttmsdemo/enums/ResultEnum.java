package com.qc.ttmsdemo.enums;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:38
 * @version: 1.0
 */
public enum ResultEnum {
    SUCCESS(200,"成功"),
    NO_LOGIN(400,"未登录"),
    TOKEN_ERROR(401,"token已失效"),
    PROGRAM_ERROR(500,"系统繁忙，请稍后再试"),
    PASSWORD_ERROR(10001,"密码不正确"),
    REGISTER_ERROR(10003,"该用户名已注册"),
    ;
    private int type;
    private String mean;

    ResultEnum(int type, String mean) {
        this.type = type;
        this.mean = mean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
