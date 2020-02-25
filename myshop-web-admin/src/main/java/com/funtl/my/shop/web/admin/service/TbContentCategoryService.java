package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.ui.Model;
import sun.awt.ModalExclude;

import java.util.List;

public interface TbContentCategoryService {
    TbContentCategory selectById(Long id);
    List<TbContentCategory> selectAll();
    List<TbContentCategory> selectByPid(Long pid);
    BaseResult save(TbContentCategory tbContentCategory);

}
