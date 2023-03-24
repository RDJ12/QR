package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Lab;
import com.eleserv.qrCode.reposistory.LabRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabService {
    Logger logger = LoggerFactory.getLogger(LabService.class);
    @Autowired
    private LabRepository labRepository;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<Lab> getLabRecordById(String id) {
        List<Lab> labList = new ArrayList<>();
        try {
            labList = labRepository.getLabRecordById(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getLabRecordById Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getLabRecordById()");
        }
        return labList;
    }
}
