package com.od.demo.model.Order;

import com.od.demo.service.Customer.model.common.Meta;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

        Meta meta;
        TransactionDto transaction;
        List<OrderDto> order;

}
