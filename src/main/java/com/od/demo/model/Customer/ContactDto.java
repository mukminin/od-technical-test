package com.od.demo.model.Customer;

import lombok.Data;

@Data
public class ContactDto {

    Integer id;

    //TODO change to enum PHONE,VALUE
    String type;
    String value;
}
