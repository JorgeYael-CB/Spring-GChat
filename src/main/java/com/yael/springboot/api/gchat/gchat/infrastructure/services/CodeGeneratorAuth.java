package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.ICodeAuthGenerator;



@Service
public class CodeGeneratorAuth implements ICodeAuthGenerator {

    Random randomNumbers = new Random();

    @Override
    public String getCodeGenerator(int range) {
        String number = "";

        for( int i = 0; i < range; i ++ ){
            int random = randomNumbers.nextInt(1, 9);
            number += random;
        }

        return number;
    }

}
