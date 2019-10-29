package com.cn.web.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
 
    @Autowired
    private WrapperArgumentResolver wrapperArgumentResolver;
    @Autowired
    private PageMethodArgumentResolver pageMethodArgumentResolver;

 
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(wrapperArgumentResolver);
        argumentResolvers.add(pageMethodArgumentResolver);
    }
}