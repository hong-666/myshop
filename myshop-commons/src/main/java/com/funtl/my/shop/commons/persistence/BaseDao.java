package com.funtl.my.shop.commons.persistence;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    List<T> selectAll();
    void insert(T entity);
    void delete(Long id);
    T selectById(Long id);
    void update(T entity);
    List<T> search(T entity);
    void deleteMulti(String[] ids);
    int count(T entity);
}
