package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.SaleAgent;
import com.example.demo.Repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleAgentService implements ISaleAgentService {
    @Autowired AgentRepository repo;

    public void test(){

    }

    @Override
    public void MyFunction() {
        var x=repo.findByName("arber");
        String k="";
    }
}
