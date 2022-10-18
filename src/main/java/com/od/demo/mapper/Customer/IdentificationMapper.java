package com.od.demo.mapper.Customer;

import com.od.demo.model.Customer.IdentificationDto;
import com.od.demo.service.Customer.model.Customer.IdentificationResponse;
import org.mapstruct.Mapper;

@Mapper
public interface IdentificationMapper {

    IdentificationDto toDto(IdentificationResponse identificationResponse);
}
