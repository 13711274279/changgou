package com.changgou;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void createToken(){
        //1、设置头，默认不需要
        JwtBuilder builder = Jwts.builder()
                //2、设置载荷
                .setId("8888")  //设置唯一编号
                .setSubject("小白")   //设置主题  可以是JSON数据
                .setIssuedAt(new Date())    //设置签发日期
//                .setExpiration(new Date()) //设置有效时间
                //3、设置签名
                .signWith(SignatureAlgorithm.HS256,"itcast"); //设置签名 使用HS256算法，并设置SecretKey(字符串) 构建并返回一个字符串
        // 自定义载荷信息
        Map<String, Object> map = new HashMap<>();
        map.put("key1","vaule1");
        map.put("key2","vaule2");
        builder.addClaims(map);

        System.out.println(builder.compact());
    }

    @Test
    public void parseToken(){
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoi5bCP55m9IiwiaWF0IjoxNjQ3NzY0NzI2LCJrZXkxIjoidmF1bGUxIiwia2V5MiI6InZhdWxlMiJ9.8-F2mt5sN9nwhljaI4Ey8SpkXPnp0de8Ih3w21vbL2c";
        //解析
        JwtParser parser = Jwts.parser();
        // 设置签名的秘钥
        Jws<Claims> itcast = parser.setSigningKey("itcast")
                .parseClaimsJws(s);
        // 获取载荷信息
        Claims body = itcast.getBody();
        //打印
        System.out.println(body);
    }
}
