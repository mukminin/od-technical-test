package com.od.demo.model.Customer;

import com.od.demo.common.enums.ContactType;
import lombok.Data;

@Data
public class ContactDto {

    Integer id;


    ContactType type;
    String value;
}
