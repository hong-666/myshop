package com.funtl.my.shop.web.api.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao {

    TbUser login(TbUser tbUser);
}
