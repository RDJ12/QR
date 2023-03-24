package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.DecisionHistory;
import com.eleserv.qrCode.reposistory.DecisionHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DecisionHistoryService {
    Logger logger = LoggerFactory.getLogger(DecisionHistoryService.class);
    @Autowired
    private DecisionHistoryRepository decisionHistoryRepository;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<DecisionHistory> getDecisionHistoryRecordByID(String id) {
        List<DecisionHistory> decisionHistoryList = new ArrayList<>();
        try {
            decisionHistoryList = decisionHistoryRepository.getDecisionHistoryRecordById(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getDecisionHistoryRecordByID Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}",
                    "getDecisionHistoryRecordByID()");
        }
        return decisionHistoryList;
    }

    public DecisionHistory saveDecisionHistory(DecisionHistory decisionHistory) {
        DecisionHistory decisionHistoryObject = null;
        try {
            decisionHistoryObject = decisionHistoryRepository.save(decisionHistory);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@saveDecisionHistory Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/decisionHistory", "saveDecisionHistory()");
        }
        return decisionHistoryObject;
    }

    public ResponseEntity<Map> getDecisionHistoryByUser(String fromDate, String toDate, String userId) {
        List<DecisionHistory> decisionHistoryList = new ArrayList<>();
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            if (!fromDate.equals("") && (fromDate.equals(toDate) || toDate.equals(""))) {
                decisionHistoryList = decisionHistoryRepository.getDecisionHistoryByUserSingleDay(fromDate, userId);
            } else if (fromDate.equals("") && toDate.equals("")) {
                decisionHistoryList = decisionHistoryRepository.getLastSevenDaysDecisionHistoryByUser(userId);
            } else if (!fromDate.equals("") && !toDate.equals("")) {
                decisionHistoryList = decisionHistoryRepository.getDecisionHistoryByUser(fromDate, toDate, userId);
            }

            if (decisionHistoryList.isEmpty()) {
                map.put("message", "No record found");
                map.put("data", "");
                map.put("status", 404);
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("message", "Data Found");
                map.put("data", decisionHistoryList);
                map.put("status", 200);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getDecisionHistoryByUser Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "userWorkList", "getDecisionHistoryByUser()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getCaseCount(String fromDate, String toDate) {
        List<Map> decisionHistoryList = new ArrayList<>();
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            if (!fromDate.equals("") && (fromDate.equals(toDate) || toDate.equals(""))) {
                decisionHistoryList = decisionHistoryRepository.getCaseCountSingleDay(fromDate);
            } else if (fromDate.equals("") && toDate.equals("")) {
                decisionHistoryList = decisionHistoryRepository.getCaseCountLastSevenDays();
            } else if (!fromDate.equals("") && !toDate.equals("")) {
                decisionHistoryList = decisionHistoryRepository.getCaseCount(fromDate, toDate);
            }
            if (decisionHistoryList.isEmpty()) {
                map.put("message", "No record found");
                map.put("data", "");
                map.put("status", 404);
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("message", "Data Found");
                map.put("data", decisionHistoryList);
                map.put("status", 200);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getDecisionHistoryByUser Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "userWorkList", "getDecisionHistoryByUser()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getStageCaseCount(String fromDate, String toDate) {
        List<Map> decisionHistoryList = new ArrayList<>();
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            if (!fromDate.equals("") && (fromDate.equals(toDate) || toDate.equals(""))) {
                decisionHistoryList = decisionHistoryRepository.getStageCaseCountSingleDay(fromDate);
            } else if (fromDate.equals("") && toDate.equals("")) {
                decisionHistoryList = decisionHistoryRepository.getStageCaseCountLastSevenDays();
            } else if (!fromDate.equals("") && !toDate.equals("")) {
                decisionHistoryList = decisionHistoryRepository.getStageCaseCount(fromDate, toDate);
            }
            if (decisionHistoryList.isEmpty()) {
                map.put("message", "No record found");
                map.put("data", "");
                map.put("status", 404);
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("message", "Data Found");
                map.put("data", decisionHistoryList);
                map.put("status", 200);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getDecisionHistoryByUser Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "userWorkList", "getDecisionHistoryByUser()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

}
