package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Leads;
import com.eleserv.qrCode.reposistory.LeadsReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadsService {
    @Autowired
    private LeadsReposistory leadsReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    Logger logger = LoggerFactory.getLogger(LabService.class);

    public Leads save(Leads leads) {
        Leads leadsObject = null;
        try {
            leadsObject = leadsReposistory.save(leads);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@save Exception: " + exception.getMessage());
        }
        return leadsObject;
    }

    public List<Leads> getAllUser() {
        List<Leads> leadsList = new ArrayList<>();
        try {
            leadsList = leadsReposistory.getAllInReverse();
            ;
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getAllUser Exception: " + exception.getMessage());
        }
        return leadsList;
    }

    public String checkCaseid(String caseid) {
        String check = "";
        try {
            check = leadsReposistory.checkcaseidexistornot(caseid);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@checkCaseid Exception: " + exception.getMessage());
        }
        return check;
    }

    public String checkCaseid1(String caseid) {
        String check = "";
        try {
            check = leadsReposistory.checkcaseidexistornot1(caseid);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@checkCaseid1 Exception: " + exception.getMessage());
        }
        return check;
    }

}
