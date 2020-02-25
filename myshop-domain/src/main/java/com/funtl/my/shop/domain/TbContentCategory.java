package com.funtl.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
public class TbContentCategory extends BaseEntity {
    private Long parentId;

    @Length(min = 1,max = 20,message = "分类名称必须在1-20个字符之间")
    private String name;
    private Integer status;

    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    @JsonProperty(value="isParent")
    private boolean isParent;

    private TbContentCategory parentCategory;


}
