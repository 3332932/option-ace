package com.cn.jwt.entity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cn.base.annotation.JwtConfig;
import com.cn.jwt.properties.JwtProperties;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yshh44
 */
public class JwtToken {

private JwtProperties properties;

    public JwtToken(JwtProperties properties) {
        this.properties=properties;
    }

    public JwtToken() {
        if (properties==null){
            properties= new JwtProperties();
        }
    }

    public String createToken(Object obj) {
        Calendar nowTime = Calendar.getInstance();
        Date iatDate = nowTime.getTime();
        nowTime.add(properties.getTimeOutUnit(), properties.getTimeOutVal());
        Date expireDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>(16);
        map.put(properties.getAlgorithm(), properties.getAlgorithmVal());
        map.put(properties.getType(), properties.getTypeVal());
        JWTCreator.Builder builder = JWT.create().withHeader(map).withExpiresAt(expireDate).withIssuedAt(iatDate);
        Class userClass = obj.getClass();
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(JwtConfig.class);
            if (annotationPresent) {
                String name = field.getName();
                Object o = null;
                try {
                    field.setAccessible(true);
                    o = field.get(obj) == null ? "" : field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                String value = String.valueOf(o);
                builder.withClaim(name, value);
            }
        }


        return builder.sign(Algorithm.HMAC256(properties.getSecret()));
    }

    public <T> T getInfoByToken(String token, Class clazz) {
        Map<String, Claim> stringClaimMap = verifyToken(token);
        Field[] declaredFields = clazz.getDeclaredFields();
        try {
            Object o = clazz.newInstance();
            for (Field field : declaredFields) {
                boolean annotationPresent = field.isAnnotationPresent(JwtConfig.class);
                if (annotationPresent) {
                    String name = field.getName();
                    Claim claim = stringClaimMap.get(name);
                    field.setAccessible(true);
                    Object value = getValue(field, claim);
                    field.set(o, value);
                }
            }
            return (T) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(properties.getSecret())).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    private Object getValue(Field field, Claim claim) {
        Object value = null;
        if (field.getType().getName().equals("java.lang.String")) {
            value = claim.asString();
        } else {
            if (StringUtils.isEmpty(claim.asString())) {
                value = null;
            } else if (field.getType().getName().equals("java.lang.Long")) {
                value = Long.valueOf(claim.asString());
            } else if (field.getType().getName().equals("java.lang.Double")) {
                value = new BigDecimal(claim.asString()).doubleValue();
            } else if (field.getType().getName().equals("java.lang.Integer")) {
                value = Integer.valueOf(claim.asString());
            } else if (field.getType().getName().equals("java.util.Date")) {
                value = claim.asDate();
            } else if (field.getType().getName().equals("java.util.Map")) {
                value = claim.asMap();
            }
        }

        return value;
    }

}
