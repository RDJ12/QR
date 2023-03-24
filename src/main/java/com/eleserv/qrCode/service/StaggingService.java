package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Stagging;
import com.eleserv.qrCode.reposistory.StaggingReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaggingService {
    Logger logger = LoggerFactory.getLogger(StaggingService.class);
    @Autowired
    private StaggingReposistory staggingReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;


    public List<Stagging> getStaggingRecordById(String id) {
        List<Stagging> staggingList = new ArrayList<>();
        try {
            staggingList = staggingReposistory.getStaggingRecordById(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getStaggingRecordById Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getStaggingRecordById()");
        }
        return staggingList;
    }
}
