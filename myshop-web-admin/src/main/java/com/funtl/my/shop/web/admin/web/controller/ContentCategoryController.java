package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "contect/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @ModelAttribute
    public TbContentCategory getTbUContentCategory(Long id) {
        TbContentCategory tbContentCategory=null;
        if(id!=null){
            tbContentCategory=tbContentCategoryService.selectById(id);
        }else {
            tbContentCategory=new TbContentCategory();
        }

        return tbContentCategory;
    }


    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbContentCategory> targetList=new ArrayList<TbContentCategory>();
        List<TbContentCategory> sourceList=tbContentCategoryService.selectAll();
        sortList(sourceList,targetList,0L);

        model.addAttribute("tbContentCategories",targetList);
        return "content_category_list";
    }


    @ResponseBody
    @RequestMapping(value = "tree/data",method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if(id==null){
            id=0L;
        }
        return tbContentCategoryService.selectByPid(id);
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){

        return "content_category_form";
    }


    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes, Model model){
       // model.addAttribute("tbContentCategory",tbContentCategory);

        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);
        if(baseResult.getStatus()==200){

            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/contect/category/list";
        }else {
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";

        }

    }

    private void sortList(List<TbContentCategory> sourceList,List<TbContentCategory> targetList,Long parentId){
        for(TbContentCategory tbContentCategory:sourceList){
            if (tbContentCategory.getParentId().equals(parentId)){
                targetList.add(tbContentCategory);
                //判断有没有子节点，如果有继续追加
                if(tbContentCategory.isParent()){
                    for(TbContentCategory contentCategory:sourceList){
                        if(contentCategory.getParentId().equals(tbContentCategory.getId())){
                            sortList(sourceList,targetList,tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }

    }
}
