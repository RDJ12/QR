package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Planning;
import com.eleserv.qrCode.reposistory.PlanningReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanningService {
    Logger logger = LoggerFactory.getLogger(PlanningService.class);
    @Autowired
    private PlanningReposistory planningReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<Planning> getRecordById(String id) {
        List<Planning> planningList = new ArrayList<>();
        try {
            planningList = planningReposistory.getRecordByCaseStage(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getRecordById Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/planningdetails/{caseid}", "getRecordById()");
        }
        return planningList;
    }

}
