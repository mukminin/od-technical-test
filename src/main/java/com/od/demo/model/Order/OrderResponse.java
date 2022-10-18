package com.od.demo.model.Order;

import com.od.demo.entity.CustOrder;
import com.od.demo.entity.Transaction;
import com.od.demo.model.CustOrderDto;
import com.od.demo.service.Customer.model.common.Meta;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

        Meta meta;
        Transaction transaction;
        List<CustOrderDto> order;

}
