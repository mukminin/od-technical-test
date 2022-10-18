package com.od.demo.model.Order;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {

    //todo change to enum
    private String status;
    private String remarks;
}
