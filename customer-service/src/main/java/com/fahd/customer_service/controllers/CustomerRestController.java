package com.fahd.customer_service.controllers;

import com.fahd.customer_service.entities.Customer;
import com.fahd.customer_service.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public  Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }
}
