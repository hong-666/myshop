package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {

     List<TbUser> selectAll();
     BaseResult save(TbUser tbUser);
     void delete(Long id);
     TbUser selectById(Long id);
     void update(TbUser tbUser);
     List<TbUser> selectByusername(String username);
     TbUser login(String email,String password);
     List<TbUser> search(TbUser tbUser);
     void deleteMulti(String[] ids);
}
