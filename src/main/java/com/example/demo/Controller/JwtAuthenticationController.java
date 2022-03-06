package com.example.demo.Controller;

import java.util.Objects;

import com.example.demo.Helpers.UnauthorizedRequest;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Helpers.Validators.JWTwithUserRequestDataValidation;
import com.example.demo.auth.JwtRequest;
import com.example.demo.auth.JwtResponse;
import com.example.demo.auth.JwtTokenUtil;
import com.example.demo.auth.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JWTwithUserRequestDataValidation jwTwithUserRequestDataValidation;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws Exception {

        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails;
        try {
             userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>("You are not authorized to make this call", HttpStatus.BAD_REQUEST);
        }
        if (!bcryptEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
            return new ResponseEntity<>("You are not authorized to make this call", HttpStatus.BAD_REQUEST);
        }

        Cookie cookie=new Cookie("username", authenticationRequest.getUsername());
        response.addCookie(cookie);
        UserInfoHelper.setUserDetails(userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new JwtResponse(token,jwTwithUserRequestDataValidation.getUserIdByUsername(authenticationRequest.getUsername())));
    }
}
