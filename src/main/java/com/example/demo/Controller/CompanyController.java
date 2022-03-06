package com.example.demo.Controller;

import com.example.demo.Helpers.Responses.GeneralResponse;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Helpers.Validators.JWTwithUserRequestDataValidation;
import com.example.demo.Model.Employee;
import com.example.demo.Service.ICompanyService;
import com.example.demo.auth.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTwithUserRequestDataValidation jwTwithUserRequestDataValidation;

    @PostMapping("/{companyid}/employees/add")
    public ResponseEntity addEmployeesToCompany(@PathVariable int companyid, @RequestBody List<Employee> employeeList, HttpSession session, HttpServletRequest request){
        jwTwithUserRequestDataValidation.setSession(request,session);
        try {
            companyService.addEmployees(employeeList, companyid);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GeneralResponse response=new GeneralResponse(null,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));

        return ResponseEntity.ok().body(response);
    }
}
