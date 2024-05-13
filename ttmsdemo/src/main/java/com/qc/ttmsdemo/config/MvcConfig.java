package com.qc.ttmsdemo.config;


import com.qc.ttmsdemo.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/refreshToken", "/swagger-ui", "/swagger/**", "/v3/api-docs/**");
    }

    @Bean
    public AuthInterceptor authInterceptor () {
        return new AuthInterceptor();
    }
}