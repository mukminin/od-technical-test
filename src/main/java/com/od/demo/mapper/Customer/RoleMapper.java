package com.od.demo.mapper.Customer;

import com.od.demo.model.Customer.RoleDto;
import com.od.demo.service.Customer.model.Customer.Role.RoleResponse;
import com.od.demo.service.Customer.model.Customer.Role.RolesResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleDto toDto(RoleResponse roleResponse);
}
