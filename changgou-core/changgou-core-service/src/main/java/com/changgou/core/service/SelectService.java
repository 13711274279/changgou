package com.changgou.core.service;

import java.util.List;

public interface
SelectService<T> {
    public List<T> selectAll();

    public T selectByPrimaryKey(Object id);

    public List<T> select(T record);
}
