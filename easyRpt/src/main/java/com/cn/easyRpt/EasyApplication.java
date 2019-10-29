package com.cn.easyRpt;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yshh44
 */
@SpringBootApplication
@MapperScan("com.cn.easyRpt.mapper")
@ComponentScan("com.cn.web")
@ComponentScan("com.cn.easyRpt")
public class EasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
