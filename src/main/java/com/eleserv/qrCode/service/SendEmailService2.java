package com.eleserv.qrCode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

@Service
public class SendEmailService2 {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    public ResponseEntity<Map> sendMail(String message, String body, String cc, String to, List<MultipartFile> attachment) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(env.getProperty("spring.mail.host1"));
            javaMailSender.setUsername(env.getProperty("spring.mail.username1"));
            javaMailSender.setPassword(env.getProperty("spring.mail.password1"));

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(env.getProperty("spring.mail.username1"));
            mimeMessageHelper.setTo(InternetAddress.parse(to));



            if (!cc.isEmpty()) {
                mimeMessageHelper.setCc(InternetAddress.parse(cc));
            }

            String path = env.getProperty("mail.attachment.path");
            File file = null;
            List<File> files = new ArrayList<>();

            if (attachment != null) {
                for (MultipartFile uploadedFile : attachment) {
                    if (uploadedFile.isEmpty() == false && uploadedFile.getSize() != 0) {
                        file = new File(path + uploadedFile.getOriginalFilename());
                        files.add(file);
                        uploadedFile.transferTo(file);
                        mimeMessageHelper.addAttachment(uploadedFile.getOriginalFilename(), new FileDataSource(file));
                    }
                }
            }

            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(message);

            System.out.println("Host : "+javaMailSender.getHost());
            System.out.println("Username : "+javaMailSender.getUsername());
            System.out.println("Password : "+javaMailSender.getPassword());

            javaMailSender.send(mimeMessage);

            if (files.isEmpty() == false && files.size() != 0) {
                for (File deleteFile : files) {
                    deleteFile.delete();
                }
            }

            map.put("response", 200);
            map.put("message", "Mail send successfully");
            status = HttpStatus.OK;



        } catch (Exception e) {
            System.out.println("Exception==" + e.getMessage());
            map.put("response", 500);
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }
}
