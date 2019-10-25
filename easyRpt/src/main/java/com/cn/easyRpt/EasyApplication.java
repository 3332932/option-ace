package com.cn.easyRpt;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author yshh44
 */
@SpringBootApplication
@MapperScan("com.cn.easyRpt.mapper")
public class EasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
