package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.ICodeAuthGenerator;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;



@Service
public class CodeGeneratorAuth implements ICodeAuthGenerator {

    private Random randomNumbers = new Random();
    private Map<String, String> usersNotVerify = new HashMap<>();
    private Map<String, String> usersVerify = new HashMap<>();


    @Override
    public String getCodeGenerator(int range, String username) {
        String number = "";

        for( int i = 0; i < range; i ++ ){
            int random = randomNumbers.nextInt(1, 9);
            number += random;
        }

        usersNotVerify.put(number, username);
        return number;
    }

    @Override
    public String getUsernameByCode(String code) {
        String username = usersVerify.get(code);
        System.out.println(username);
        if( username == null ) throw CustomException.badRequestException("Code is not verify or expired.");
        usersVerify.remove(code);
        return username;
    }

    @Override
    public void validateCodeUsername(String code) {
        String username = usersNotVerify.remove(code);
        System.out.println(username);
        if( username == null ) throw CustomException.badRequestException("Code already verify");
        usersVerify.put(code, username);
    }

}
