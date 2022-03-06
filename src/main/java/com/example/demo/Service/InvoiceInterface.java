package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;

public interface InvoiceInterface {
    Employee findEmployeeByInvoice(int invoiceID);

    Invoice findInvoiceByInvoiceId(int invoiceID);
}
