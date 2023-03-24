package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.ThreeDPrinting;
import com.eleserv.qrCode.reposistory.ThreeDPrintingReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreeDPrintingService {
    Logger logger = LoggerFactory.getLogger(ThreeDPrintingService.class);
    @Autowired
    private ThreeDPrintingReposistory threeDPrintingReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<ThreeDPrinting> getThreeDPrintingRecordByID(String id) {
        List<ThreeDPrinting> threeDPrintingList = new ArrayList<>();
        try {
            threeDPrintingList = threeDPrintingReposistory.getThreeDPrintingRecordById(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getThreeDPrintingRecordByID Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getThreeDPrintingRecordByID()");
        }
        return threeDPrintingList;
    }
}
