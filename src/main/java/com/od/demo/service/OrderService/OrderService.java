package com.od.demo.service.OrderService;

import com.od.demo.model.CustOrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;
import com.od.demo.model.Order.UpdateOrderStatusRequest;
import com.od.demo.model.PageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

     ResponseEntity  updateStatusOrder(Integer trxRefId,UpdateOrderStatusRequest updateOrderStatusRequest);

    PageDto<CustOrderDto> searchOrder(String keyword, PageRequest pageRequest);



}
