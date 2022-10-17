package com.od.demo.controller;

import com.od.demo.model.Customer.CustomerDto;
import com.od.demo.service.Customer.model.Customer.CustomerResponse;

import com.od.demo.service.Customer.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final CustomerService customerService;

    public UserController( CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping()
    public CustomerDto getUser(@RequestParam(value="idType") String idType,
                               @RequestParam(value = "idNumber") String idNumber) {
        return customerService.getCustomer(idType,idNumber);
    }

}
