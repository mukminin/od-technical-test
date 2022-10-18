package com.od.demo.service.OrderService;

import com.od.demo.Exception.NoSuchOrderException;
import com.od.demo.common.ResponseHandler;
import com.od.demo.entity.CustOrder;
import com.od.demo.model.Customer.ContactDto;
import com.od.demo.model.Order.OrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;

import com.od.demo.model.Order.UpdateOrderStatusRequest;
import com.od.demo.repository.OrderRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    public final RestTemplate restTemplate;

    public static final String purchaseOrderURL = "https://avocado.od-tech.my/api/purchase";

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplateBuilder restTemplateBuilder) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        OrderResponse orderModel = new OrderResponse();
//        orderModel.setMeta(response);

        //set order
        System.out.print(orderRequest.toString());
        orderModel.setOrder(orderRequest.getOrder());


        //set transaction model
//        orderModel.setTransaction();

        //save order into db
        if (orderRequest.getOrder().size() > 0) {
            orderRequest.getOrder().forEach(orderDto -> {

                CustOrder custOrder = new CustOrder();
                custOrder.setCode(orderDto.getCode());
                custOrder.setCustomerId(orderRequest.getCustomer().getId());
                custOrder.setQuantity(orderDto.getQuantity());
                custOrder.setStatus("Submitted");
                orderRepository.save(custOrder);
            });

        }

        //save to backend host api
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(purchaseOrderURL)
//                .queryParam("customerId", customerId);

//       Meta response = restTemplate.postForObject(builder.toUriString(),orderdto, Meta.class);


//use update status service to change status in progress

        return orderModel;
    }

    @Override
    public ResponseEntity updateStatusOrder(Integer trxRefId, UpdateOrderStatusRequest updateOrderStatusRequest) {

        CustOrder custOrder = new CustOrder();
        Optional<CustOrder> custOrderOptional = orderRepository.findByTrxrefid(trxRefId);
        if (custOrderOptional.isPresent()) {
            custOrder.setStatus(updateOrderStatusRequest.getStatus());
            custOrder.setRemarks(updateOrderStatusRequest.getRemarks());
            orderRepository.save(custOrder);

            return ResponseHandler.generateResponseMeta(HttpStatus.OK);
        } else {
            return ResponseHandler.generateResponseMeta(HttpStatus.MULTI_STATUS);

        }


    }
}
