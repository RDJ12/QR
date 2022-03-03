package com.eleserv.qrCode.service;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.stream.Collectors;
import com.eleserv.qrCode.entity.NextBatchReminder;
import com.eleserv.qrCode.reposistory.NextBatchReminderReposistory;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import org.springframework.core.env.Environment;

import java.io.*;


import com.eleserv.qrCode.entity.Dispatche;

@Service
public class NextBatchReminderService {
    @Autowired
    private NextBatchReminderReposistory nextBatchReminderReposistory;
    @Autowired
    private Environment env;
    @Autowired
    private LeadsService leadsService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
   /* public List<NextBatchReminder> getNextBatchReminder(String next_date){
        return nextBatchReminderReposistory.getNext_Batch_details(next_date);
    }*/
    public List<Dispatche> getALLDispatched(){
        List<Dispatche> result=null;
        result = nextBatchReminderReposistory.getALLDispatchedCases();
        result.forEach(n->{
            if(n.getCase_id()!=null && n.getLower_aligner_from() !=null && n.getLower_aligner_to() !=null && n.getUpper_aligner_from() !=null && n.getUpper_aligner_to() !=null) {
                System.out.println(n.getCase_id());
                Long caseid = Long.valueOf(n.getCase_id());
               try
               {
                   int Lower_aligner_to_dispatched= Integer.parseInt(n.getLower_aligner_to());
                   String id = String.valueOf(nextBatchReminderReposistory.getPlanningId(Long.valueOf(n.getCase_id())));
                if(id!=null) {
                    System.out.println(id);
                    String getPlanningDetails = nextBatchReminderReposistory.getPlanningDetails(Integer.parseInt(id));
                    if(getPlanningDetails!=null){
                        int remainingLower=Integer.parseInt(getPlanningDetails)-Lower_aligner_to_dispatched;
                            if(remainingLower>0) {

                                int addedDays = ((remainingLower-1)*15);
                                System.out.println("remainingLower="+remainingLower+" || addedDays="+addedDays);
                                Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(n.getDate());
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date1);
                                cal.add(Calendar.DATE, addedDays);
                                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String formatted = format1.format(cal.getTime());
                                System.out.println(formatted);
                                nextBatchReminderReposistory.updateUser(formatted,caseid);

                            }
                    }

                }
               }catch (Exception e){
                   System.out.println(e.getMessage());
               }
            }
        });
        return result;
    }
    public void sendMailWorkflow(){
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.DATE, 50);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatted = format1.format(cal.getTime());
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("date : "+date+ " formatted : "+formatted);
        Set<Dispatche> nextBatchReminderList1=nextBatchReminderReposistory.getsechuledata(date,formatted);
        Set<Dispatche> nextBatchReminderList=new HashSet<>();
        Map<String,Dispatche> map =new HashMap<>();

        nextBatchReminderList1.forEach(n->{
           map.put(n.getCase_id(),n);
        });
        map.forEach( (k,v) ->
                        nextBatchReminderList.add(v)
        );
        System.out.println("::::::::::"+map);
        if(nextBatchReminderList.size()>0) {
            String dest = env.getProperty("app.dynamicpicture") + "nextbatch.pdf";
            PdfDocument pdfDoc = null;
            try {
                pdfDoc = new PdfDocument(new PdfWriter(dest));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Document doc = new Document(pdfDoc, PageSize.A4.rotate());

           float[] columnWidths = {5, 5, 5, 5, 5, 2, 2, 2, 2, 2};
           // float[] columnWidths = {5, 5, 5, 5};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));

            String today = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt);

            PdfFont f = null;
            try {
                f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Cell cell = new Cell(1, 10)
                    .add(new Paragraph("Next Batch Report On " + today))
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
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Doctor Name")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("next_date")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Total Aligner")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph(" upper_aligner_to")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("upper_aligner_from")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_aligner_from")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_aligner_to")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Next Batch Date"))
                        /*  new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_aligner_from")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_aligner_to")),
                        // new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("upper_date_in_days")),
                        //new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("lower_date_in_days"))
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("dispatch_date")),
                        new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("next_date"))*/

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
                System.out.println("Data:::::::=="+nextBatchReminder.getCase_id()+" :::::::: =="+nextBatchReminder.getPlanning_id()+" :::::::::::="+nextBatchReminder.getUpper_aligner_to());
                // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(nextBatchReminder.getNextbtch_id()))));
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getCase_id())));
               table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getPatient_name())));
              // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDoctor_name())));
                if(nextBatchReminder.getDoctor_name()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDoctor_name())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
                if(nextBatchReminder.getDate()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDate())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
               if(nextBatchReminder.getPlanning_id()!=null) {
                   String id =nextBatchReminderReposistory.getTotalAligners(nextBatchReminder.getPlanning_id());
                   if(id!=null)
                   table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(id)));
                   else
                       table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph( " ")));
               } else
               table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
                if(nextBatchReminder.getUpper_aligner_to()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getUpper_aligner_to())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
                if(nextBatchReminder.getUpper_aligner_from()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getUpper_aligner_from())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
                if(nextBatchReminder.getLower_aligner_from()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getLower_aligner_from())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
                if(nextBatchReminder.getLower_aligner_to()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getLower_aligner_to())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));


                if(nextBatchReminder.getNext_batch_date()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getNext_batch_date())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));
               /* if(nextBatchReminder.getDate()!=null)
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDate())));
                else
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(" ")));*/
                //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getUpper_aligner_to())));
                //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getUpper_aligner_from())));
                //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getLower_aligner_from())));
                //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getLower_aligner_to())));
                ///  table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(nextBatchReminder.getUpper_date_in_days()))));
                // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(nextBatchReminder.getLower_date_in_days()))));
               // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDate())));
                //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(nextBatchReminder.getDate())));
            });


            // for (int counter = 0; counter < 100; counter++) {
            //   table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(counter + 1))));
            // table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("key " + (counter + 1))));
            //table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + (counter + 1))));
            //}

            doc.add(table);

            doc.close();
            System.out.println("Table created successfully..");
            String body = "Hi All,\nPlease find the attachment.\n\n\n\n\nnote:This is auto-generated mail send by system";
           sendEmailService.sendMail(body);
        }
    }
}
