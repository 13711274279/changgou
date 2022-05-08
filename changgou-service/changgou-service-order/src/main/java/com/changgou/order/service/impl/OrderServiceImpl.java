package com.changgou.order.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.order.dao.OrderItemMapper;
import com.changgou.order.dao.OrderMapper;
import com.changgou.order.pojo.Order;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.OrderService;
import com.changgou.user.feign.UserFeign;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/****
 * @Author:admin
 * @Description:Order业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class OrderServiceImpl extends CoreServiceImpl<Order> implements OrderService {

    private OrderMapper orderMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        super(orderMapper, Order.class);
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //本地的Spring声明事务注解
    public void add(Order order) {
        //1、添加数据到订单表和订单选表中
         //1.1生成主键
        order.setId(idWorker.nextId()+"");
        //获取redis中购物车的数据 循环遍历 统计即可
        List<OrderItem> orderItemList = redisTemplate.boundHashOps("Cart_" + order.getUsername()).values();
        Integer totalNum = 0;
        Integer totalMoney = 0;
        for (OrderItem orderItem : orderItemList) {
            // 数量合计 和金额合计
            totalNum = orderItem.getNum();
            // 设置实付金额
            totalMoney += orderItem.getMoney();

            //订单的选项
            orderItem.setId(idWorker.nextId()+"");
            orderItem.setOrderId(order.getId());
            orderItemMapper.insertSelective(orderItem);
            skuFeign.decCount(orderItem.getSkuId(),orderItem.getNum());
        }
        order.setTotalNum(totalNum);
        order.setTotalMoney(totalMoney);
        order.setPayMoney(totalMoney);
        //1.2 数量合计，合计金额 todo
         //1.3 设置值邮费免邮
        order.setPostFee(0);
         //1.4 设置实付金额 todo
         //1.5 设置和创建更新时间
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());

        order.setBuyerRate("0");
        //1.6 设置状态
        order.setPayStatus("0");
        order.setConsignStatus("0");
        order.setOrderStatus("0");
        order.setIsDelete("0");
        orderMapper.insertSelective(order);
        //2、减库存
        // update tb_sku set num = num -#{num} where id=#{id} and num>#{num}
        //3、加积分
        userFeign.addPoints(order.getUsername(), 10);
        //4、清除购物车
    }
}
