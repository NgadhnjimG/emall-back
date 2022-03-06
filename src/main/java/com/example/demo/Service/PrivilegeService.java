package com.example.demo.Service;

import com.example.demo.Model.Privileges;
import com.example.demo.Repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService implements PrivilegeInterface {

    @Autowired
    private PrivilegeRepository repo;
    @Override
    public Privileges getPrivilegeById(int id) {
        return repo.findById(id);
    }
}
