package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {

    List<TbContent> selectAll();
    void insert(TbContent tbContent);
    void delete(Long id);
    TbContent selectById(Long id);
    void update(TbContent tbContent);
    void deleteMulti(String[] ids);
    List<TbContent> page(Map<String,Object> params);
    int count(TbContent tbContent);
    List<TbContent> search(TbContent tbContent);
}
