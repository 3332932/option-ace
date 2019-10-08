package com.cn.shiro.exception;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ExceptionScannerRegister.class)
public @interface ExceptionScanner {
    String[] value() default {};
}
