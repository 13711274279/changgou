package com.changgou.goods.dao;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:SkuDao
 * @Date 2019/6/14 0:12
 *****/
public interface SkuMapper extends Mapper<Sku>, InsertListMapper<Sku> {
}
