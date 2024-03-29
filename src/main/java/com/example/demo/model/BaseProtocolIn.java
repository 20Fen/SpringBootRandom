package com.example.demo.model;

import com.example.demo.exception.CustomException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Description:基本入参协议，继承后通过方法进行参数校验
 */
public class BaseProtocolIn {

    public void validate(BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                throw new CustomException(fieldError.getDefaultMessage());
            });
        }
    }
}
