package com.yael.springboot.api.gchat.gchat.application.interfaces.services;




public interface IValidateClassService {

    <T> Boolean fieldsEmptyClass( Class<T> typeClass, T clase );

}
