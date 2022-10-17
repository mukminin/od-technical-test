package com.od.demo.service.Customer.model.Customer;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ContactResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer id;

    //can use enum phone, email
    private String type;
    private String value;

}
