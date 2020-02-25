package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.utils.RegexUtils;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao tbContentDao;
    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);

        if (validator!=null){
            return BaseResult.fail(validator);
        }else {
            tbContent.setUpdated(new Date());

            if(tbContent.getId()==null){
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);
            }else{
                tbContentDao.update(tbContent);
            }
            return BaseResult.success("保存内容信息成功");

        }


    }

    @Override
    public void delete(Long id) {
        tbContentDao.delete(id);

    }

    @Override
    public TbContent selectById(Long id) {
        return tbContentDao.selectById(id);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);

    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);

    }

    @Override
    public PageInfo<TbContent> page(int start,int length,int draw,TbContent tbContent) {
        int count=tbContentDao.count(tbContent);
        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbContent",tbContent);

        PageInfo<TbContent> pageInfo=new PageInfo<>();
        pageInfo.setDarw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbContentDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

    @Override
    public List<TbContent> search(TbContent tbContent) {
        return tbContentDao.search(tbContent);
    }


}
