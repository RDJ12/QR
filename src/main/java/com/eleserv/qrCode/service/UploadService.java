package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Upload;
import com.eleserv.qrCode.reposistory.UploadReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {
    Logger logger = LoggerFactory.getLogger(UploadService.class);
    @Autowired
    private UploadReposistory uploadReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<Upload> getAllUploadRecordbyId(String caseid) {
        List<Upload> uploadList = new ArrayList<>();
        try {
            uploadList = uploadReposistory.getRecordByCaseStage(caseid);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getAllUploadRecordbyId Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getAllUploadRecordbyId()");
        }
        return uploadList;
    }
}
