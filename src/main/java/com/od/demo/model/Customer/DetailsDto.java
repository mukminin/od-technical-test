package com.od.demo.model.Customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.od.demo.service.Customer.model.Customer.Details.AddressResponse;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
public class DetailsDto {
    private String salutation;
    private String displayName;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String nationality;
    private String race;

    List<AddressResponse> address;
}
