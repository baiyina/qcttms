package com.qc.ttmsdemo.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qc.ttmsdemo.enums.FilmEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/5/14 21:15
 * @version: 1.0
 */
@Data
@TableName("Film")
public class Film {
    Long id;
    String name;
    FilmEnum type;
    Date 
}
