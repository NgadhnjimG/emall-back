package com.example.demo.Service;

import com.example.demo.Helpers.InvoiceResponse;
import com.example.demo.Helpers.Mocks.ProductsMoneyGivenMock;
import com.example.demo.Model.*;
import com.example.demo.Repository.EmployeeProductInvoiceRepository;
import com.example.demo.Repository.InvoiceRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService, InvoiceInterface {
    private Invoice invoice;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeInterface employeeInterface;

    @Autowired
    private EmployeeProductInvoiceRepository joinedEmpInvProdRepo;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public InvoiceResponse calculateInvoice(HashMap<Product, Integer> products, int employeeID, ProductsMoneyGivenMock productsMoneyGivenMock) {

        double totalPrice = 0;
        Employee employee = getEmployee(employeeID); //get employee

        Set<Product> productSet = products.keySet();//helper
        Iterator<Product> productIterator = productSet.iterator();//helper
        List<EmployeeProductInvoice> toBeSaved = new ArrayList<EmployeeProductInvoice>(); //helper

        EmployeeProductInvoice employeeProductInvoice;
        Product product;
        Invoice invoice = new Invoice();
        invoice = invoiceRepository.save(invoice);
        while (productIterator.hasNext()) {
            product = productIterator.next();
            employeeProductInvoice = new EmployeeProductInvoice();
            employeeProductInvoice.setEmployee(employee);
            employeeProductInvoice.setInvoice(invoice);
            employeeProductInvoice.setProduct(product);
            employeeProductInvoice.setAmmount(products.get(product));
            employeeProductInvoice.setLocalDate(LocalDate.now());
            employeeProductInvoice.setLocalTime(LocalTime.now());
            toBeSaved.add(employeeProductInvoice);
            totalPrice = calculatePrice(product, products.get(product), totalPrice);
        }
        invoice.setTotalPrice(totalPrice);
        invoice.setCashMoneyGiven(productsMoneyGivenMock.getCashMoneyGiven());
        invoice.setCardMoneyGiven(productsMoneyGivenMock.getCardMoneyGiven());
        invoice.setDate(LocalDateTime.now());

        Iterator<String> stringIterator = Arrays.stream(productsMoneyGivenMock.getPaymentTypes()).iterator();

        while (stringIterator.hasNext()) {
            if (stringIterator.next().toLowerCase().equals("card")) {
                invoice.setPaymentType(PaymentType.CARD);
                break;
            }
        }
        if (invoice.getPaymentType() == null) invoice.setPaymentType(PaymentType.CASH);
        invoice.setChange(defineChange(totalPrice, productsMoneyGivenMock));
        joinedEmpInvProdRepo.saveAll(toBeSaved);
        HashMap<Invoice, Set<Product>> fullInvoice = new HashMap<>();
        fullInvoice.put(invoice, productSet);
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setEmployee(employee);
        invoiceResponse.setInvoice(invoice);
        invoiceResponse.setProduct(productSet);
        return invoiceResponse;
    }

    private double defineChange(double totalPrice, ProductsMoneyGivenMock productsMoneyGivenMock) {
        double change = totalPrice - (productsMoneyGivenMock.getCardMoneyGiven() + productsMoneyGivenMock.getCashMoneyGiven());
        return change * -1;
    }

    @Override
    public Invoice getInvoiceById(int id) {
        return this.invoiceRepository.findByInvoiceID(id);
    }

    private double calculatePrice(Product product, int ammount, double totalPrice) {
        return totalPrice + (product.getProductPrice() * ammount);
    }

    private Employee getEmployee(int employeeID) {
        return employeeInterface.getEmployeeById(employeeID);
    }

    @Override
    public Employee findEmployeeByInvoice(int invoiceID) {

        invoice = this.invoiceRepository.findByInvoiceID(invoiceID);
        Employee employee = invoice.getEmployeeProductInvoices().iterator().next().getEmployee();
        return employee;
    }

    @Override
    public Invoice findInvoiceByInvoiceId(int invoiceID) {
        return this.invoiceRepository.findByInvoiceID(invoiceID);
    }

    @Override
    public List<Invoice> retrieveAllInvoicesByEmployee(int employeeID) {
        Employee employee = this.employeeInterface.getEmployeeById(employeeID);
        List<Invoice> list = distinctInvoices(employee.getEmployeeProductInvoices());
        return list;
    }

    @Override
    public List<Invoice> retrieveInvoicesByEmployeeByDate(int employeeID, LocalDate date) {
        Employee employee = this.employeeInterface.getEmployeeById(employeeID);
        List<Invoice> list = distinctInvoices(employee.getEmployeeProductInvoices(), date);
        return list;
    }

    @Override
    public void deleteOneInvoice(int invoiceid) {
        invoice = this.invoiceRepository.findByInvoiceID(invoiceid);
        if (invoice != null)
            this.invoiceRepository.delete(invoice);
    }

    @Override
    public void cancelInvoice(int invoiceid) throws Exception {
        Invoice invoice = this.invoiceRepository.findByInvoiceID(invoiceid);
        if (invoice == null) return;
        invoice.setCancelled(true);
        invoice.setCancelledAt(LocalDateTime.now());
        this.invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> retrieveAllCancelledInvoicesByEmployee(int employeeid) {
        Employee employee = this.employeeInterface.getEmployeeById(employeeid);
        List<Invoice> list = distinctCancelledInvoices(employee.getEmployeeProductInvoices());
        return list;
    }

    @Override
    public List<Invoice> retrieveCancelledInvoicesByEmployeeByDate(int employeeID, LocalDate date) {
        Employee employee = this.employeeInterface.getEmployeeById(employeeID);
        List<Invoice> list = distinctInvoices(employee.getEmployeeProductInvoices(), date);
        return list;
    }

    @Override
    public void restoreInvoice(int invoiceID) {
        Invoice invoice = this.invoiceRepository.findByInvoiceID(invoiceID);
        if (invoice == null) return;
        invoice.setCancelled(false);
        invoice.setCancelledAt(null);
        this.invoiceRepository.save(invoice);
    }

    private List<Invoice> distinctCancelledInvoices(Set<EmployeeProductInvoice> employeeProductInvoices) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (EmployeeProductInvoice EPI : employeeProductInvoices) {
            if (!EPI.getInvoice().isCancelled()) continue;
            if (invoiceList.contains(EPI.getInvoice())) continue;
            invoiceList.add(EPI.getInvoice());
        }
        return invoiceList;
    }

    private List<Invoice> distinctCancelledInvoices(Set<EmployeeProductInvoice> employeeProductInvoices, LocalDate date) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (EmployeeProductInvoice EPI : employeeProductInvoices) {
            if (!EPI.getInvoice().isCancelled()) continue;
            if (invoiceList.contains(EPI.getInvoice())) continue;
            if (EPI.getLocalDate().equals(date))
                invoiceList.add(EPI.getInvoice());
        }
        return invoiceList;
    }

    private List<Invoice> distinctInvoices(Set<EmployeeProductInvoice> employeeProductInvoices) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (EmployeeProductInvoice EPI : employeeProductInvoices) {
            if (EPI.getInvoice().isCancelled()) continue;
            if (invoiceList.contains(EPI.getInvoice())) continue;
            invoiceList.add(EPI.getInvoice());
        }
        return invoiceList;
    }

    private List<Invoice> distinctInvoices(Set<EmployeeProductInvoice> employeeProductInvoices, LocalDate date) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (EmployeeProductInvoice EPI : employeeProductInvoices) {
            if (EPI.getInvoice().isCancelled()) continue;
            if (invoiceList.contains(EPI.getInvoice())) continue;
            if (EPI.getLocalDate().equals(date))
                invoiceList.add(EPI.getInvoice());
        }
        return invoiceList;
    }
}
