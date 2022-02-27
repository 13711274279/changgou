package com.changgou.service.Impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();

    }

    @Override
    public Brand findById(Integer id) {
        // 根据主键查询品牌数据
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        //添加数据到数据库中 insert
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        //更新数据库
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        //删除品牌
        brandMapper.deleteByPrimaryKey(id);
    }


    //根据条件查询
    @Override
    public List<Brand> search(Brand brand) {
        Example examle = new Example(Brand.class);
        if(brand != null){
            Example.Criteria criteria = examle.createCriteria();
            if(!StringUtils.isEmpty(brand.getName())){
                criteria.andLike("name","%" + brand.getName() + "%");
            }
            if(!StringUtils.isEmpty(brand.getFirst_word())){
                criteria.andEqualTo("first_word", brand.getFirst_word());
            }
        }
        List<Brand> brandList = brandMapper.selectByExample(examle);
        return brandList;
    }


}
