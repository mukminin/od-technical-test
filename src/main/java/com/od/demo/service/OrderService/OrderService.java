package com.od.demo.service.OrderService;

import com.od.demo.model.Order.OrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

}
