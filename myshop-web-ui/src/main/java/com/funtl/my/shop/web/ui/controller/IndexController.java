package com.funtl.my.shop.web.ui.controller;

import com.funtl.my.shop.commons.utils.HttpClientUtils;
import com.funtl.my.shop.commons.utils.MapperUtils;
import com.funtl.my.shop.web.ui.api.ContentsApi;

import com.funtl.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {


    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(Model model){
       requestContentsPPT(model);
        return "index";
    }

    private void requestContentsPPT(Model model){
        List<TbContent> tbContents = ContentsApi.ppt();
        model.addAttribute("ppt",tbContents);
    }
}
