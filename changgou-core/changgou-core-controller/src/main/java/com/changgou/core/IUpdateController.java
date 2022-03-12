package com.changgou.core;

import entity.Result;

public interface IUpdateController<T> {

    Result<T> updateByPrimaryKey(T record);
}
