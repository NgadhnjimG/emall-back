package com.example.demo.Service;

import com.example.demo.Helpers.InvoiceResponse;
import com.example.demo.Helpers.Mocks.ProductsMoneyGivenMock;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface IInvoiceService {
    InvoiceResponse calculateInvoice(HashMap<Product,Integer> products, int employeeID, ProductsMoneyGivenMock productsMoneyGivenMock);

    Invoice getInvoiceById(int id);

    List<Invoice> retrieveAllInvoicesByEmployee(int employeeID);

    List<Invoice> retrieveInvoicesByEmployeeByDate(int employeeID, LocalDate date);

    void deleteOneInvoice(int invoiceid);

    void cancelInvoice(int invoiceid) throws Exception;

    List<Invoice> retrieveAllCancelledInvoicesByEmployee(int employeeid);
    List<Invoice> retrieveCancelledInvoicesByEmployeeByDate(int employeeID, LocalDate date);

    void restoreInvoice(int invoiceID);
}
