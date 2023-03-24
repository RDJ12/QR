package com.eleserv.qrCode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;

@Service
public class QRCodeService {
    Logger logger = LoggerFactory.getLogger(QRCodeService.class);
    @Autowired
    private Environment env;
    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }

    public  void generateQRCODE(String caseid,String doctorName,String patientName) throws WriterException, IOException,
            NotFoundException, DocumentException, MalformedURLException, IOException {
        logger.info("@Start generateQRCODE "+caseid);
       try{
           String para1 = caseid;
           String DoctorName="DR:"+doctorName;
           String PatientName = "PT:"+patientName;
           // Creating Paragraphs

           String qrCodeText = env.getProperty("app.qrCodeText")+"?id="+caseid;
        String filePath = env.getProperty("app.dynamicpicture")+caseid+".png";
        String file = env.getProperty("app.dynamicFile")+caseid+".pdf";
        int size = 250;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        Font f1= new Font(Font.FontFamily.TIMES_ROMAN,55,Font.BOLD);
           Font f2= new Font(Font.FontFamily.TIMES_ROMAN,30,Font.BOLD);
           //Font f3= new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
        Paragraph paragraph1 = new Paragraph(para1,f1);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph1);
        Paragraph paragraph3 = new Paragraph(PatientName,f2);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph3);
        Paragraph paragraph2 = new Paragraph(DoctorName,f2);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph2);

        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(filePath);

        img.setAlignment(Element.ALIGN_CENTER);
        document.add(img);
        /*
        * This Change made by Neeraj Kumar Singh
        *
        * Prakash wanted to decrease the size page so that we need remove 3 qr code
        *
        *
        * Date:- 18 Nov 2022 14:00 hrs
        *
        *
        * */
//        com.itextpdf.text.Image img1 = com.itextpdf.text.Image.getInstance(filePath);
//        img1.setAbsolutePosition(300f, 288f);
//        document.add(img1);
//        com.itextpdf.text.Image img2 = com.itextpdf.text.Image.getInstance(filePath);
//           img2.setAbsolutePosition(38f, 08f);
//        document.add(img2);
//        com.itextpdf.text.Image img3 = Image.getInstance(filePath);
//        img3.setAbsolutePosition(300f, 08f);
//        document.add(img3);
        document.close();
        logger.info("File Done...");}catch (Exception e){
           logger.error("Exception="+e.getMessage());
       }
    }

    public  void generateQRCODE1(String caseid) throws WriterException, IOException,
            NotFoundException, DocumentException, MalformedURLException, IOException {
        logger.info("@Start generateQRCODE "+caseid);
        try{
            String qrCodeText = env.getProperty("app.qrCodeText1")+"?cid="+caseid;
            String filePath = env.getProperty("app.dynamicpicture")+caseid+".png";
            String file = env.getProperty("app.dynamicFile")+caseid+".pdf";
            int size = 300;
            String fileType = "png";
            File qrFile = new File(filePath);
            createQRImage(qrFile, qrCodeText, size, fileType);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(filePath);
            document.add(img);
            com.itextpdf.text.Image img1 = com.itextpdf.text.Image.getInstance(filePath);
            img1.setAbsolutePosition(300f, 505f);
            document.add(img1);
            com.itextpdf.text.Image img2 = com.itextpdf.text.Image.getInstance(filePath);
            document.add(img2);
            com.itextpdf.text.Image img3 = Image.getInstance(filePath);
            img3.setAbsolutePosition(300f, 207f);
            document.add(img3);
            document.close();
            logger.info("File Done...");}catch (Exception e){
            logger.error("Exception="+e.getMessage());
        }
    }


}
