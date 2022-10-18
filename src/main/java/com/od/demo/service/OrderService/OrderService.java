package com.od.demo.service.OrderService;

import com.od.demo.model.Order.OrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;
import com.od.demo.model.Order.UpdateOrderStatusRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

     ResponseEntity  updateStatusOrder(Integer trxRefId,UpdateOrderStatusRequest updateOrderStatusRequest);



}
