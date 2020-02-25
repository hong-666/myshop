package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;


    /**
     * ModelAttribute会在此类所有RequestMapping方法前执行
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContent getTbUContent(Long id) {
        TbContent tbContent=null;
        if(id!=null){
            tbContent=tbContentService.selectById(id);
        }else {
            tbContent=new TbContent();
        }

        return tbContent;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbContent> tbContents = tbContentService.selectAll();
        model.addAttribute("tbContents",tbContents);

        return "content_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){

        BaseResult baseResult = tbContentService.save(tbContent);
        if(baseResult.getStatus()==200){

            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";

        }

    }




    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if (StringUtils.isNotBlank(ids)){
            String[] idArray=ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult=BaseResult.success("删除内容成功");
        }
        else{
            baseResult=BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }

    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){
        String strDraw=request.getParameter("draw");
        String strStart=request.getParameter("start");
        String strLength=request.getParameter("length");

        int draw=strDraw==null?0:Integer.parseInt(strDraw);
        int start=strStart==null?0:Integer.parseInt(strStart);
        int length=strLength==null?10:Integer.parseInt(strLength);

        PageInfo<TbContent> pageInfo=tbContentService.page(start,length,draw,tbContent);
        return pageInfo;
    }


    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(TbContent tbContent,Model model){

        List<TbContent> tbContents = tbContentService.search(tbContent);
        model.addAttribute("tbContents",tbContents);
        return "content_list";

    }

}
