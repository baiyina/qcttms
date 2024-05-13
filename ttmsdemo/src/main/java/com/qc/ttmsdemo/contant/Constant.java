package com.qc.ttmsdemo.contant;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/3/27 21:28
 * @version: 1.0
 */
public class Constant {

    // 在线状态过期时间 600s
    public static final long ONLINE_TIMEOUT_SECOND = 600;
    // accessToken 过期时间(1小时)
    public static final Integer ACCESS_TOKEN_EXPIRE = 30 * 60;
    // refreshToken 过期时间(7天)
    public static final Integer REFRESH_TOKEN_EXPIRE = 7 * 24 * 60 * 60 ;
    // accessToken 加密秘钥
    public static final String ACCESS_TOKEN_SECRET = "NIHAODSADSAasasaA";
    // refreshToken 加密秘钥
    public static final String REFRESH_TOKEN_SECRET = "WOIOidbgsahdas";

}