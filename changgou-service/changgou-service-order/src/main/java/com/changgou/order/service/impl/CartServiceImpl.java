package com.changgou.order.service.impl;

import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Override
    public void add(Integer num, Long id, String username) {
        if (num<=0){
            redisTemplate.boundHashOps("Cart_"+username).delete(id);
            return;
        }
        //2、根据商品的SKUID。获取商品的数据ID
        Sku sku = skuFeign.findById(id).getData();
        OrderItem orderItem = new OrderItem();
        // 设置一级， 二级 三级 分类ID  需要先通过sku的ID 获取到spu的ID 再通过spu的ID 获取spu的数据
        Spu spu = spuFeign.findById(sku.getSpuId()).getData();
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        orderItem.setImage(sku.getImage());
        orderItem.setPrice(sku.getPrice());
        orderItem.setSkuId(id);
        orderItem.setSpuId(sku.getSpuId());
        orderItem.setName(sku.getName());
        orderItem.setNum(num);
        orderItem.setMoney(num * sku.getPrice()); // 应付金额
        orderItem.setPayMoney(num * sku.getPrice()); //实付金额
        //3、将数据存储到redis中(购物车) key:用户名value 数据列表
        redisTemplate.boundHashOps("Cart_"+username).put(id, orderItem); //hset key field value
    }

    @Override
    public List<OrderItem> list(String username) {
        return redisTemplate.boundHashOps("Cart_"+username).values();
    }
}
