package com.eleserv.qrCode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eleserv.qrCode.reposistory.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SendEmailService1 sendEmailService1;

    Logger logger = LoggerFactory.getLogger(CCCRMEntityService.class);

    public ResponseEntity<Map> getAccountsData(String fromDate, String toDate) {
        Map map = new HashMap();
        HttpStatus status = null;
        List accountsList = new ArrayList();
        try {
            if (!fromDate.equals("") && (fromDate.equals(toDate) || toDate.equals(""))) {
                accountsList = accountRepository.getAccountsDataByDate(fromDate);
            } else if (fromDate.equals("") && toDate.equals("")) {
                accountsList = accountRepository.getLastSevenDaysAccountData();
            } else if(!fromDate.equals("") && !toDate.equals("")) {
                accountsList = accountRepository.getAccountsData(fromDate, toDate);
            }
            if (accountsList.isEmpty()) {
                map.put("message", "No record found");
                map.put("data", "");
                map.put("status", 404);
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("message", "Data Found");
                map.put("data", accountsList);
                map.put("status", 200);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getAccountsData Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "accountsData", "getAccountsData()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }
}
