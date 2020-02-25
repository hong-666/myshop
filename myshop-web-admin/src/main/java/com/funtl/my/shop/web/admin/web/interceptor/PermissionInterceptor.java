package com.funtl.my.shop.web.admin.web.interceptor;

import com.funtl.my.shop.commons.constant.ConstantUtil;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.domain.User;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于登陆后将请求返回首页
 */
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null&&modelAndView.getViewName()!=null&&modelAndView.getViewName().endsWith("login")){
            TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtil.SESSION_USER);

            if(tbUser!=null){
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
