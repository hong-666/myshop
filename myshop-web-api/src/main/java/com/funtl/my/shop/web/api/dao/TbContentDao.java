package com.funtl.my.shop.web.api.dao;

import com.funtl.my.shop.domain.TbContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentDao {

    List<TbContent> selectByCategoryId(TbContent tbContent);
}
