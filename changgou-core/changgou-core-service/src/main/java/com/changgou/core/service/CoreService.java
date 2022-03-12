package com.changgou.core.service;

import tk.mybatis.mapper.common.Mapper;

public interface CoreService <T> extends
        DeleteService<T>,
        InsertService<T>,
        PagingService<T>,
        SelectService<T>,
        UpdateService<T> {

}
