package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){

        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser:tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("LuoZeHong");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setPhone("15888888888");
        tbUser.setEmail("LuoZeHong@qq.com");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.delete(42L);
    }

    @Test
    public void testSelrctById(){
        TbUser tbUser = tbUserService.selectById(43L);
        System.out.println(tbUser.getUsername());
    }


    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.selectById(43L);
        tbUser.setUsername("LuoZeHong");
        tbUserService.update(tbUser);
    }

    @Test
    public void testSelectByUsername(){
        List<TbUser> tbUsers = tbUserService.selectByusername("uni");
        for (TbUser tbuser:tbUsers
             ) {
            System.out.println(tbuser.getUsername());
        }
    }

    @Test
    public void testMd5Password(){
        String mdsPasswor=DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println("123456的明文密码是"+mdsPasswor);
    }
}
