package com.changgou.order.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.order.config.TokenDecode;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenDecode tokenDecode;

    /**
     * 添加购物车
     * @param num 购买的数量
     * @param id  购买的sku的ID
     * @return
     * */
    @RequestMapping("/add")
    public Result add(Integer num, Long id){
        //1、获取用户名
        //获取令牌的值
        String userName = tokenDecode.getUserName();
        cartService.add(num,id,userName);
        return new Result(true, StatusCode.OK,"购买成功");
    }

    @RequestMapping("list")
    public Result<List<OrderItem>> list(){
        //获取登录的用户
        String userName = tokenDecode.getUserName();
        // 根据用户名查询该用户赌赢的购物车列表
        List<OrderItem> cartList = cartService.list(userName);
        return new Result(true, StatusCode.OK,"查询成功",cartList);
    }
}
