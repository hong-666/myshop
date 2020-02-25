package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbUserDao {

     List<TbUser> selectAll();
     void insert(TbUser tbUser);
     void delete(Long id);
     TbUser selectById(Long id);
     void update(TbUser tbUser);
     List<TbUser> selectByUsername(String username);
     TbUser selectByEmail(String email);
     List<TbUser> search(TbUser tbUser);
     void deleteMulti(String[] ids);

}
