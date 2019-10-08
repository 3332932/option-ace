package com.cn.api;

import com.cn.jwt.entity.JwtToken;
import com.cn.user.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AceController {
    @Autowired
    private JwtToken jwtToken;

    @GetMapping("test")
    @RequiresPermissions("user:list")
    public Object test(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setLevel("1");
        user.setSalt("2432rfsfisdjfkldsjgoiremgrehjg");
        String token = jwtToken.createToken(user);
        User currentUser = jwtToken.getInfoByToken(token, User.class);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",currentUser);
        return map;

    }

}
