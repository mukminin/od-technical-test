package com.od.demo.model.Order;

import com.od.demo.model.Customer.CustomerDto;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Data
public class OrderRequest {

    CustomerDto customer;
    List<OrderDto> order;
}
