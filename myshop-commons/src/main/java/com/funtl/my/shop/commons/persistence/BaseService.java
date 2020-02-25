package com.funtl.my.shop.commons.persistence;

import com.funtl.my.shop.commons.dto.BaseResult;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    List<T> selectAll();
    BaseResult save(T entity);
    void delete(Long id);
    void insert(T entity);
    T selectById(Long id);
    void update(T entity);
    List<T> search(T entity);
    void deleteMulti(String[] ids);
    int count(T entity);
}
