package com.od.demo.controller;

import com.od.demo.common.ResponseHandler;
import com.od.demo.model.Order.OrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;
import com.od.demo.model.Order.UpdateOrderStatusRequest;
import com.od.demo.service.Customer.model.common.Meta;
import com.od.demo.service.OrderService.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public OrderResponse CreateOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @PutMapping("/{trxRefId}/status")
    public ResponseEntity updateOrderStatus(@PathVariable Integer trxRefId, @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {

           ResponseEntity response =  orderService.updateStatusOrder(trxRefId, updateOrderStatusRequest);
            if(response.getStatusCode().equals(HttpStatus.OK)){
                return ResponseHandler.generateResponseMeta(HttpStatus.OK);
            }
            else {
                return ResponseHandler.generateResponseMeta(response.getStatusCode());
            }
    }


}
