package com.od.demo.controller;

import com.od.demo.common.ResponseHandler;
import com.od.demo.model.CustOrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;
import com.od.demo.model.Order.UpdateOrderStatusRequest;
import com.od.demo.model.PageDto;
import com.od.demo.service.Order.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

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

    @GetMapping("/search")
    public PageDto<CustOrderDto> searchOrder(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                             @RequestParam(value = "keyword", required = false) String keyword,
                                             @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                             @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection
    ) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return orderService.searchOrder(keyword, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortBy)));

    }

}
