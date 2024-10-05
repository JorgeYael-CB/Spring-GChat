package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IMailerService;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class MailerService implements IMailerService {

    @Autowired
    private JavaMailSender mailerSender;


    @Async
    @Override
    public void sendEmail(String to, String html) {
        MimeMessage message = mailerSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setText(html, true);
            helper.setSubject("GChat");
        } catch (MessagingException e) {
            throw CustomException.internalServerException("Email not send");
        }

        mailerSender.send(message);
    }

    @Async
    @Override
    public void sendEmail(String to, String subject, String html) {
        MimeMessage message = mailerSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setText(html, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            throw CustomException.internalServerException("Email not send");
        }

        mailerSender.send(message);
    }

}
