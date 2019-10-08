package com.cn.shiro.exception;

import com.cn.base.utils.ResultMapUtils;
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
        if (e instanceof UnauthorizedException){
            return ResultMapUtils.getResultMap("10200","没有权限");
        }else if (e instanceof UnauthenticatedException){
            logger.error("",e);
            return ResultMapUtils.getResultMap("10105","权限错误，请重新登录");
        }
        else {
            logger.error("",e);
            return ResultMapUtils.getResultMap("-1",e.getMessage());

        }
    }


}
