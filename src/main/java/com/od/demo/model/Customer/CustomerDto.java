package com.od.demo.model.Customer;
import com.od.demo.service.Customer.model.Customer.Role.RolesResponse;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CustomerDto {

    @NotEmpty(message = "Customer Id cannot be empty")
    private   String id;

    IdentificationDto identification;

    DetailsDto details;

    List<ContactDto> contact;

    List<RoleDto> roles;
}
