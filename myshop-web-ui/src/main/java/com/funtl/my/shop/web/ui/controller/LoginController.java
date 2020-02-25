package com.funtl.my.shop.web.ui.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.EmailSendUtils;
import com.funtl.my.shop.web.ui.api.UsersApi;
import com.funtl.my.shop.web.ui.constants.SystemConstants;
import com.funtl.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.awt.ModalExclude;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private EmailSendUtils emailSendUtils;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        if (!checkVerification(tbUser,request)){
            model.addAttribute("baseResult",BaseResult.fail("验证码错误，请重新输入"));
            return "login";
        }


        TbUser user = UsersApi.login(tbUser);
        if (user==null){
            model.addAttribute("baseResult",BaseResult.fail("用户或密码错误，请重新输入"));
            return "login";
        }else {
            emailSendUtils.send("用户登陆",String.format("用户【%s】登陆MyShop",user.getUsername()),"2959232344@qq.com");
            request.getSession().setAttribute(SystemConstants.SESSION_USER_KEY,user);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){

        request.getSession().invalidate();
        return "redirect:/index";

    }

    private boolean checkVerification(TbUser tbUser,HttpServletRequest request){
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(verification,tbUser.getVerification())){
            return true;
        }else {
            return false;
        }
    }
}
