package com.changgou.core;

import entity.Result;

import java.util.List;

public interface ISelectController <T>{
    public Result<T> findById(Object id);

    public Result<List<T>> findAll();
}
