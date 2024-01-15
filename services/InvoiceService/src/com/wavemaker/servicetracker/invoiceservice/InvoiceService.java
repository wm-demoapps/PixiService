/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.invoiceservice;

import com.lowagie.text.DocumentException;
import com.wavemaker.runtime.commons.file.model.DownloadResponse;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.servicetracker.invoiceservice.model.InvoiceRequest;
import com.wavemaker.servicetracker.supportdb.PartSale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//import com.wavemaker.servicetracker.invoiceservice.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 *
 * NOTE: We do not recommend using method overloading on client exposed methods.
 */
@ExposeToClient
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private SecurityService securityService;

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    public Downloadable generatePDF(InvoiceRequest invoiceRequest) throws IOException, DocumentException {
        File input = ResourceUtils.getFile("classpath:InvoiceTemplate.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        String htmlString = doc.toString();

        htmlString = htmlString.replace("${CustomerName}", invoiceRequest.getRma().getJobRequest().getProductSerial().getCustomer().getName());
        htmlString = htmlString.replace("${CustomerAddress}", invoiceRequest.getRma().getFullAddress());
        htmlString = htmlString.replace("${CustomerMobileNumber}", invoiceRequest.getRma().getJobRequest().getProductSerial().getCustomer().getMobile());

        htmlString = htmlString.replace("${EngineerName}", invoiceRequest.getRma().getEngineerAppointment().getEngineer().getName());
        htmlString = htmlString.replace("${EngineerId}", invoiceRequest.getRma().getEngineerAppointment().getEngineer().getEngineerId());

        htmlString = htmlString.replace("${IssuedDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        htmlString = htmlString.replace("${ActivityDate}", new SimpleDateFormat("yyyy-MM-dd").format(invoiceRequest.getRepairActivity().getDate()) + " " +new SimpleDateFormat("hh:mm a").format(invoiceRequest.getRepairActivity().getStartTime()) + " - " +new SimpleDateFormat("hh:mm a").format(invoiceRequest.getRepairActivity().getEndTime()));

        htmlString = htmlString.replace("${FaultAnalysis}", invoiceRequest.getRepairActivity().getFaultAnalysis() != null ? invoiceRequest.getRepairActivity().getFaultAnalysis().getAnalysis(): "");

        StringBuilder partsHTML = new StringBuilder();
        if(invoiceRequest.getPartSales() != null && invoiceRequest.getPartSales().size() > 0){
            for (PartSale partSale : invoiceRequest.getPartSales()) {
                if(partSale != null) {
                    partsHTML.append("<tr>")
                            .append("<td>").append(partSale.getPart().getName()).append("</td>")
                            .append("<td>").append("₹").append(partSale.getPart().getPrice()).append("</td>")
                            .append("<td>").append(partSale.getQuantity()).append("</td>")
                            .append("<td>").append(partSale.getDiscount()).append("%").append("</td>")
                            .append("<td>").append("₹").append(partSale.getPrice()).append("</td>")
                            .append("</tr>");
                }
            }
        }

        htmlString = htmlString.replace("${parts}", partsHTML.toString());

        htmlString = htmlString.replace("${ServiceCharge}", "₹"+NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(invoiceRequest.getRma().getQuotationAmount()));

        Double partsTotal = invoiceRequest.getPartSales().stream().mapToDouble(PartSale::getPrice).sum();
        Double subTotal = partsTotal + invoiceRequest.getRma().getQuotationAmount();
        Double gstAmount = (subTotal * 18) / 100;
        htmlString = htmlString.replace("${GSTPrice}", "₹"+NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(gstAmount));

        Double grandTotal = subTotal+gstAmount;
        htmlString = htmlString.replace("${GrandTotal}", "₹"+NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(grandTotal));

        // Generate the PDF
        DownloadResponse downloadableResponse = getDownloadResponse(htmlString);
        return downloadableResponse;
    }

    private static DownloadResponse getDownloadResponse(String htmlString) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlString);
        renderer.layout();
        renderer.createPDF(outputStream);
        renderer.finishPDF();
        byte[] pdfBytes = outputStream.toByteArray();

        // Use the pdfBytes as needed
        DownloadResponse downloadableResponse = new DownloadResponse(new ByteArrayInputStream(pdfBytes), "application/pdf", "Invoice.pdf");
        downloadableResponse.setInline(false);
        return downloadableResponse;
    }

}
