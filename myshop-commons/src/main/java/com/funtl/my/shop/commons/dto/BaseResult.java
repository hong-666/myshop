package com.funtl.my.shop.commons.dto;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.Serializable;

public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS=200;
    public static final int STATUS_FAIL=500;
    private int status;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResult success(String message,Object data){
        return BaseResult.createBaseResult(STATUS_SUCCESS,message,data);

    }

    public static BaseResult success(){
        return BaseResult.createBaseResult(STATUS_SUCCESS,"成功",null);
    }
    public static BaseResult success(String message){
        return BaseResult.createBaseResult(STATUS_SUCCESS,message,null);
    }

    public static BaseResult fail(){
        return BaseResult.createBaseResult(STATUS_FAIL,"失败",null);
    }
    public static BaseResult fail(String message){
        return BaseResult.createBaseResult(STATUS_FAIL,message,null);
    }
    public static BaseResult fail(int status,String message){
        return BaseResult.createBaseResult(status,message,null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static BaseResult createBaseResult(int status,String message,Object data){
        BaseResult baseResult=new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }
}
