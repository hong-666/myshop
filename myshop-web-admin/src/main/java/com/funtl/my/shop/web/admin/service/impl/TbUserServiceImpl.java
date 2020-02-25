package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.RegexUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult=checkedTbUser(tbUser);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());

            if(tbUser.getId()==null){
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }else{
                tbUserDao.update(tbUser);
            }
            baseResult.setMessage("保存用户信息成功");
        }

        return baseResult;

    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser selectById(Long id) {
       return tbUserDao.selectById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    @Override
    public List<TbUser> selectByusername(String username) {
        return tbUserDao.selectByUsername(username);
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.selectByEmail(email);
        if(tbUser!=null){
            String md5Password= DigestUtils.md5DigestAsHex(password.getBytes());
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }

        return null;
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {

        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    private BaseResult checkedTbUser(TbUser tbUser){
        BaseResult baseResult=BaseResult.success();
        if(StringUtils.isBlank(tbUser.getEmail())){
            baseResult=BaseResult.fail("邮箱不能为空，请重新输入");
        }
        else if(!RegexUtils.checkEmail(tbUser.getEmail())){
            baseResult=BaseResult.fail("邮箱格式不正确");
        }

        else if(StringUtils.isBlank(tbUser.getPassword())){
            baseResult=BaseResult.fail("密码不能为空，请重新输入");
        }else if(StringUtils.isBlank(tbUser.getUsername())){
            baseResult=BaseResult.fail("姓名不能为空，请重新输入");
        }else if(StringUtils.isBlank(tbUser.getPhone())){
            baseResult=BaseResult.fail("手机号不能为空，请重新输入");
        }
        else if(!RegexUtils.checkPhone(tbUser.getPhone())){
            baseResult=BaseResult.fail("手机号格式不正确");
        }

        return baseResult;

    }
}
