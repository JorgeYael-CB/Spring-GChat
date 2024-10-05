package com.yael.springboot.api.gchat.gchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;



@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {}
