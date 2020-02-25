package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;
import java.util.Map;

public interface TbContentService {

    List<TbContent> selectAll();
    BaseResult save(TbContent tbContent);
    void delete(Long id);
    TbContent selectById(Long id);
    void update(TbContent tbContent);
    void deleteMulti(String[] ids);
    PageInfo<TbContent> page(int start,int length,int draw,TbContent tbContent);
    int count(TbContent tbContent);
    List<TbContent> search(TbContent tbContent);
}
