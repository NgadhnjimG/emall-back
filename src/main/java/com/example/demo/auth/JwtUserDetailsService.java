package com.example.demo.auth;
import java.util.ArrayList;

import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IEmployeeService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var employee=service.verifyLogin(username);
        if (employee!=null) {
            UserInfoHelper.setCompanyID(employee.getCompany().getCompanyID());
            UserInfoHelper.setEmpID(employee.getEmpID());
            return new User(employee.getEmpName(), employee.getEmpPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }



}
