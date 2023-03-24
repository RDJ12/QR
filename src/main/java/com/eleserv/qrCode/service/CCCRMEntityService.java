package com.eleserv.qrCode.service;

import com.eleserv.qrCode.entity.*;
import com.eleserv.qrCode.reposistory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CCCRMEntityService {

    Logger logger = LoggerFactory.getLogger(CCCRMEntityService.class);

    @Autowired
    private CCCRMEntityReposistory cccrmEntityReposistory;

    @Autowired
    private SendEmailService1 sendEmailService1;

    @Autowired
    private DecisionHistoryRepository decisionHistoryRepository;

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountChangeRepository accountChangeRepository;

    @Autowired
    private ExecuteDecitionHistoryRepository executeDecitionHistoryRepository;

    public int updateCC(CCCRMEntity cccrmEntity) {
        int count = 0;
        try {
            CCCRMEntity cccrmEntity1 = cccrmEntityReposistory.getOne(cccrmEntity.getCase_Id());
            if (cccrmEntity1 != null) {
                if (cccrmEntity.getRegistered_doctor() != null)
                    cccrmEntity1.setRegistered_doctor(cccrmEntity.getRegistered_doctor());

                if (cccrmEntity.getClinic_Name() != null)
                    cccrmEntity1.setClinic_Name(cccrmEntity.getClinic_Name());
                if (cccrmEntity.getCrm_name() != null)
                    cccrmEntity1.setCrm(cccrmEntity.getCrm());
                if (cccrmEntity.getCase_type() != null)
                    cccrmEntity1.setCase_type(cccrmEntity.getCase_type());

                if (cccrmEntity.getScan() != null)
                    cccrmEntity1.setScan(cccrmEntity.getScan());
                if (cccrmEntity.getLocation() != null)
                    cccrmEntity1.setLocation(cccrmEntity.getLocation());
                if (cccrmEntity.getCity() != null)
                    cccrmEntity1.setCity(cccrmEntity.getCity());
                if (cccrmEntity.getP_graph() != null)
                    cccrmEntity1.setP_graph(cccrmEntity.getP_graph());
                if (cccrmEntity.getStarter_kit() != null)
                    cccrmEntity1.setStarter_kit(cccrmEntity.getStarter_kit());
                if (cccrmEntity.getType_request() != null)
                    cccrmEntity1.setType_request(cccrmEntity.getType_request());
                if (cccrmEntity.getS_shown() != null)
                    cccrmEntity1.setS_shown(cccrmEntity.getS_shown());
                if (cccrmEntity.getPpf_fill() != null)
                    cccrmEntity1.setPpf_fill(cccrmEntity.getPpf_fill());
                if (cccrmEntity.getCase_stage() != null) {
                    cccrmEntity1.setCase_stage(cccrmEntity.getCase_stage());
                    if (cccrmEntity.getCase_stage().equals("Dispatched"))
                        cccrmEntity1.setDispatch(cccrmEntity.getDispatch());
                    if (cccrmEntity.getCase_stage().equals("PRECOR"))
                        cccrmEntity1.setPrecor_at(cccrmEntity.getPrecor_at());
                    if (cccrmEntity.getCase_stage().equals("INI"))
                        cccrmEntity1.setIni_at(cccrmEntity.getIni_at());
                    if (cccrmEntity.getCase_stage().equals("REV"))
                        cccrmEntity1.setRev_at(cccrmEntity.getRev_at());
                    if (cccrmEntity.getCase_stage().equals("MPT"))
                        cccrmEntity1.setMpt_at(cccrmEntity.getMpt_at());
                    if (cccrmEntity.getCase_stage().equals("STG"))
                        cccrmEntity1.setStg_at(cccrmEntity.getStg_at());
                    if (cccrmEntity.getCase_stage().equals("QA"))
                        cccrmEntity1.setQa_at(cccrmEntity.getQa_at());
                    if (cccrmEntity.getCase_stage().equals("FQC"))
                        cccrmEntity1.setFqc_at(cccrmEntity.getFqc_at());
                    if (cccrmEntity.getCase_stage().equals("CADBS"))
                        cccrmEntity1.setCadbs_at(cccrmEntity.getCadbs_at());
                    if (cccrmEntity.getCase_stage().equals("WFCCOR"))
                        cccrmEntity1.setWfccor_at(cccrmEntity.getWfccor_at());
                    if (cccrmEntity.getCase_stage().equals("PCK"))
                        cccrmEntity1.setPck_at(cccrmEntity.getPck_at());
                    if (cccrmEntity.getCase_stage().equals("PLN"))
                        cccrmEntity1.setPln_at(cccrmEntity.getPln_at());
                    if (cccrmEntity.getCase_stage().equals("WFC"))
                        cccrmEntity1.setWfc_at(cccrmEntity.getWfc_at());
                    if (cccrmEntity.getCase_stage().equals("PLNCOR"))
                        cccrmEntity1.setPlncor_at(cccrmEntity.getPlncor_at());
                    if (cccrmEntity.getCase_stage().equals("INISTRKIT"))
                        cccrmEntity1.setInistrkit_at(cccrmEntity.getInistrkit_at());
                    if (cccrmEntity.getCase_stage().equals("MTPSTRKIT"))
                        cccrmEntity1.setMtpstrkit_at(cccrmEntity.getMtpstrkit_at());
                    if (cccrmEntity.getCase_stage().equals("PCKSTRKIT"))
                        cccrmEntity1.setPckstrkit_at(cccrmEntity.getPckstrkit_at());
                    if (cccrmEntity.getCase_stage().equals("PCKCOR"))
                        cccrmEntity1.setPckcor_at(cccrmEntity.getPckcor_at());
                    if (cccrmEntity.getCase_stage().equals("PCKSTRKITCOR"))
                        cccrmEntity1.setPckstrkitcor_at(cccrmEntity.getPckstrkitcor_at());
                }
                if (cccrmEntity.getPriority() != null)
                    cccrmEntity1.setPriority(cccrmEntity.getPriority());
                if (cccrmEntity.getDraft() != null)
                    cccrmEntity1.setDraft(cccrmEntity.getDraft());
                if (cccrmEntity.getRemark() != null)
                    cccrmEntity1.setRemark(cccrmEntity.getRemark());
                if (cccrmEntity.getHoldflag() != null)
                    cccrmEntity1.setHoldflag(cccrmEntity.getHoldflag());
                if (cccrmEntity.getUser_id() != null)
                    cccrmEntity1.setUser_id(cccrmEntity.getUser_id());
                if (cccrmEntity.getStarter_satus() != null)
                    cccrmEntity1.setStarter_satus(cccrmEntity.getStarter_satus());
                if (cccrmEntity.getIni_at() != null)
                    cccrmEntity1.setIni_at(cccrmEntity.getIni_at());
                if (cccrmEntity.getAddress1() != null)
                    cccrmEntity1.setAddress1(cccrmEntity.getAddress1());
                if (cccrmEntity.getAddress2() != null)
                    cccrmEntity1.setAddress2(cccrmEntity.getAddress2());
                if (cccrmEntity.getAddress3() != null)
                    cccrmEntity1.setAddress3(cccrmEntity.getAddress3());
                if (cccrmEntity.getAddress4() != null)
                    cccrmEntity1.setAddress4(cccrmEntity.getAddress4());
                if (cccrmEntity.getAddress5() != null)
                    cccrmEntity1.setAddress5(cccrmEntity.getAddress5());
                if (cccrmEntity.getPhone1() != null)
                    cccrmEntity1.setPhone1(cccrmEntity.getPhone1());
                if (cccrmEntity.getPhone2() != null)
                    cccrmEntity1.setPhone2(cccrmEntity.getPhone2());
                if (cccrmEntity.getPhone3() != null)
                    cccrmEntity1.setPhone3(cccrmEntity.getPhone3());
                if (cccrmEntity.getPhone4() != null)
                    cccrmEntity1.setPhone4(cccrmEntity.getPhone4());
                if (cccrmEntity.getPhone5() != null)
                    cccrmEntity1.setPhone5(cccrmEntity.getPhone5());
                if (cccrmEntity.getLocation2() != null)
                    cccrmEntity1.setLocation2(cccrmEntity.getLocation2());
                if (cccrmEntity.getLocation3() != null)
                    cccrmEntity1.setLocation3(cccrmEntity.getLocation3());
                if (cccrmEntity.getLocation4() != null)
                    cccrmEntity1.setLocation4(cccrmEntity.getLocation4());
                if (cccrmEntity.getLocation5() != null)
                    cccrmEntity1.setLocation5(cccrmEntity.getLocation5());
                if (cccrmEntity.getCity2() != null)
                    cccrmEntity1.setCity2(cccrmEntity.getCity2());
                if (cccrmEntity.getCity3() != null)
                    cccrmEntity1.setCity3(cccrmEntity.getCity3());
                if (cccrmEntity.getCity4() != null)
                    cccrmEntity1.setCity4(cccrmEntity.getCity4());
                if (cccrmEntity.getCity5() != null)
                    cccrmEntity1.setCity5(cccrmEntity.getCity5());
                if (cccrmEntity.getDefault_starterkit() != null)
                    cccrmEntity1.setDefault_starterkit(cccrmEntity.getDefault_starterkit());
                if (cccrmEntity.getPincode1() != null)
                    cccrmEntity1.setPincode1(cccrmEntity.getPincode1());
                if (cccrmEntity.getPincode2() != null)
                    cccrmEntity1.setPincode2(cccrmEntity.getPincode2());
                if (cccrmEntity.getPincode3() != null)
                    cccrmEntity1.setPincode3(cccrmEntity.getPincode3());
                if (cccrmEntity.getPincode4() != null)
                    cccrmEntity1.setPincode4(cccrmEntity.getPincode4());
                if (cccrmEntity.getPincode5() != null)
                    cccrmEntity1.setPincode5(cccrmEntity.getPincode5());
                if (cccrmEntity.getDefault_address() != null)
                    cccrmEntity1.setDefault_address(cccrmEntity.getDefault_address());
                if (cccrmEntity.getPatient_Name() != null)
                    cccrmEntity1.setPatient_Name(cccrmEntity.getPatient_Name());
                if (cccrmEntity.getDoctor_Name() != null)
                    cccrmEntity1.setDoctor_Name(cccrmEntity.getDoctor_Name());
                CCCRMEntity result = cccrmEntityReposistory.save(cccrmEntity1);
                if (result != null)
                    count = 1;
            } else {
                return count;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@saveCC Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/cccrm", "saveCC()");
        }
        return count;
    }

    public CCCRMEntity saveCC(CCCRMEntity cccrmEntity) {
        CCCRMEntity cccrmEntityObject = null;
        try {
            cccrmEntityObject = cccrmEntityReposistory.saveAndFlush(cccrmEntity);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@saveCC Exception: " + exception.getMessage());
            // sendEmailService1.sendMail(exception.toString(), "/rest/cccrm", "saveCC()");
        }
        return cccrmEntityObject;
    }


    public CCCRMEntity getById(Long caseId) {
        CCCRMEntity cccrmEntityObject = null;
        try {
            cccrmEntityObject = cccrmEntityReposistory.findById(caseId).get();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getById Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.getMessage(), "/rest/customer360/{caseid}", "getById()");
        }
        return cccrmEntityObject;
    }

    public List<CCCRMEntity> getListCase(String casestage) {
        List<CCCRMEntity> cccrmEntityList = new ArrayList<>();
        try {
            cccrmEntityList = cccrmEntityReposistory.getRecordByCaseStage(casestage);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getListCase Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/cccrm", "getListCase()");
        }
        return cccrmEntityList;
    }

    public List<CCCRMEntity> getListCaseofStarterkit(String casestage) {
        List<CCCRMEntity> cccrmEntityList = new ArrayList<>();
        try {
            cccrmEntityList = cccrmEntityReposistory.getRecordByStarterkitCaseStage(casestage);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getListCaseofStarterkit Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/ccrm", "getListCaseofStarterkit()");
        }
        return cccrmEntityList;
    }

    public List getAddress(Long caseId, String addressType) {
        List list = new ArrayList();
        try {
            CCCRMEntity cccrmentity = cccrmEntityReposistory.findById(caseId).get();
            String defaultAddress = "";
            if (addressType.equals("batch"))
                defaultAddress = cccrmentity.getDefault_address();
            else if (addressType.equals("starterkit"))
                defaultAddress = cccrmentity.getDefault_starterkit();

            switch (defaultAddress) {
                case "1":
                    list.add(addValueInMap(cccrmentity.getAddress1(), cccrmentity.getPincode1(), cccrmentity.getPhone1(),
                            cccrmentity.getCity(), cccrmentity.getLocation()));
                    break;
                case "2":
                    list.add(addValueInMap(cccrmentity.getAddress2(), cccrmentity.getPincode2(), cccrmentity.getPhone2(),
                            cccrmentity.getCity2(), cccrmentity.getLocation2()));
                    break;
                case "3":
                    list.add(addValueInMap(cccrmentity.getAddress3(), cccrmentity.getPincode3(), cccrmentity.getPhone3(),
                            cccrmentity.getCity3(), cccrmentity.getLocation3()));
                    break;
                case "4":
                    list.add(addValueInMap(cccrmentity.getAddress4(), cccrmentity.getPincode4(), cccrmentity.getPhone4(),
                            cccrmentity.getCity4(), cccrmentity.getLocation4()));
                    break;
                case "5":
                    list.add(addValueInMap(cccrmentity.getAddress5(), cccrmentity.getPincode5(), cccrmentity.getPhone5(),
                            cccrmentity.getCity5(), cccrmentity.getLocation5()));
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getAddress Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "/rest/customer360/{caseid}", "getAddress()");
        }
        return list;
    }

    public Map addValueInMap(String address, String pincode, String phone, String city,
                             String location) {
        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("pincode", pincode);
        map.put("phone", phone);
        map.put("city", city);
        map.put("location", location);
        return map;
    }

    public ResponseEntity<Map> updatePlanStage(Long caseId, String userId) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            int count = cccrmEntityReposistory.updatePlanStage(caseId, LocalDateTime.now());
            if (count >= 1) {
                DecisionHistory decisionHistory = new DecisionHistory();
                decisionHistory.setCaseid(caseId.toString());
                decisionHistory.setStage("UPL");
                decisionHistory.setDecision("UPL");
                decisionHistory.setUserId(userId);
                decisionHistoryRepository.saveAndFlush(decisionHistory);
                if (decisionHistory != null) {
                    map.put("status", 200);
                    map.put("message", "Data update successfully");
                    status = HttpStatus.OK;
                }
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@updatePlanStage Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "updatePlanStage", "updatePlanStage()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> updateBatchStage(Dispatch dispatchData) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            int count = cccrmEntityReposistory.updateBatchStage(Long.parseLong(dispatchData.getCase_id()),
                    LocalDateTime.now());
            if (count >= 1) {
                DecisionHistory decisionHistory = new DecisionHistory();
                decisionHistory.setCaseid(dispatchData.getCase_id());
                decisionHistory.setStage("Dispatched");
                decisionHistory.setDecision("Dispatched");
                decisionHistory.setRemarks(dispatchData.getRemark());
                decisionHistory.setUserId(dispatchData.getUserId());
                decisionHistoryRepository.saveAndFlush(decisionHistory);
                if (decisionHistory != null) {
                    Dispatch dispatch = dispatchService.saveDispatch(dispatchData);
                    if (dispatch != null) {
                        map.put("status", 200);
                        map.put("message", "Data update successfully");
                        status = HttpStatus.OK;
                    }
                }
            } else {
                map.put("status", 404);
                map.put("message", "Case Id Not Present");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@updateBatchStage Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "updateBatchStage", "updateBatchStage()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> updateStarterkitStage(Dispatch dispatchData) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            int count = cccrmEntityReposistory.updateStarterkitStage(Long.parseLong(dispatchData.getCase_id()), LocalDateTime.now());
            if (count >= 1) {
                DecisionHistory decisionHistory = new DecisionHistory();
                decisionHistory.setCaseid(dispatchData.getCase_id());
                decisionHistory.setStage("DPHSTRKIT");
                decisionHistory.setDecision("DPHSTRKIT");
                decisionHistory.setRemarks(dispatchData.getRemark());
                decisionHistory.setUserId(dispatchData.getUserId());
                decisionHistoryRepository.saveAndFlush(decisionHistory);
                if (decisionHistory != null) {
                    Dispatch dispatch = dispatchService.saveDispatch(dispatchData);
                    if (dispatch != null) {
                        map.put("status", 200);
                        map.put("message", "Data update successfully");
                        status = HttpStatus.OK;
                    }
                }
            } else {
                map.put("status", 404);
                map.put("message", "Case Id Not Present");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@updateStarterkitStage Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "updateStarterkitStage", "updateStarterkitStage()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> updatePayment_Bill_Status(List<DispatchUpdateReport> list) {
        Map map = new HashMap();
        HttpStatus status = null;
        List<PaymentBillStatus> updatePayment_Bill_Status = new ArrayList<>();
        boolean isAccountPresent = false;
        boolean isUpdateExecuted = false;
        try {
            DispatchUpdateReport dispatchUpdateReport = list.get(0);
            if (list.size() == 1) {
                Account account = accountRepository.findByCaseId(dispatchUpdateReport.getCaseId());
                if (dispatchUpdateReport.getStatus().equals("billed") || dispatchUpdateReport.getStatus().equals("collected")) {
                    if (account != null) {
                        updatePayment_Bill_Status = cccrmEntityReposistory.updatePayment_Bill_Status(dispatchUpdateReport.getIds(), dispatchUpdateReport.getStatus(), dispatchUpdateReport.getRemarks());
                        updateDispatchData(dispatchUpdateReport);
                        AccountChange accountChange = new AccountChange();
                        accountChange.setCase_id(account.getCase_id());
                        accountChange.setUser(dispatchUpdateReport.getUsername());
                        Account transaction = new Account();
                        transaction.setCrm(account.getCrm());
                        transaction.setCase_id(account.getCase_id());
                        transaction.setDoctor_name(account.getDoctor_name());
                        transaction.setPatient_name(account.getPatient_name());
                        transaction.setPayment(account.getPayment());
                        transaction.setRemain_amount(account.getRemain_amount());
                        transaction.setSubmitted_amount(account.getSubmitted_amount());
                        transaction.setUser(dispatchUpdateReport.getUsername());
                        if (dispatchUpdateReport.getBillingAmount() != null && dispatchUpdateReport.getBillingAmount() != "" && dispatchUpdateReport.getStatus().equals("billed")) {
                            transaction.setBilling_amount(dispatchUpdateReport.getBillingAmount());
                            accountChange.setBilling_amount(dispatchUpdateReport.getBillingAmount());
                        } else {
                            if (dispatchUpdateReport.getPaidAmount() != null) {
                                Integer remainingAmount = Integer.parseInt(account.getRemain_amount()) - Integer.parseInt(dispatchUpdateReport.getPaidAmount());
                                Integer submittedAmount = Integer.parseInt(account.getSubmitted_amount()) + Integer.parseInt(dispatchUpdateReport.getPaidAmount());
                                transaction.setPaid_amount(dispatchUpdateReport.getPaidAmount());
                                transaction.setRemain_amount(remainingAmount.toString());
                                transaction.setSubmitted_amount(submittedAmount.toString());
                                accountChange.setPrevious_amount(account.getPaid_amount());
                                accountChange.setNew_amount(dispatchUpdateReport.getPaidAmount());
                            }
                            if (dispatchUpdateReport.getModeOfPayment() != null)
                                transaction.setPayment_mode(dispatchUpdateReport.getModeOfPayment());
                            if (dispatchUpdateReport.getInvoiceDate() != null) {
                                transaction.setInvoiceDate(dispatchUpdateReport.getInvoiceDate());
                                accountChange.setInvoiceDate(dispatchUpdateReport.getInvoiceDate());
                            }
                            if (dispatchUpdateReport.getInvoiceNumber() != null) {
                                transaction.setInvoiceNumber(dispatchUpdateReport.getInvoiceNumber());
                                accountChange.setInvoiceNumber(dispatchUpdateReport.getInvoiceNumber());
                            }
                            if (dispatchUpdateReport.getRemarks() != null) {
                                transaction.setRemarks(dispatchUpdateReport.getRemarks());
                                accountChange.setReason(dispatchUpdateReport.getRemarks());
                            }
                        }
                        accountRepository.saveAndFlush(transaction);
                        accountChangeRepository.saveAndFlush(accountChange);
                        isAccountPresent = true;
                    }
                    isUpdateExecuted = true;
                } else if (!dispatchUpdateReport.getStatus().equals("billed") && !dispatchUpdateReport.getStatus().equals("collected")) {
                    updatePayment_Bill_Status = cccrmEntityReposistory.updatePayment_Bill_Status(dispatchUpdateReport.getIds(), dispatchUpdateReport.getStatus(), dispatchUpdateReport.getRemarks());
                    updateDispatchData(dispatchUpdateReport);
                    isUpdateExecuted = true;
                }
            } else if (list.size() > 1 && (!dispatchUpdateReport.getStatus().equals("billed") && !dispatchUpdateReport.getStatus().equals("collected"))) {
                for (DispatchUpdateReport data : list) {
                    updatePayment_Bill_Status = cccrmEntityReposistory.updatePayment_Bill_Status(data.getIds(), data.getStatus(), data.getRemarks());
                    updateDispatchData(data);
                }
                isUpdateExecuted = true;
            }
            if (updatePayment_Bill_Status.size() > 0 || isUpdateExecuted) {
                map.put("status", 200);
                map.put("message", "Data updated successfully");
                map.put("isAccountPresent", isAccountPresent);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Case Id Not Present");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception: " + exception);
            logger.info("@updateStarterkitStage Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "updatePayment_Bill_Status", "updatePayment_Bill_Status()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public void updateDispatchData(DispatchUpdateReport data) {
        try {
            cccrmEntityReposistory.updatePayment_Bill_Status(data.getIds(), data.getStatus(), data.getRemarks());
            ExecuteDecitionHistory executeDecitionHistory = new ExecuteDecitionHistory();
            executeDecitionHistory.setUser_name(data.getUsername());
            executeDecitionHistory.setCase_id(data.getCaseId());
            executeDecitionHistory.setDispatched_id(data.getIds());
            executeDecitionHistory.setPatient_name(data.getPatientName());
            executeDecitionHistory.setDoctor_name(data.getDoctorName());
            executeDecitionHistory.setLower_dispatched(data.getLowerDispatched());
            executeDecitionHistory.setUpper_dispatched(data.getUpperDispatched());
            executeDecitionHistory.setPayment_bill_status(data.getStatus());
            executeDecitionHistory.setPayment_bill_status_remarks(data.getRemarks());
            executeDecitionHistoryRepository.saveAndFlush(executeDecitionHistory);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
        }
    }

    public ResponseEntity<Map> getWattsDispatchReports(String FromDate, String ToDate, String Case_Type, String Dispatch_Type, String Request_Type, String Extra_Aligner, String Payment_Status) {
        List<WattsDispatchReports> getWattsDispatchReports = new ArrayList<>();
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            getWattsDispatchReports = cccrmEntityReposistory.getWattsDispatchReports(FromDate, ToDate, Case_Type, Dispatch_Type, Request_Type, Extra_Aligner, Payment_Status);
            if (getWattsDispatchReports.size() > 0) {
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", getWattsDispatchReports);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Case Id Not Present");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception: " + exception);
            logger.info("@updateStarterkitStage Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "getWattsDispatchReports", "getWattsDispatchReports()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);

    }

    public static void updateHistoryTable(String case_stage, Long caseid) {

    }

    public ResponseEntity<Map> getPaymentReport(String FromDate, String ToDate, String Case_Type) {
        List<PaymentReport> list = new ArrayList<>();
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            list = cccrmEntityReposistory.getPaymentReport(FromDate, ToDate, Case_Type);
            if (list.size() > 0) {
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Data not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
            logger.info("@getPaymentReport Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "getPaymentReport", "getPaymentReport()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getCaseStatusReport(String Case_Type, String Case_Status, String FromDate, String ToDate) {
        List<CaseStatusReport> list = new ArrayList<>();
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            list = cccrmEntityReposistory.getCaseStatusReport(Case_Type, Case_Status, FromDate, ToDate);
            if (list.size() > 0) {
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Data not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception: " + exception);
            logger.info("@getCaseStatusReport Exception: " + exception.getMessage());
            sendEmailService1.sendMail(exception.toString(), "getCaseStatusReport", "getCaseStatusReport()");
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }


}
