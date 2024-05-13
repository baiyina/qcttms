package com.qc.ttmsdemo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qc.ttmsdemo.domain.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/13 13:50
 * @version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
