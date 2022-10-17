package com.od.demo.model.Customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class DetailsDto {
    private String displayName;


    private String dateOfBirth;
    private String email;
}
