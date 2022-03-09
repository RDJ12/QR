package com.eleserv.qrCode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eleserv.qrCode.controller.AppController;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendEmailServiceAPI {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;

	Logger logger = LoggerFactory.getLogger(AppController.class);

	public String sendMail(String body, String to, String cc, List<MultipartFile> attachment, String message) {
		String status = "";
		try {
			logger.info("@Start sendmail");
			System.out.println("Mail Started....");
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(env.getProperty("spring.mail.username"));
			mimeMessageHelper.setTo(InternetAddress.parse(to));
			if (!cc.isEmpty()) {
				mimeMessageHelper.setCc(InternetAddress.parse(cc));
			}
			mimeMessageHelper.setText("", body);
			mimeMessageHelper.setSubject(message);
			String path = env.getProperty("mail.attachment.path");
			File file = null;
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
			status = "success";
		} catch (Exception e) {
			logger.error("Exception=" + e.getMessage());
			System.out.println("Exception==" + e.getMessage());
		}
		return status;
	}
}
