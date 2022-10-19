package com.od.demo.model.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.od.demo.common.enums.Status;
import com.od.demo.common.enums.StatusDescription;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TransactionDto {

    private Integer trxRefId;
    private Status Status;
    private StatusDescription statusDesc;
    private String remarks;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;


}
