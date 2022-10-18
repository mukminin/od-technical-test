package com.od.demo.mapper.Customer;

import com.od.demo.model.Customer.DetailsDto;
import com.od.demo.service.Customer.model.Customer.Details.DetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DetailsMapper {

    @Mapping(source="name",target="displayName")
   DetailsDto toDto(DetailsResponse detailsResponse);
}
