package com.od.demo.service.Customer.model.Customer.Details;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AddressResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer id;
    private Boolean primaryAddress;
    private String line1;
    private String line2;
    private String line3;
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
