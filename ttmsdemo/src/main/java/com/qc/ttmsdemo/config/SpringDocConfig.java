package com.qc.ttmsdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangguoa
 * @description: TODO
 * @date: 2024/4/8 20:25
 * @version: 1.0
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI myOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("TTMS API")
                        .description("TTMS系统接口文档")
                        .version("v1.0.0"));
    }
}
