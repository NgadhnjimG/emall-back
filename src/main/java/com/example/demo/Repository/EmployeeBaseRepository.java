package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoRepositoryBean
@Component
public interface EmployeeBaseRepository<T extends Employee> extends CrudRepository<T,Integer> {
    @Query("select emp from  #{#entityName} emp where emp.empName=?1")
    T findByName(String userName);

    @Query("select e.empID from Employee e where e.empUserName=?1")
    int findEmployeeIdFromUsername(String username);

}
