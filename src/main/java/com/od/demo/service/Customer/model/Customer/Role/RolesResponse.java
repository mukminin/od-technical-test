package com.od.demo.service.Customer.model.Customer.Role;

import com.od.demo.service.Customer.model.common.Meta;
import lombok.Data;

import java.util.List;

@Data
public class RolesResponse {

    Meta meta;

    List<RoleResponse> roles;
}
