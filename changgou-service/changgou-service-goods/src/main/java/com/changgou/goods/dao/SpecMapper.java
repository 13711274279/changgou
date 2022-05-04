package com.changgou.goods.dao;
import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:SpecDao
 * @Date 2019/6/14 0:12
 *****/
public interface SpecMapper extends Mapper<Spec> {
    @Select(value = "SELECT * FROM  tb_category tc JOIN tb_spec ts ON ts.template_id = tc.template_id where tc.id = #{id}")
    List<Spec> findByCategoryId(Integer id);
}
