package com.funtl.my.shop.web.api.service;

import com.funtl.my.shop.domain.TbContent;


import java.util.List;


public interface TbContentService {


    List<TbContent> selectByCategoryId(Long categoryId);
}
