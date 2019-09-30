package com.cn.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@ConfigurationProperties(
        prefix = "spring.jwt",
        ignoreUnknownFields = true
)
@Getter
@Setter
public class JwtProperties {

    private String secret = "pWDt0Cx#9l8h8eS9%xQ*zBEHMY6M3BIgA&1PfR0VDgkUFIq$aWmnjOKJAZTcvUD3uILZj1uatw^W1Fz$XAlhcG75dRYWlVcZ%ie";
    /**
     * token类型
     */
    private String algorithm = "alg";
    private String algorithmVal = "JWT";
    /**
     * 加密算法
     */
    private String type = "typ";
    private String typeVal = "HS256";
    /**
     * 有效期
     */
    private Integer timeOutUnit = Calendar.MINUTE;
    private Integer timeOutVal = 30;
}
