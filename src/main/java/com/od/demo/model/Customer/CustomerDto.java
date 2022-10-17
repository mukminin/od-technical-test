package com.od.demo.model.Customer;
import com.od.demo.service.Customer.model.Customer.Role.RolesResponse;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private   String id;

    IdentificationDto identification;

    DetailsDto details;

    List<ContactDto> contact;

    List<RoleDto> roles;
}
