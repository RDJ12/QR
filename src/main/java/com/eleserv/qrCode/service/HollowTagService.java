package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.HollowTag;
import com.eleserv.qrCode.reposistory.HollowTagReposistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HollowTagService {
    Logger logger = LoggerFactory.getLogger(HollowTagService.class);
    @Autowired
    private HollowTagReposistory hollowTagReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    public List<HollowTag> getHollowTagListById(String id) {
        List<HollowTag> hollowTagList = new ArrayList<>();
        try {
            hollowTagList = hollowTagReposistory.getHollowTaggingRecordById(id);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getHollowTagListById Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getHollowTagListById()");
        }
        return hollowTagList;
    }
}
