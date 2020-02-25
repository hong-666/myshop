package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public TbContentCategory selectById(Long id) {
        return tbContentCategoryDao.selectById(id);
    }

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return tbContentCategoryDao.selectByPid(pid);
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        } else {
            TbContentCategory parentCategory = entity.getParentCategory();
            entity.setUpdated(new Date());
            if (parentCategory == null || parentCategory.getId() == null) {
                //parentCategory.setId(0L);
                entity.setParent(true);
                entity.setParentId(0L);
                entity.setCreated(new Date());
                tbContentCategoryDao.insert(entity);
            } else {
                if (entity.getId() != null) {
                    //entity.setId(entity.getId());
                    entity.setParentId(parentCategory.getId());
                    entity.setCreated(new Date());
                    tbContentCategoryDao.update(entity);
                }else {
                    entity.setParentId(parentCategory.getId());
                    entity.setParent(true);
                    entity.setCreated(new Date());
                    tbContentCategoryDao.insert(entity);
                }

            }

            return BaseResult.success("保存信息成功");
        }

    }




    private BaseResult checkedTbContentCategoory(TbContentCategory tbContentCategory){
        BaseResult baseResult=BaseResult.success();
        if(tbContentCategory.getId()==null){
            baseResult=BaseResult.fail("内容不能为空，请重新输入");
        }


        return baseResult;

    }
}
