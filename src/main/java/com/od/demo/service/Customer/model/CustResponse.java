package com.od.demo.service.Customer.model;

import com.od.demo.service.Customer.model.Customer.CustomerResponse;
import com.od.demo.service.Customer.model.common.Meta;
import lombok.Data;

@Data public class CustResponse {
    Meta meta;
    CustomerResponse customer;
}
