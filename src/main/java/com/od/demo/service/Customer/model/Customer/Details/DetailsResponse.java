package com.od.demo.service.Customer.model.Customer.Details;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class DetailsResponse {

    //can use enum Mr ms etc
  private String salutation;
  private String name;
  private String gender;
  private String dateOfBirth;
  private String email;
  private String nationality;
  private String race;

  List<AddressResponse> address;
}
