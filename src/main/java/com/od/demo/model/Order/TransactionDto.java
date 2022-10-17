package com.od.demo.model.Order;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Data
public class TransactionDto {

    private Integer trxRefId;

    //TODO apply enum
    private String Status;
    private String statusDesc;
    private String remarks;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
