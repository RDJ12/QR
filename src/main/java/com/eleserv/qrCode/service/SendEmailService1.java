package com.eleserv.qrCode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService1 {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    public String sendMail(String exceptionMessage, String api, String method) {
        try {
            System.out.println("Exception mail started....");
            String messageBody = "<p>Hi team,</p> <br/>" +
                    "<p>Caught exception in API, details are mentioned below:</p>" +
                    "<p><b>API : </b>" + api + "</p>" +
                    "<p><b>Method : </b>" + method + "</p>" +
                    "<p><b>Exception : </b>" + exceptionMessage + "</p> <br/> <br/>" +
                    "Thanks.";
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(env.getProperty("spring.mail.username"));
            //mimeMessageHelper.setTo(env.getProperty("mail1.to"));
            mimeMessageHelper.setTo(env.getProperty("mail1.cc"));

            mimeMessageHelper.setText(messageBody, true);
            mimeMessageHelper.setSubject(env.getProperty("mail1.subject"));
            javaMailSender.send(mimeMessage);
            System.out.printf("Exception mail sent successfully..");
            return "Mail send successfully";
        } catch (Exception e) {
            System.out.println("Exception==" + e.getMessage());
            return "Something went wrong";
        }
    }
}

