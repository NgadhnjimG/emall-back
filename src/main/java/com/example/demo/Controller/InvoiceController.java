package com.example.demo.Controller;

import com.example.demo.Helpers.Responses.GeneralResponse;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Helpers.Validators.JWTwithUserRequestDataValidation;
import com.example.demo.Model.Invoice;
import com.example.demo.Service.IInvoiceService;
import com.example.demo.auth.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTwithUserRequestDataValidation jwTwithUserRequestDataValidation;


    @PutMapping("")
    public ResponseEntity cancelInvoice(@RequestBody Invoice invoice, HttpServletRequest request, HttpSession session){
        if (!jwTwithUserRequestDataValidation.validateInvoiceIDWithJWT(request,invoice.getInvoiceID()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User credentials do not match with invoice");
        jwTwithUserRequestDataValidation.setSession(request,session);
        try {
            this.invoiceService.cancelInvoice(invoice.getInvoiceID());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invoice doesnt exist");
        }
        GeneralResponse response=new GeneralResponse("Invoice cancelled",jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/restore")
    public ResponseEntity restoreInvoice(@RequestBody Invoice invoice, HttpServletRequest request, HttpSession session){
        if (!jwTwithUserRequestDataValidation.validateInvoiceIDWithJWT(request,invoice.getInvoiceID()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User credentials do not match with invoice");
        jwTwithUserRequestDataValidation.setSession(request,session);
        try {
            this.invoiceService.restoreInvoice(invoice.getInvoiceID());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invoice doesnt exist");
        }
        GeneralResponse response=new GeneralResponse("Invoice restored",jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{employeeid}/get/all")
    public ResponseEntity getAllInvoicesByEmployee(@PathVariable int employeeid, HttpServletRequest request, HttpSession session){
        if (!jwTwithUserRequestDataValidation.validateIdSentByUserAgainstJWT(request,employeeid,session))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials do not match with token");
        jwTwithUserRequestDataValidation.setSession(request,session);
        List<Invoice> invoiceList = this.invoiceService.retrieveAllInvoicesByEmployee(employeeid);
        if (invoiceList == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(invoiceList,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{employeeid}/get/all/cancelled")
    public ResponseEntity getAllCancelledInvoicesByEmployee(@PathVariable int employeeid, HttpServletRequest request, HttpSession session){
        if (!jwTwithUserRequestDataValidation.validateIdSentByUserAgainstJWT(request,employeeid,session))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials do not match with token");
        jwTwithUserRequestDataValidation.setSession(request,session);
        List<Invoice> invoiceList = this.invoiceService.retrieveAllCancelledInvoicesByEmployee(employeeid);
        if (invoiceList == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(invoiceList,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{employeeid}/get/by/{date}")
    public ResponseEntity getAllInvoicesByEmployee(@PathVariable int employeeid,HttpSession session, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, HttpServletRequest request){
        if (!jwTwithUserRequestDataValidation.validateIdSentByUserAgainstJWT(request,employeeid,session))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials do not match with token");
        jwTwithUserRequestDataValidation.setSession(request,session);
        List<Invoice> invoiceList = this.invoiceService.retrieveInvoicesByEmployeeByDate(employeeid,date);
        if (invoiceList == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(invoiceList,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{employeeid}/get/cancelled/by/{date}")
    public ResponseEntity getAllCancelledInvoicesByEmployee(@PathVariable int employeeid,HttpSession session, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, HttpServletRequest request){
        if (!jwTwithUserRequestDataValidation.validateIdSentByUserAgainstJWT(request,employeeid,session))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials do not match with token");
        jwTwithUserRequestDataValidation.setSession(request,session);
        List<Invoice> invoiceList = this.invoiceService.retrieveCancelledInvoicesByEmployeeByDate(employeeid,date);
        if (invoiceList == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(invoiceList,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
