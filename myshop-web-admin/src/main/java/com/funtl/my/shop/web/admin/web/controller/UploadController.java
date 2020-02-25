package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {


    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        System.out.println(dropFile.getOriginalFilename());
        String fileName=dropFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        //System.out.println(request.getSession().getServletContext().getRealPath("/"));
        String filePath="E:/单体应用myshop/myshop/myshop-web-admin/src/main/webapp/static/upload";
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            dropFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("fileName",file.getName());
        return result;
    }
}
