package com.eleserv.qrCode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eleserv.qrCode.controller.AppController;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SendEmailServiceAPI {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(AppController.class);

    public ResponseEntity<Map> sendMail(String body, String to, String cc, MultipartFile attachment1,
                                        MultipartFile attachment2, MultipartFile attachment3, MultipartFile attachment4, MultipartFile attachment5,
                                        MultipartFile attachment6, MultipartFile attachment7, MultipartFile attachment8, MultipartFile attachment9,
                                        MultipartFile attachment10, String message) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            System.out.println("Mail Started....");
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
            mimeMessageHelper.setText("", body);
            mimeMessageHelper.setSubject(message);
            String path = env.getProperty("mail.attachment.path");
            File file = null;
            List<MultipartFile> attachment = new ArrayList<>();
            attachment.add(attachment1);
            attachment.add(attachment2);
            attachment.add(attachment3);
            attachment.add(attachment4);
            attachment.add(attachment5);
            attachment.add(attachment6);
            attachment.add(attachment7);
            attachment.add(attachment8);
            attachment.add(attachment9);
            attachment.add(attachment10);
            List<File> files = new ArrayList<>();
            for (MultipartFile uploadedFile : attachment) {
                if (uploadedFile.isEmpty() == false && uploadedFile.getSize() != 0) {
                    file = new File(path + uploadedFile.getOriginalFilename());
                    files.add(file);
                    uploadedFile.transferTo(file);
                    mimeMessageHelper.addAttachment(uploadedFile.getOriginalFilename(), new FileDataSource(file));
                }
            }
            javaMailSender.send(mimeMessage);
            if (files.isEmpty() == false && files.size() != 0) {
                for (File deleteFile : files) {
                    deleteFile.delete();
                }
            }
            System.out.printf("Mail with attachment sent successfully..");
            map.put("status", 200);
            map.put("message", "success");
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error("@sendmail Exception=" + e.getMessage());
            System.out.println("@sendmail Exception=" + e.getMessage());
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }
}
