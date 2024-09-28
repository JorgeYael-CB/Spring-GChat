package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IValidateClassService;




@Service
public class ValidateClassService implements IValidateClassService {

    @Override
    public <T> Boolean fieldsEmptyClass(Class<T> typeClass, T clase) {
        Method[] methods = typeClass.getDeclaredMethods();

        for (Method meth : methods) {
            if( meth.getName().startsWith("get") ){
                try {
                    Object value = meth.invoke(clase);
                    if( value != null ){
                        return false;
                    }
                } catch (Exception e) {}
            }
        }

        return true;
    }



}
