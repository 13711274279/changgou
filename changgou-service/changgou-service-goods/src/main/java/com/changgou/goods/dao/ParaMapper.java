package com.changgou.goods.dao;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:ParaDao
 * @Date 2019/6/14 0:12
 *****/
public interface ParaMapper extends Mapper<Para> {

    @Select(value = "SELECT tc.id, tp.seq, tp.name, tp.options, tc.template_id FROM  tb_category tc JOIN tb_para tp ON tc.template_id = tp.template_id where tc.id = #{id}")
    List<Para> findByCategoryId(Integer id);
}
