package com.qc.ttmsdemo.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:40
 * @version: 1.0
 */
@Data
@TableName("user")
public class User{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名/账号
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField("head_image")
    private String headImage;

    /**
     * 头像缩略图
     */
    @TableField("head_image_thumb")
    private String headImageThumb;

    /**
     * 个性签名
     */
    @TableField("signature")
    private String signature;

    /**
     * 密码(明文)
     */
    @TableField("password")
    private String password;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

}
