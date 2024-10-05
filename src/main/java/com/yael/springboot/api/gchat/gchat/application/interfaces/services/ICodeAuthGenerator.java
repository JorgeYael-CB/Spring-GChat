package com.yael.springboot.api.gchat.gchat.application.interfaces.services;



public interface ICodeAuthGenerator {

    String getCodeGenerator( int range, String username );
    String getUsernameByCode( String code );
    void validateCodeUsername( String code );

}
