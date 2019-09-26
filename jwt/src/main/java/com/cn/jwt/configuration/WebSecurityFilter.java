package com.cn.jwt.configuration;

import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cn.jwt.entity.JwtToken;
import com.cn.jwt.entity.User;
import com.cn.jwt.utils.ThreadLocals;
import com.cn.jwt.utils.WorkUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yshh44
 */
@Configuration
public class WebSecurityFilter implements WebMvcConfigurer {
    @Autowired
    private JwtToken jwtToken;
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除的路径
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/findByAdminAndPassword");
        //拦截所有路径
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

            List<String> list= new ArrayList<>();
            list.add("/test");
            if (list.contains(request.getRequestURI())){
                return true;
            }
            String token = request.getHeader("X-Token");
            if (StringUtils.isEmpty(token)){
                token = request.getHeader("token");
            }
            try {
                if (StringUtils.isNoneBlank(token)) {
                    User user = jwtToken.getInfoByToken(token, User.class);
                    ThreadLocals.setCurrentUser(user);
                    if (user == null) {
                        writeOut(response,WorkUtils.getResultMap("10100","token解析错误"));
                        return false;
                    }
                    return true;
                }else {
                    //跳转到登录页
                    writeOut(response,WorkUtils.getResultMap("10101","token不存在,请在header里加token或X-Token"));
                    return false;
                }
            }catch (TokenExpiredException e){
                writeOut(response,WorkUtils.getResultMap("10102","token失效"));
                return false;
            }catch (JWTDecodeException e){
                writeOut(response,WorkUtils.getResultMap("10103","token异常"));
                return false;
            }
            catch (Exception e) {
                e.printStackTrace();
                writeOut(response,WorkUtils.getResultMap("10104","登录异常"));
                return false;
            }

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            ThreadLocals.setCurrentUser(null);
            super.afterCompletion(request, response, handler, ex);
        }

        public void writeOut(HttpServletResponse response,Map<String,Object> map) throws IOException {
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            Object toJSON = JSONArray.toJSON(map);
            response.getWriter().print(toJSON);
        }
    }



}
