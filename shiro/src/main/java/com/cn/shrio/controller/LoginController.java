package com.cn.shrio.controller;

import com.cn.base.utils.ResultMapUtils;
import com.cn.jwt.entity.JwtToken;
import com.cn.user.utils.ThreadLocals;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yshh44
 */
@RestController
public class LoginController {
    @Autowired
    private JwtToken jwtToken;
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken shiroToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            // 执行认证登陆
            subject.login(shiroToken);
            User currentUser = ThreadLocals.getCurrentUser();
            currentUser.setPassword(null);
            subject.isPermitted("*");
            Map<String, Object> resultDataMap = ResultMapUtils.getResultDataMap(currentUser);
            String token = jwtToken.createToken(currentUser);
            resultDataMap.put("token",token);
            return resultDataMap;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            ThreadLocals.setCurrentUser(null);
            Map<String, Object> resultDataMap = ResultMapUtils.getResultMap("10100",e.getMessage());
            return resultDataMap;
        }
    }
    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("md5","admin".getBytes(),"c3284d0f94606de1fd2af172aba15bf3",2);
        System.out.println(simpleHash);
    }


}
