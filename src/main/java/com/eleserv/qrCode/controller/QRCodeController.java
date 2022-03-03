package com.eleserv.qrCode.controller;


import com.eleserv.qrCode.entity.NextBatchReminder;
import com.eleserv.qrCode.service.LeadsService;
import com.eleserv.qrCode.service.NextBatchReminderService;
import com.eleserv.qrCode.service.QRCodeService;
import com.eleserv.qrCode.service.SendEmailService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
    public StreamingResponseBody demo(@PathVariable("caseid") String caseid,HttpServletResponse response) throws IOException  {
        String caseno= leadsService.checkCaseid(caseid);
        if(caseno!=null){
        try {

     //   qrCodeService.generateQRCODE(caseid);
    } catch (Exception e) {
        e.printStackTrace();
    }
        String file = env.getProperty("app.dynamicFile")+caseid+".pdf";
        String QRName = "QR"+caseid+".pdf";
        String t1= "attachment; filename=\""+QRName+"\"";
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
        }else{
        return outputStream -> {};
    }


    }



}
