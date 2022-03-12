package com.changgou.core;

import entity.Result;

import java.util.List;

public interface ISelectController <T>{
    public Result<T> finById(Object id);

    public Result<List<T>> findAll();
}
