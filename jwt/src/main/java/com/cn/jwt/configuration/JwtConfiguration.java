package com.cn.jwt.configuration;

import com.cn.jwt.entity.JwtToken;
import com.cn.jwt.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
    @Autowired
    private JwtProperties properties;
    @Bean
    public JwtToken jwtToken(){
        return new JwtToken(properties);
    }
}
