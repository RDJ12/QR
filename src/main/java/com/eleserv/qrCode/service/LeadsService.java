package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.Leads;
import com.eleserv.qrCode.reposistory.LeadsReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadsService {
    @Autowired
    private LeadsReposistory leadsReposistory;

    public Leads save(Leads leads){
        return leadsReposistory.save(leads);
    }
    public List<Leads> getAllUser(){
        return leadsReposistory.getAllInReverse();
    }
    public String checkCaseid(String caseid){
        return leadsReposistory.checkcaseidexistornot(caseid);
    }
    public String checkCaseid1(String caseid){
        return leadsReposistory.checkcaseidexistornot1(caseid);
    }

}
