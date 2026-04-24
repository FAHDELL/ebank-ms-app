package com.fahd.customer_service.service;

import com.fahd.customer_service.entities.Customer;
import com.fahd.customer_service.repository.CustomerRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @McpTool(description = "Get All Customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @McpTool(description = "Find Customer by id")
    public Customer getCustomerById(@McpToolParam(description = "The Customers Id") Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @McpTool(description = "Save a new customer")
    public  Customer saveCustomer(@McpToolParam(description = "Customer to save") Customer customer){
        return customerRepository.save(customer);
    }
}
