package com.od.demo.service.Customer;

import com.od.demo.model.Customer.CustomerDto;
import com.od.demo.model.Customer.RoleDto;
import com.od.demo.service.Customer.model.Customer.CustomerResponse;
import com.od.demo.service.Customer.model.Customer.Role.RoleResponse;

import java.util.List;


public interface CustomerService {

    CustomerDto getCustomer(String idType, String idNumber);
    List<RoleDto> getCustomerRole(String customerId);
}
