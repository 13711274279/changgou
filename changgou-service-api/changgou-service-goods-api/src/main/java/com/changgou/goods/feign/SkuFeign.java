package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "goods", path = "/sku")
public interface SkuFeign {

    @GetMapping("/status/{status}")
    public Result<List<Sku>> findByStatus(@PathVariable(name = "status") String status);

    // 根据sku的ID 获取sku数据
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable(name = "id") Object id);
}
