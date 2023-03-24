package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Dispatch;
import com.eleserv.qrCode.reposistory.DispatchReposistory;
import com.eleserv.qrCode.reposistory.PackingReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispatchService {
    Logger logger = LoggerFactory.getLogger(DispatchService.class);
    @Autowired
    private DispatchReposistory dispatchReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public Dispatch saveDispatch(Dispatch dispatch) {
        Dispatch dispatchObject = null;
        try {
            dispatchObject = dispatchReposistory.save(dispatch);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@saveDispatch Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/dispatch", "saveDispatch()");
        }
        return dispatchObject;
    }

    public List<Dispatch> getDispatchListById(String case_id) {
        List<Dispatch> dispatchList = new ArrayList<>();
        try {
            dispatchList = dispatchReposistory.getDispatchListById(case_id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getDispatchListById Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getDispatchListById()");
        }
        return dispatchList;
    }
}
