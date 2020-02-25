package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtil;
import com.funtl.my.shop.commons.constant.CookieUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.domain.User;

import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

   // private UserService userService;
   @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){

        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest req, Model model)
    {
        TbUser tbUser = tbUserService.login(email, password);

        if(tbUser==null){
            model.addAttribute("message","用户名或密码错误，请重新输入");
            return login();
        }else{

            req.getSession().setAttribute(ConstantUtil.SESSION_USER,tbUser);
            return "redirect:/main";
        }


/*
        User user = userService.login(email, password);
        boolean isRemember = httpServletRequest.getParameter("isRemember")==null?false:true;

        if (user == null) {

            httpServletRequest.setAttribute("message","用户名或密码错误");
            return login();

        } else {
            if(isRemember){
                //将登陆信息放入会话
                httpServletRequest.getSession().setAttribute(ConstantUtil.SESSION_USER,user);

            }

            //return "redirect:/main";
            return "main";
        }*/

    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){

        httpServletRequest.getSession().invalidate();

        return "login";
    }


}
