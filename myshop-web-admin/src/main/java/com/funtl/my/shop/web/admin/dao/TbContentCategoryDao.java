package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {
    TbContentCategory selectById(Long id);
    List<TbContentCategory> selectAll();

    List<TbContentCategory> selectByPid(Long pid);

    void insert(TbContentCategory tbContentCategory);
    void update(TbContentCategory tbContentCategory);
}
