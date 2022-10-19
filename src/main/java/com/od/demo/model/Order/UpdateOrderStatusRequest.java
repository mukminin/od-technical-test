package com.od.demo.model.Order;

import com.od.demo.common.enums.Status;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {


    private Status status;
    private String remarks;
}
