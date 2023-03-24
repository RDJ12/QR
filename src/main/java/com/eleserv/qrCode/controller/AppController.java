package com.eleserv.qrCode.controller;

import com.eleserv.qrCode.entity.Dispatche;
import com.eleserv.qrCode.entity.Leads;
import com.eleserv.qrCode.entity.QRCode;
import com.eleserv.qrCode.service.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class AppController {
    @Autowired
    private Environment env;
    @Autowired
    private LeadsService leadsService;
    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    private SendEmailServiceAPI sendEmailServiceAPI;
    @Autowired
    private NextBatchReminderService nextBatchReminderService;
    @Autowired
    private SendEmailService2 sendEmailService2;

    Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/qrCode1")
    public StreamingResponseBody demo(@RequestParam("casedid") QRCode qrCode1, HttpServletResponse response, RedirectAttributes attributes) throws IOException {
        logger.info("@Start qrCode1");
        String caseno1 = leadsService.checkCaseid(qrCode1.getCaseid());
        String[] words = caseno1.split("##");

        String caseno = words[0];
        String doctorName = words[1].toUpperCase(Locale.ROOT);
        String patientName = words[2].toUpperCase(Locale.ROOT);

        int length = doctorName.length();
        int length2 = patientName.length();
        if (length > 19) {
            doctorName = doctorName.substring(0, 19);
            doctorName = doctorName + "..";

        }
        if (length2 > 19) {
            patientName = patientName.substring(0, 19);
            patientName = patientName + "..";
        }
        if (caseno != null) {
            try {

                qrCodeService.generateQRCODE(qrCode1.getCaseid(), doctorName, patientName);
                String file = env.getProperty("app.dynamicFile") + caseno + ".pdf";
                String QRName = "QR" + caseno + ".pdf";
                String t1 = "attachment; filename=\"" + QRName + "\"";
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", t1);
                InputStream inputStream = new FileInputStream(new File(file));
                return outputStream -> {
                    int nRead;
                    byte[] data = new byte[1024];
                    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                        outputStream.write(data, 0, nRead);
                    }
                };
            } catch (Exception e) {
                logger.error("Exception=" + e.getMessage());
            }
            return outputStream -> {
            };


        } else {
            return outputStream -> {
            };
        }
    }

    @GetMapping("/qrCode2")
    public StreamingResponseBody demo2(@ModelAttribute("qrCode1") QRCode qrCode1, HttpServletResponse response, RedirectAttributes attributes) throws IOException {
        logger.info("@Start qrCode2");
        String caseno = leadsService.checkCaseid1(qrCode1.getCaseid());
        if (caseno != null) {
            try {

                qrCodeService.generateQRCODE1(qrCode1.getCaseid());
                String file = env.getProperty("app.dynamicFile") + caseno + ".pdf";
                String QRName = "QR" + caseno + ".pdf";
                String t1 = "attachment; filename=\"" + QRName + "\"";
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", t1);
                InputStream inputStream = new FileInputStream(new File(file));
                return outputStream -> {
                    int nRead;
                    byte[] data = new byte[1024];
                    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                        outputStream.write(data, 0, nRead);
                    }
                };
            } catch (Exception e) {
                logger.error("Exception=" + e.getMessage());
            }
            return outputStream -> {
            };


        } else {
            return outputStream -> {
            };
        }
    }

    @PostMapping(value = "/sendmail", consumes = "multipart/form-data")
    public ResponseEntity<Map> sendMail(@RequestParam MultipartFile attachment1,
                                        @RequestParam MultipartFile attachment2, @RequestParam MultipartFile attachment3,
                                        @RequestParam MultipartFile attachment4, @RequestParam MultipartFile attachment5,
                                        @RequestParam MultipartFile attachment6, @RequestParam MultipartFile attachment7,
                                        @RequestParam MultipartFile attachment8, @RequestParam MultipartFile attachment9,
                                        @RequestParam MultipartFile attachment10, @RequestParam String to, @RequestParam String cc,
                                        @RequestParam String message, @RequestParam String body) {
        return sendEmailServiceAPI.sendMail(body, to, cc, attachment1, attachment2, attachment3, attachment4,
                attachment5, attachment6, attachment7, attachment8, attachment9, attachment10, message);
    }

    @PostMapping("/sendTestMail")
    public ResponseEntity<Map> sendMail1(@RequestParam String body, @RequestParam String message, @RequestParam String to, @RequestParam String cc, @RequestParam(required = false) List<MultipartFile> attachment) {
        return sendEmailService2.sendMail(message, body, cc, to, attachment);
    }


}
