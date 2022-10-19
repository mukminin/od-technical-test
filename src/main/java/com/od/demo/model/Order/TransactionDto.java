package com.od.demo.model.Order;

import com.od.demo.common.enums.Status;
import com.od.demo.common.enums.StatusDescription;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class TransactionDto {

    private Integer trxRefId;


    private Status Status;
    private StatusDescription statusDesc;
    private String remarks;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
