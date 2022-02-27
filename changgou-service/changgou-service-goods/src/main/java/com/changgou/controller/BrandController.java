package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.Impl.BrandServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

    //查询所有品牌数据
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> all = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询成功",all);
    }

    //根据品牌ID 获取品牌的数据
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(name = "id") Integer id){
        Brand byId = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK,"查询成功",byId);
    }

    //添加品牌信息
    @PostMapping("/add")
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"成功");
    }

    //更新商品
    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"成功");
    }

    //删除商品
    @DeleteMapping
    public Result delete(@RequestBody Map<String, Integer> Id ){
        brandService.delete(Id.get("id"));
        return new Result(true,StatusCode.OK,"成功");
    }

    // 查询商品
    @PostMapping("search")
    public Result<List<Brand>> search(@RequestBody(required = false) Brand brand){
        List<Brand> brandList = brandService.search(brand);
        return new Result(true,StatusCode.OK,"成功", brandList);
    }

}
