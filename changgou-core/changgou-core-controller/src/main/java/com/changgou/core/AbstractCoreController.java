package com.changgou.core;

import com.changgou.core.service.CoreService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class AbstractCoreController<T>  implements ICoreController<T> {

    protected final CoreService<T> coreService;

    protected Class<T> tClass;

    @Autowired
    public AbstractCoreController(CoreService<T> coreService, Class<T> tClass){
        this.coreService = coreService;
        this.tClass = tClass;
    }

    @Override
    @DeleteMapping("/{id}")
    public Result<T> deleteById(Object id) {
        coreService.deleteById(id);
        return new Result<T>(true, StatusCode.OK, "删除成功");
    }

    @PostMapping
    @Override
    public Result<T> insert(@RequestBody T record) {
        coreService.insert(record);
        return new Result<T>(true, StatusCode.OK, "添加成功");
    }


    @GetMapping(value = "/search/{page}/{size}")
    @Override
    public Result<PageInfo<T>> findByPage(@PathVariable(name = "page") Integer pageNo,
                                          @PathVariable(name = "size") Integer pageSize) {
        PageInfo<T> pageInfo = coreService.findByPage(pageNo, pageSize);
        return new Result<PageInfo<T>>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    @PostMapping(value = "/search/{page}/{size}")
    @Override
    public Result<PageInfo<T>> findByPage(@PathVariable(name = "page") Integer pageNo,
                                          @PathVariable(name = "size") Integer pageSize,
                                          @RequestBody T record) {
        PageInfo<T> pageInfo = coreService.findByPage(pageNo, pageSize, record);
        return new Result<PageInfo<T>>(true, StatusCode.OK, "条件分页查询成功", pageInfo);
    }

    @GetMapping("/{id}")
    @Override
    public Result<T> finById(Object id) {
        T t = coreService.selectByPrimaryKey(id);
        return new Result<T>(true, StatusCode.OK, "查询单个数据成功", t);
    }

    @PutMapping
    @Override
    public Result<T> updateByPrimaryKey(@RequestBody T record) {
        coreService.updateByPrimaryKey(record);
        return new Result<T>(true, StatusCode.OK, "更新成功");
    }

    @Override
    @GetMapping
    public Result<List<T>> findAll() {
        List<T> list = coreService.selectAll();
        return new Result<List<T>>(true, StatusCode.OK, "查询成功", list);
    }
}
