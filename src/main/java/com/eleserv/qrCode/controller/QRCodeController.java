package com.eleserv.qrCode.controller;


import com.eleserv.qrCode.entity.*;
import com.eleserv.qrCode.service.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


@CrossOrigin(origins = "*")
@RestController
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    private Environment env;
    @Autowired
    private LeadsService leadsService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private NextBatchReminderService nextBatchReminderService;
    @Autowired
    private PackingService packingService;
    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private CCCRMEntityService cccrmEntityService;
    @Autowired
    private PlanningService planningService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private StaggingService staggingService;
    @Autowired
    private HollowTagService hollowTagService;
    @Autowired
    private ThreeDPrintingService threeDPrintingService;
    @Autowired
    private LabService labService;
    @Autowired
    private FQCService fqcService;
    @Autowired
    private DecisionHistoryService decisionHistoryService;
    @Autowired
    private SendEmailService1 sendEmailService1;
    @Autowired
    private AccountService accountService;

    Logger logger = LoggerFactory.getLogger(UploadService.class);

   /* @GetMapping("/neeraj")
    public String test() throws IOException {
       // List<NextBatchReminder> nextBatchReminderList=nextBatchReminderService.getNextBatchReminder("01-25-22");

        String dest = env.getProperty("app.dynamicpicture")+"addingTable.pdf";
     //   String dest = "C:/itextExamples/addingTable.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A4.rotate());

        float[] columnWidths = {5, 5, 5, 2, 2, 2, 2, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        LocalDateTime ldt = LocalDateTime.now();
        String today = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt);

        PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Cell cell = new Cell(1, 10)
                .add(new Paragraph("Next Batch Report On "+today))
                .setFont(f)
                .setFontSize(13)
                .setFontColor(DeviceGray.WHITE)
                .setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);

        table.addHeaderCell(cell);

        for (int i = 0; i < 1; i++) {
            Cell[] headerFooter = new Cell[]{
                  //  new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("S/No")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Case Id")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("PatientName")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("No of aligners")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("upper_aligner_from")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("upper_aligner_to")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_aligner_from")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_aligner_to")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("upper_date_in_days")),
                    new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_date_in_days"))
                  //  new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("dispatch_date")),
                  //  new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("next_date"))

            };

            for (Cell hfCell : headerFooter) {
                if (i == 0) {
                    table.addHeaderCell(hfCell);
                } else {
                    table.addFooterCell(hfCell);
                }
            }
       }
        nextBatchReminderList.forEach(nextBatchReminder -> {
           // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(nextBatchReminder.getNextbtch_id()))));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getCase_Id())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getPatient_Name())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getNo_of_aligners())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getUpper_aligner_from())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getUpper_aligner_to())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getLower_aligner_from())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getLower_aligner_to())));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(nextBatchReminder.getUpper_date_in_days()))));
            table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(nextBatchReminder.getLower_date_in_days()))));
          //  table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDispatch_date())));
           // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getNext_date())));
        });
       // for (int counter = 0; counter < 100; counter++) {
         //   table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(counter + 1))));
           // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("key " + (counter + 1))));
            //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + (counter + 1))));
        //}

        doc.add(table);

        doc.close();
        System.out.println("Table created successfully..");
        String body="Hi All,\nPlease find the attachment.\n\n\n\n\nnote:This is auto-generated mail send by system";
        sendEmailService.sendMail(body);
        return "Completed";
    }*/

    @GetMapping("/rest/")
    public StreamingResponseBody demo(@PathVariable("caseid") String caseid, HttpServletResponse response) throws IOException {
        String caseno = leadsService.checkCaseid(caseid);
        if (caseno != null) {
            try {

                //   qrCodeService.generateQRCODE(caseid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String file = env.getProperty("app.dynamicFile") + caseid + ".pdf";
            String QRName = "QR" + caseid + ".pdf";
            String t1 = "attachment; filename=\"" + QRName + "\"";
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", t1);
            InputStream inputStream = new FileInputStream(new File(file));
            return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, nRead);
                }
            };
        } else {
            return outputStream -> {
            };
        }


    }

    /*@GetMapping("/rest/packing/{caseid}")
    public Packing getRecordById(@PathVariable String caseid){
        return packingService.getRecordByID(caseid);
    }*/


    @GetMapping("/rest/packing/{caseid}")
    public ResponseEntity<Map> get(@PathVariable String caseid) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            if (packingService.getRecordByID(caseid) != null) {
                map.put("status_code", "200");
                map.put("results", packingService.getRecordByID(caseid));
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Case ID Invalid!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@get Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/packing/{caseid}", "getRecordByID()");
        }
        return new ResponseEntity(map, status);
    }

    @PostMapping("/rest/dispatch")
    public ResponseEntity<Map> saveDispatched(@RequestBody Dispatch dispatch) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            if (nextBatchReminderService.updateCC_CRM_StarterKit_Dispatch(dispatch.getRemark(), dispatch.getDecesion(), Long.valueOf(dispatch.getCase_id())) > 0) {
                Dispatch dispatch1 = dispatchService.saveDispatch(dispatch);
                if (dispatch1 != null) {
                    map.put("status_code", "200");
                    map.put("results", dispatch1);
                    map.put("message", "Success");
                    status = HttpStatus.OK;
                } else {
                    map.put("status_code", "404");
                    map.put("results", "Data not saved");
                    map.put("message", "Please check your request!");
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Please check your request or Case Id is incorrect!");
                status = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@saveDispatched Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/dispatch", "saveDispatch() and updateCC_CRM_StarterKit_Dispatch");
        }
        return new ResponseEntity(map, status);


    }

    @GetMapping("/rest/cccrm/{caseid}")
    public ResponseEntity<Map> getcccrmid(@PathVariable String caseid) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {

            CCCRMEntity cccrmEntity = cccrmEntityService.getById(Long.parseLong(caseid));
            if (cccrmEntity != null) {
                map.put("status_code", "200");
                map.put("results", cccrmEntity);
                map.put("message", "Success");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            } else {

                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Case ID Invalid!");
                status = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@getcccrmid Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/cccrm/{caseid}", "getById()");
        }
        return new ResponseEntity(map, status);
    }

    @PutMapping("/rest/cccrm")
    public ResponseEntity<Map> updateCCCRMData(@RequestBody CCCRMEntity cccrmEntity) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            int count = cccrmEntityService.updateCC(cccrmEntity);
            if (count != 0) {
                map.put("status_code", "200");
                map.put("results","Case Id :"+cccrmEntity.getCase_Id()+" Updated Successfully");
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Data not updated!");
                status = HttpStatus.NOT_FOUND;
            }


        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@saveccrm Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/cccrm", "saveCC()");
        }
        return new ResponseEntity(map, status);
    }


    @PostMapping("/rest/cccrm")
    public ResponseEntity<Map> saveccrm(@RequestBody CCCRMEntity cccrmEntity) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            CCCRMEntity cccrmEntity1 = cccrmEntityService.saveCC(cccrmEntity);
            if (cccrmEntity1 != null) {
                map.put("status_code", "200");
                map.put("results", cccrmEntity1);
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Data not saved!");
                status = HttpStatus.NOT_FOUND;
            }


        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@saveccrm Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/cccrm", "saveCC()");
        }
        return new ResponseEntity(map, status);
    }



    @GetMapping("/rest/packing")
    public ResponseEntity<Map> get() {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            List<Packing> getList = packingService.getALLPacking();

            if (getList != null && getList.size() > 0) {
                map.put("status_code", "200");
                map.put("results", getList);
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {

                map.put("status_code", "404");
                map.put("results", "No Data Found!");
                map.put("message", "Records Not Found");
                status = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@get Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/packing", "getALLPacking()");
        }
        return new ResponseEntity(map, status);
    }

    @GetMapping(path = "/rest/cccrm")
    public ResponseEntity<Map> getcccrmBycaseStage(@RequestParam String caseStage) {  //,@RequestParam String starterStage
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        map.entrySet().forEach(entry -> {
            System.out.println("key value :::::::::::: " + entry.getKey() + " " + entry.getValue());
        });

        try {
            List<CCCRMEntity> getList = cccrmEntityService.getListCase(caseStage);
            if (getList != null && getList.size() > 0) {
                map.put("status_code", "200");
                map.put("results", getList);
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Please check Case casestage!");
                status = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@getcccrmBycaseStage Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/ccrm", "getListCase()");
        }
        return new ResponseEntity(map, status);
    }

    //@GetMapping("/rest/ccrm")
    @GetMapping(path = "/rest/ccrm")
    public ResponseEntity<Map> getcccrmBycaseStageforStarterKit(@RequestParam String starterStage) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            List<CCCRMEntity> getList = cccrmEntityService.getListCaseofStarterkit(starterStage);
            if (getList != null && getList.size() > 0) {
                map.put("status_code", "200");
                map.put("results", getList);
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {

                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Please check  starterStage!");
                status = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@getcccrmBycaseStageforStarterKit Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/ccrm", "getListCaseofStarterkit()");
        }
        return new ResponseEntity(map, status);
    }

    @GetMapping("/rest/planningdetails/{caseid}")
    public ResponseEntity<Map> getPlanningDetailsByid(@PathVariable String caseid) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {

            List<Planning> list = planningService.getRecordById(caseid);
            if (list != null && list.size() > 0) {
                map.put("status_code", "200");
                map.put("results", list);
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {

                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Case ID Invalid!");
                status = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@getPlanningDetailsByid Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/planningdetails/{caseid}", "getRecordById()");
        }
        return new ResponseEntity(map, status);
    }


    @GetMapping("/rest/customer360/{caseid}")
    public ResponseEntity<Map> getcustomer360DetailsByid(@PathVariable String caseid, @RequestParam(value = "fields", required = false) final String fields) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        HttpStatus status = null;
        try {
            CCCRMEntity cccrmEntity = cccrmEntityService.getById(Long.parseLong(caseid));
            if (cccrmEntity != null) {

                if (fields != null) {
                    String[] words = fields.split(",");
                    for (String field : words) {
                        System.out.println(field);
                        if (field.equals("plan")) {
                            map1.put(field, planningService.getRecordById(caseid));
                        } else if (field.equals("packing")) {
                            map1.put(field, packingService.getALLPackingByID(caseid));
                        } else if (field.equals("customerDetails")) {
                            map1.put(field, cccrmEntity);
                        } else if (field.equals("dispatch")) {
                            map1.put(field, dispatchService.getDispatchListById(caseid));
                        } else if (field.equals("upload")) {
                            map1.put(field, uploadService.getAllUploadRecordbyId(caseid));
                        } else if (field.equals("stagging")) {
                            map1.put(field, staggingService.getStaggingRecordById(caseid));
                        } else if (field.equals("hollowTag")) {
                            map1.put(field, hollowTagService.getHollowTagListById(caseid));
                        } else if (field.equals("3DP")) {
                            map1.put(field, threeDPrintingService.getThreeDPrintingRecordByID(caseid));
                        } else if (field.equals("lab")) {
                            map1.put(field, labService.getLabRecordById(caseid));
                        } else if (field.equals("fqc")) {
                            map1.put(field, fqcService.getFQCRecordByID(caseid));
                        } else if (field.equals("decision_history")) {
                            map1.put(field, decisionHistoryService.getDecisionHistoryRecordByID(caseid));
                        } else if (field.equals("batchAddress")) {
                            map1.put(field, cccrmEntityService.getAddress(Long.parseLong(caseid), "batch"));
                        } else if (field.equals("starterkitAddress")) {
                            map1.put(field, cccrmEntityService.getAddress(Long.parseLong(caseid), "starterkit"));
                        }

                    }
                } else {
                    map1.put("customerDetails", cccrmEntity);
                    map1.put("plan", planningService.getRecordById(caseid));
                    map1.put("packing", packingService.getALLPackingByID(caseid));
                    map1.put("dispatch", dispatchService.getDispatchListById(caseid));
                    map1.put("upload", uploadService.getAllUploadRecordbyId(caseid));
                    map1.put("stagging", staggingService.getStaggingRecordById(caseid));
                    map1.put("hollowTag", hollowTagService.getHollowTagListById(caseid));
                    map1.put("3DP", threeDPrintingService.getThreeDPrintingRecordByID(caseid));
                    map1.put("lab", labService.getLabRecordById(caseid));
                    map1.put("fqc", fqcService.getFQCRecordByID(caseid));
                    map1.put("decision_history", decisionHistoryService.getDecisionHistoryRecordByID(caseid));
                    map1.put("batchAddress", cccrmEntityService.getAddress(Long.parseLong(caseid), "batch"));
                    map1.put("starterkitAddress", cccrmEntityService.getAddress(Long.parseLong(caseid), "starterkit"));
                }
                map.put("status_code", "200");
                map.put("results", map1);
                map.put("message", "Success");
                status = HttpStatus.OK;

            } else {

                map.put("status_code", "404");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Case ID Invalid!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@getcustomer360DetailsByid Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/customer360/{caseid}", "getById()");
        }

        return new ResponseEntity(map, status);
    }

    @PostMapping("/rest/decisionHistory")
    public ResponseEntity<Map> saveDecisionHistory(@RequestBody DecisionHistory decisionHistory) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
   /*     if(decisionHistory.getDecision()!=null && decisionHistory.getDecision().length()<=100){

                   }else{
                        map.put("status_code", "400");
                        map.put("results", "Decision Can't be null or less than 100");
                        map.put("message", "Bad Request!");
        }*/
        try {
            DecisionHistory decisionHistory1 = decisionHistoryService.saveDecisionHistory(decisionHistory);
            if (decisionHistory1 != null) {
                map.put("status_code", "200");
                map.put("results", decisionHistory1);
                map.put("message", "Success");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", "Data not saved!");
                map.put("message", "Please check your request!");
                status = HttpStatus.NOT_FOUND;
            }


        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.info("@saveDecisionHistory Exception: " + e.getMessage());
            sendEmailService1.sendMail(e.toString(), "/rest/decisionHistory", "saveDecisionHistory()");
        }
        return new ResponseEntity(map, status);


    }

    @GetMapping(value = "/getNotifications")
    public ResponseEntity<Map> getNotifications() {
        List<Dispatche> list = nextBatchReminderService.getALLDispatched();
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        if (list.isEmpty()) {
            map.put("status", 404);
            map.put("message", "Data Not Found");
            status = HttpStatus.NOT_FOUND;
        } else {
            map.put("status", 200);
            map.put("message", "Data Found");
            map.put("data", list);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(map, status);
    }

    @GetMapping("/workReport")
    public ResponseEntity<Map> getWorkReport(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String userId) {
        return decisionHistoryService.getDecisionHistoryByUser(fromDate, toDate, userId);
    }

    @GetMapping("/accountsReport")
    public ResponseEntity<Map> getAccountsReport(@RequestParam String fromDate, @RequestParam String toDate) {
        return accountService.getAccountsData(fromDate, toDate);
    }

    @GetMapping("/caseCountReport")
    public ResponseEntity<Map> getCaseCountReport(@RequestParam String fromDate, @RequestParam String toDate) {
        return decisionHistoryService.getCaseCount(fromDate, toDate);
    }

    @GetMapping("/caseCountByStageReport")
    public ResponseEntity<Map> getcaseCountByStageReport(@RequestParam String fromDate, @RequestParam String toDate) {
        return decisionHistoryService.getStageCaseCount(fromDate, toDate);
    }

    @GetMapping("/nextBatchReport")
    public ResponseEntity<Map> getnextBatchReport() {
        return nextBatchReminderService.getNextBatchDate();
    }

    @PutMapping("/updatePlanStage")
    public ResponseEntity<Map> updatePlanStage(@RequestParam Long caseId,@RequestParam String userId) {
        return cccrmEntityService.updatePlanStage(caseId, userId);
    }

    @PutMapping("/updateDispatchBatchStage")
    public ResponseEntity<Map> updateBatchStage(@RequestBody Dispatch dispatchData) {
        return cccrmEntityService.updateBatchStage(dispatchData);
    }

    @PutMapping("/updateDispatchStarterkitStage")
    public ResponseEntity<Map> updateStarterkitStage(@RequestBody Dispatch dispatchData) {
        return cccrmEntityService.updateStarterkitStage(dispatchData);
    }
    /*
     Created By - Neeraj Kumar Singh Date- 27/12/2022
     UPDATE CC_CRM Table PROCEDURE

     This Procedure mainly Create for Update two column of cc_CRM table -
     @param ids - You can pass multiple ids as well as single
     @param status - Billed, Unbilled, Cancelled , Dispute, issue CN, CN Issued, Collected, To Bill
     @param remarks - Can't be null or empty if payment bill status is - cancelled or dispute
     */

    @PutMapping("/updatePaymentBillStatus")
    public ResponseEntity<Map> updatePaymentBillStatus(@RequestBody List<DispatchUpdateReport> list) {
        return cccrmEntityService.updatePayment_Bill_Status(list);
    }

    /**
     Created By - Neeraj Kumar Singh Date- 27/12/2022
     Dispatch Report Procedure

     Mainly created to get dispatch report of cases in a perticular time period.


    @param Case_Type  - (Clove, 32Watts, Study, Institutional, Digital, Study, Planned, Retail)
    @param Dispatch_Type   - (Aligner, Starter kit)
   @param Request_Type    - (New Case, Next Batch Required, Mid Scan, Retainer,Refinment, Rescan) if pass empty value ('') it return
     all Type record.
    @param Extra_Aligner   - (Yes, No)
    @param Payment_Status   - (Unbilled, Billed, To bill, Collected, cancelled, Dispute, Issue CN, CN Issued)

     */

    @GetMapping("/getWattsDispatchReports")
    public ResponseEntity<Map> getWattsDispatchReports(@RequestParam String FromDate,@RequestParam String ToDate,@RequestParam String Case_Type,@RequestParam String Dispatch_Type,@RequestParam String Request_Type,@RequestParam String Extra_Aligner,@RequestParam String Payment_Status){
        return cccrmEntityService.getWattsDispatchReports(FromDate, ToDate, Case_Type, Dispatch_Type, Request_Type, Extra_Aligner, Payment_Status);
    }

    @GetMapping("/getNextBatchReport")
    public ResponseEntity<Map> getNextBatchReport(@RequestParam String Case_Type, @RequestParam String FromDate, @RequestParam String ToDate) {
        return nextBatchReminderService.getNextBatchReport(Case_Type, FromDate, ToDate);
    }

    @GetMapping("/getPaymentReport")
    public ResponseEntity<Map> getPaymentReport(@RequestParam String FromDate, @RequestParam String ToDate, @RequestParam String Case_Type) {
        return cccrmEntityService.getPaymentReport(FromDate, ToDate, Case_Type);
    }

    @GetMapping("/getCaseStatusReport")
    public ResponseEntity<Map> getCaseStatusReport(@RequestParam String Case_Type, @RequestParam String Case_Status, @RequestParam String FromDate, @RequestParam String ToDate) {
        return cccrmEntityService.getCaseStatusReport(Case_Type, Case_Status, FromDate, ToDate);
    }

}
