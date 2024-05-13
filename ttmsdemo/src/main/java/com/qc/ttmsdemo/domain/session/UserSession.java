package com.qc.ttmsdemo.domain.session;

import lombok.Data;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:53
 * @version: 1.0
 */
@Data
public class UserSession {
    private Long id;
    private String userName;
    private String nickName;
}
