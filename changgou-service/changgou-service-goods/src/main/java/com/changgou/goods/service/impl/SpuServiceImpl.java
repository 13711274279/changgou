package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:Spu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SpuServiceImpl extends CoreServiceImpl<Spu> implements SpuService {

    private SpuMapper spuMapper;
    
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    public SpuServiceImpl(SpuMapper spuMapper) {
        super(spuMapper, Spu.class);
        this.spuMapper = spuMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Goods goods) {
        // 获取页面传递过来的spu数据
        Spu spu = goods.getSpu();

        // 生成主键ID
        long id = idWorker.nextId();
        spu.setId(id);
        spuMapper.insert(spu);
        //获取页面传过来sku列表的数据
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            long skuId = idWorker.nextId();
            sku.setId(skuId);
            //skuname要求：将spu的name + 空格 + 规格选项值 例如：商品规格：颜色 内存容量 华安伟mate40
            String spec = sku.getSpec();
            Map<String, String> map = JSON.parseObject(spec, Map.class);
            String name = spu.getName();
            for(Map.Entry<String, String> stringStringEntry:map.entrySet()){
                String value = stringStringEntry.getValue();
                name += " " + value;
            }
            sku.setName(name);
            sku.setCreateTime(new Date());
            sku.setSpuId(spu.getId());
            Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
            if (category !=null){
                sku.setCategoryId(category.getId());
                sku.setCategoryName(category.getName());
            }
//            skuMapper.insertSelective(sku);
        }
        System.out.println(skuList);
        skuMapper.insertList(skuList);
    }
}
