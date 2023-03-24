package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.FQC;
import com.eleserv.qrCode.reposistory.FQCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FQCService {
    Logger logger = LoggerFactory.getLogger(HollowTagService.class);
    @Autowired
    private FQCRepository fqcRepository;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<FQC> getFQCRecordByID(String id) {
        List<FQC> fqcList = new ArrayList<>();
        try {
            fqcList = fqcRepository.getFQCRecordById(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getFQCRecordByID Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getFQCRecordByID()");
        }
        return fqcList;
    }
}
