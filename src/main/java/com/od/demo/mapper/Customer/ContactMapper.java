package com.od.demo.mapper.Customer;

import com.od.demo.model.Customer.ContactDto;
import com.od.demo.service.Customer.model.Customer.ContactResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
    ContactDto toDto(ContactResponse contactResponse);
}
