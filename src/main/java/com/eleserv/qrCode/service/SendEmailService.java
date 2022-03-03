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
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;
    public void sendMail(String body){
        try{
            System.out.println("Mail Started....");
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(env.getProperty("spring.mail.username"));
            mimeMessageHelper.setTo(env.getProperty("mail.to"));
            mimeMessageHelper.setCc(env.getProperty("mail.cc"));
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(env.getProperty("mail.subject"));
            String dest = env.getProperty("app.dynamicpicture")+"nextbatch.pdf";
            FileSystemResource fileSystemResource=
                    new FileSystemResource(new File(dest));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
                    fileSystemResource);
            javaMailSender.send(mimeMessage);
            System.out.printf("Mail with attachment sent successfully..");
    }catch (Exception e)
        {
        System.out.println("Exception=="+e.getMessage());
    }
    }
}

