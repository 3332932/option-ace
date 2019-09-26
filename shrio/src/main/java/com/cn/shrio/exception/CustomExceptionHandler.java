package com.cn.shrio.exception;

import com.cn.jwt.utils.WorkUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yshh44
 */
@Order(1)
@ControllerAdvice(annotations = {Controller.class})
public class CustomExceptionHandler {
    protected  static final List<Class> classes = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> myExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = WorkUtils.getErrorResultMap();
        if (e instanceof UnauthorizedException){
            return WorkUtils.getResultMap("10200","没有权限");
        }else if (e instanceof UnauthenticatedException){
            logger.error("",e);
            return WorkUtils.getResultMap("10105","权限错误，请重新登录");
        }
        else {
            logger.error("",e);
            return WorkUtils.getResultMap("-1",e.getMessage());

        }
    }


}
