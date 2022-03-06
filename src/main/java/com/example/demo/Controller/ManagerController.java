package com.example.demo.Controller;

import com.example.demo.Helpers.EmployeeInvoiceHelper;
import com.example.demo.Helpers.Mocks.EmployeeMock;
import com.example.demo.Helpers.Mocks.InvoiceMock;
import com.example.demo.Helpers.Recievers.TimePeriod;
import com.example.demo.Helpers.Responses.GeneralResponse;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Helpers.Validators.JWTwithUserRequestDataValidation;
import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;
import com.example.demo.Service.IManagerService;
import com.example.demo.auth.JwtTokenUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private IManagerService managerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTwithUserRequestDataValidation jwTwithUserRequestDataValidation;

    @GetMapping("/invoices/all/today")
    public ResponseEntity getAllInvoicesAndEmployees(@CookieValue(value = "username", defaultValue = "notAuthorized")String username, HttpSession session, HttpServletRequest request){
        if(username.toLowerCase().equals("notauthorized")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        jwTwithUserRequestDataValidation.setSession(request,session);
        List<EmployeeInvoiceHelper> list=this.managerService.getAllInvoicesOfEmployeesForToday();
        if(list==null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(list,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/invoices/all")
    public ResponseEntity getAllInvoicesAndEmployeesForSpecificTimePeriod(@RequestBody TimePeriod timePeriod, HttpSession session,HttpServletRequest request){
        List<EmployeeInvoiceHelper> list=this.managerService.getAllInvoicesOfEmployeesForToday();
        jwTwithUserRequestDataValidation.setSession(request,session);
        if(list==null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        GeneralResponse response=new GeneralResponse(list,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
