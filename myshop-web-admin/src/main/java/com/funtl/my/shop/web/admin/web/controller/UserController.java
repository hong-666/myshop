package com.funtl.my.shop.web.admin.web.controller;


import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;


    /**
     * ModelAttribute会在此类所有RequestMapping方法前执行
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser=null;
        if(id!=null){
            tbUser=tbUserService.selectById(id);
        }else {
            tbUser=new TbUser();
        }

        return tbUser;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);

        return "user_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes,Model model){

        BaseResult baseResult = tbUserService.save(tbUser);
        if(baseResult.getStatus()==200){

            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";

        }

    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model){

        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";

    }


    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
       BaseResult baseResult=null;
       if (StringUtils.isNotBlank(ids)){
           String[] idArray=ids.split(",");
           tbUserService.deleteMulti(idArray);
           baseResult=BaseResult.success("删除用户成功");
       }
       else{
           baseResult=BaseResult.fail("删除用户失败");
       }
        return baseResult;
    }


    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbUser tbUser){
        return "user_detail";
    }
}
