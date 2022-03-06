package com.example.demo.Controller;

import com.example.demo.Helpers.InvoiceResponse;
import com.example.demo.Helpers.Mocks.ProductsMoneyGivenMock;
import com.example.demo.Helpers.ProductHelper;
import com.example.demo.Helpers.Responses.GeneralResponse;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Helpers.Validators.JWTwithUserRequestDataValidation;
import com.example.demo.LogsDefinition.WriteLog;
import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.Privileges;
import com.example.demo.Service.IEmployeeService;
import com.example.demo.Service.IInvoiceService;
import com.example.demo.auth.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private ProductHelper productHelper;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTwithUserRequestDataValidation jwTwithUserRequestDataValidation;

    @PostMapping(path = "/{employeeid}/generate/invoice")
    public ResponseEntity getScannedProducts(@PathVariable int employeeid, @RequestBody ProductsMoneyGivenMock invoice,HttpServletRequest request,HttpSession session) {
        if (!jwTwithUserRequestDataValidation.validateIdSentByUserAgainstJWT(request,employeeid,session))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials do not match with token");
        jwTwithUserRequestDataValidation.setSession(request,session);
        InvoiceResponse invoiceResponse = invoiceService.calculateInvoice(productHelper.getProductList(invoice), employeeid,invoice);
        if (invoiceResponse == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(invoiceResponse,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/Privileges/set")
    public ResponseEntity setPrivileges(@PathVariable int id, @RequestBody List<Privileges> privileges,HttpSession session,HttpServletRequest request) {
        jwTwithUserRequestDataValidation.setSession(request,session);
        try {
            employeeService.setPrivileges(privileges, id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(null,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findBy/invoice/{invoiceid}")
    public ResponseEntity findEmployeeByInvoiceID(@PathVariable int invoiceid,HttpSession session,HttpServletRequest request) {
        jwTwithUserRequestDataValidation.setSession(request,session);
        Employee employee = employeeService.getEmployeeOfInvoice(invoiceService.getInvoiceById(invoiceid));
        if (employee == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(employee,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/today/all/invoices/{employeeid}")
    public ResponseEntity getAllInvoicesDoneToday(@PathVariable int employeeid, HttpServletRequest request, HttpSession session) {
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

    @GetMapping("test")
    public void test() throws IOException {
        new WriteLog().writeLog();
    }



}
