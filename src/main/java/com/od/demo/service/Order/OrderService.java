package com.od.demo.service.Order;

import com.od.demo.model.Order.CustOrderDto;
import com.od.demo.model.Order.OrderResponse;
import com.od.demo.model.Order.UpdateOrderStatusRequest;
import com.od.demo.model.PageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(String customerId, List<CustOrderDto> custOrderDtos);

     ResponseEntity  updateStatusOrder(Integer trxRefId,UpdateOrderStatusRequest updateOrderStatusRequest);

    PageDto<CustOrderDto> searchOrder(String keyword, PageRequest pageRequest);



}
