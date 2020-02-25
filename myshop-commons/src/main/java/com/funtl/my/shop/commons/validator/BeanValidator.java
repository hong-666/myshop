package com.funtl.my.shop.commons.validator;

import org.springframework.asm.SpringAsmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.util.*;

public class BeanValidator{

    private static Validator validator;

    public static void setValidator(Validator validator){
        BeanValidator.validator=validator;
    }
    private static void validateWithException(Validator validator,Object object,Class<?>...groups)throws ConstraintViolationException{
        Set constrainViolations=validator.validate(object,groups);
        if(!constrainViolations.isEmpty()){
            throw new ConstraintViolationException(constrainViolations);
        }
    }
    private static List<String> extracMessage(ConstraintViolationException e){
        return extracMessage(e.getConstraintViolations());
    }

    private static List<String> extracMessage(Set<? extends ConstraintViolation> constraintViolations){
        List<String> errorMessages=new ArrayList<>();
        for(ConstraintViolation violation:constraintViolations){
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }
    private static Map<String,String> extractPropertyAndMessage(ConstraintViolationException e){
        return extractPropertyAndMessage(e.getConstraintViolations());
    }
    private static Map<String,String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations){
        Map<String,String> errorMessages=new HashMap<>();
        for (ConstraintViolation violation:constraintViolations){
            errorMessages.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
        return errorMessages;
    }

    private static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations){
        return extractPropertyAndMessageAsList(constraintViolations," ");
    }

    private static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e,String separator){
        return extractPropertyAndMessageAsList(e.getConstraintViolations(),separator);
    }
    private static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,String separator){
        List<String> errorMessages=new ArrayList<>();
        for (ConstraintViolation violation:constraintViolations){
            errorMessages.add(violation.getPropertyPath()+separator+violation.getMessage());
        }
        return errorMessages;
    }
    public static String validator(Object object,Class<?>...groups){
        try {
            validateWithException(validator,object,groups);
        }catch (ConstraintViolationException ex){
            List<String> list=extracMessage(ex);
            list.add(0,"数据验证失败：");
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<list.size();i++){
                String exMsg=list.get(i);
                if (i!=0){
                    sb.append(String.format("%s. %s",i,exMsg)).append(list.size()>1?"</br>":"");

                }else {
                    sb.append(exMsg).append(list.size()>1?"</br>":"");
                }
            }
            return sb.toString();
        }
        return null;
    }



}
