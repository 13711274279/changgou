package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    // 添加购物车，给指定用户添加指定商品以及到购车中
    void add(Integer integer, Long id, String username);

    List<OrderItem> list(String username);
}
