package com.od.demo.mapper.Customer;

import com.od.demo.model.Customer.CustomerDto;
import com.od.demo.service.Customer.model.Customer.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto toDto(CustomerResponse customerResponse);
}
