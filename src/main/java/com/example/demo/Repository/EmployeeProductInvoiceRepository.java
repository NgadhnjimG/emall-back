package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import com.example.demo.Model.EmployeeProductInvoice;
import com.example.demo.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeProductInvoiceRepository extends JpaRepository<EmployeeProductInvoice, Integer> {
//    @Query("select epi. from #{#entityName} epi where employee=invoice")
//    Employee findByInvoice(Invoice invoice);
    @Query("select epi from EmployeeProductInvoice epi join Employee e on epi.employee.empID=e.empID join Company c on c.companyID=e.company.companyID where c.companyID=?1")
    List<EmployeeProductInvoice> getAllByCompanyId(int companyID);
}
