package com.example.demo.Helpers.Validators;

import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Model.Employee;
import com.example.demo.Model.EmployeeProductInvoice;
import com.example.demo.Model.Invoice;
import com.example.demo.Repository.EmployeeBaseRepository;
import com.example.demo.Repository.EmployeeProductInvoiceRepository;
import com.example.demo.Service.EmployeeInterface;
import com.example.demo.Service.IEmployeeService;
import com.example.demo.Service.InvoiceInterface;
import com.example.demo.auth.JwtTokenUtil;
import com.example.demo.auth.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Iterator;

@Service
public class JWTwithUserRequestDataValidation {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @Autowired
    private IEmployeeService employeeInterface;

    @Autowired
    private InvoiceInterface invoiceInterface;

    public boolean validateIdSentByUserAgainstJWT(HttpServletRequest request, int userID, HttpSession session) {
        String username = null;
        String jwtToken = decomposeToken(request);
        if (jwtToken == null) return false;
        try {
            username = getUsernameFromJWT(jwtToken);

            if (userID == getUserIdByUsername(username)) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }

        return false;
    }

    private String getUsernameFromJWT(String jwt) {
        String username = jwtTokenUtil.getUsernameFromToken(jwt);
        return username;
    }

    public UserDetails getUserDetailsByUsername(String username) {
        return jwtUserDetailsService.loadUserByUsername(username);
    }

    public int getUserIdByUsername(String username) {
        Employee employee = employeeInterface.verifyLogin(username);
        return employee.getEmpID();
    }

    public void setSession(HttpServletRequest request, HttpSession session) {
        String username = null;
        String jwtToken = decomposeToken(request);
        if (jwtToken == null) return;
        try {
            username = getUsernameFromJWT(jwtToken);
            session.setAttribute("username", username);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }

    }

    private String decomposeToken(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
        }
        return jwtToken;
    }

    public boolean validateInvoiceIDWithJWT(HttpServletRequest request, int invoiceID){
        String username = null;
        int userID=0;
        String jwtToken = decomposeToken(request);
        try {
            username = getUsernameFromJWT(jwtToken);
            userID=getUserIdByUsername(username);
            Invoice invoice=this.invoiceInterface.findInvoiceByInvoiceId(invoiceID);
            Iterator<EmployeeProductInvoice> EPI=invoice.getEmployeeProductInvoices().iterator();
            if(EPI.next().getEmployee().getEmpID()==userID){
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }
        return false;
    }

}
