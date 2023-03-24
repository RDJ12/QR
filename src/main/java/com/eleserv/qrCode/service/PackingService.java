package com.eleserv.qrCode.service;

import com.eleserv.qrCode.controller.AppController;
import com.eleserv.qrCode.entity.Packing;
import com.eleserv.qrCode.reposistory.PackingReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackingService {
    Logger logger = LoggerFactory.getLogger(PackingService.class);
    @Autowired
    private PackingReposistory packingReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public Packing getRecordByID(String case_id) {
        Packing packingObject = null;
        try {
            packingObject = packingReposistory.getPackingById(case_id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getRecordByID Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/packing/{caseid}", "getRecordByID()");
        }
        return packingObject;
    }

    public List<Packing> getALLPacking() {
        List<Packing> packingList = new ArrayList<>();
        try {
            packingList = packingReposistory.findAll();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getALLPacking Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/packing", "getALLPacking()");
        }
        return packingList;
    }

    public List<Packing> getALLPackingByID(String caseid) {
        List<Packing> packingList = new ArrayList<>();
        try {
            packingList = packingReposistory.getPackingListById(caseid);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getALLPackingByID Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getALLPackingByID()");
        }
        return packingList;
    }

}
