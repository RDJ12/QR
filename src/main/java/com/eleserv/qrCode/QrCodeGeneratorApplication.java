package com.eleserv.qrCode;

import com.eleserv.qrCode.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eleserv.qrCode"})
public class QrCodeGeneratorApplication extends SpringBootServletInitializer  {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QrCodeGeneratorApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(QrCodeGeneratorApplication.class, args);
    }

}
