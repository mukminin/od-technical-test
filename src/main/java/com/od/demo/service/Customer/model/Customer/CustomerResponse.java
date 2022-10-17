package com.od.demo.service.Customer.model.Customer;

import com.od.demo.service.Customer.model.Customer.Details.DetailsResponse;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {


    private   String id;

    IdentificationResponse identification;

    DetailsResponse details;

    List<ContactResponse> contact;

}
