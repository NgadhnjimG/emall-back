package com.example.demo.Controller;

import com.example.demo.Helpers.Responses.GeneralResponse;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Helpers.Validators.JWTwithUserRequestDataValidation;
import com.example.demo.Model.Product;
import com.example.demo.Service.IProductService;
import com.example.demo.auth.JwtTokenUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IProductService productService;

    @Autowired
    private JWTwithUserRequestDataValidation jwTwithUserRequestDataValidation;

    @GetMapping()
    public ResponseEntity getAllProducts(HttpServletRequest request, HttpSession session){
        jwTwithUserRequestDataValidation.setSession(request,session);
        List<Product> productList=this.productService.getAllProducts();

        GeneralResponse response=new GeneralResponse(productList,jwtTokenUtil.generateToken(jwTwithUserRequestDataValidation.getUserDetailsByUsername(session.getAttribute("username").toString())));
        return ResponseEntity.ok(response);
    }
}
