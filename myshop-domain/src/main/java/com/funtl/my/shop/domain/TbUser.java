package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class TbUser extends BaseEntity {

    private String username;
    private String password;
    private String phone;
    private String email;


}
