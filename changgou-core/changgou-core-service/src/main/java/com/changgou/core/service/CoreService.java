package com.changgou.core.service;


public interface CoreService <T> extends
        DeleteService<T>,
        InsertService<T>,
        PagingService<T>,
        SelectService<T>,
        UpdateService<T> {

}
