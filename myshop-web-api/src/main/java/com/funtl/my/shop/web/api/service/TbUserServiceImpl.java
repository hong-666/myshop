package com.funtl.my.shop.web.api.service;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.api.dao.TbUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl implements TbUserService{
    @Autowired
    private TbUserDao tbUserDao;


    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);
        if (user!=null){
            String password= DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
