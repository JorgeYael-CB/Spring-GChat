package com.yael.springboot.api.gchat.gchat.application.interfaces.services;

public interface IMailerService {

    void sendEmail(String to, String html);
    void sendEmail(String to, String subject, String html);

}
