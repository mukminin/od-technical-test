package com.od.demo.service.OrderService;

import com.od.demo.common.ResponseHandler;
import com.od.demo.entity.CustOrder;
import com.od.demo.entity.Transaction;
import com.od.demo.mapper.CustOrderMapper;
import com.od.demo.mapper.PageMapper;
import com.od.demo.model.CustOrderDto;
import com.od.demo.model.Order.OrderRequest;
import com.od.demo.model.Order.OrderResponse;

import com.od.demo.model.Order.UpdateOrderStatusRequest;
import com.od.demo.model.PageDto;
import com.od.demo.repository.OrderRepository;
import com.od.demo.repository.TransactionRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    public final RestTemplate restTemplate;

    private final CustOrderMapper custOrderMapper;

    public static final String purchaseOrderURL = "https://avocado.od-tech.my/api/purchase";

    public OrderServiceImpl(OrderRepository orderRepository, TransactionRepository transactionRepository, RestTemplateBuilder restTemplateBuilder, CustOrderMapper custOrderMapper) {
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.custOrderMapper = custOrderMapper;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {


        //save data into db

        //save transaction
        //set random id for transaction
        Transaction transaction = new Transaction();
        Random rand = new Random();
        Integer tempId = rand.nextInt();
        transaction.setStatus("SUBMITTED");
        transaction.setStatusDescription("Submitted");
        transaction.setTrxrefid(tempId);
        transactionRepository.save(transaction);


        //save order
        List<CustOrderDto> CustOrderlist = new ArrayList<>();
        if (orderRequest.getOrder().size() > 0) {
            orderRequest.getOrder().forEach(orderDto -> {

                CustOrder custOrder = new CustOrder();
                custOrder.setCode(orderDto.getCode());
                custOrder.setCustomerId(orderRequest.getCustomer().getId());
                custOrder.setQuantity(orderDto.getQuantity());
//                custOrder.setStatus("Submitted");
                custOrder.setTrxrefid(tempId);


                orderRepository.save(custOrder);

                CustOrderDto tempCustOrderlist = new CustOrderDto();
                tempCustOrderlist.setCode(orderDto.getCode());
                tempCustOrderlist.setQuantity(orderDto.getQuantity());
                CustOrderlist.add(tempCustOrderlist);

            });

        }

        //set order response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTransaction(transaction);
        orderResponse.setOrder(CustOrderlist);


//        OrderResponse orderModel = new OrderResponse();
//        orderModel.setMeta(response);

        //set order
//        System.out.print(orderRequest.toString());
//        orderModel.setOrder(orderRequest.getOrder());


        //set transaction model
//        TransactionDto transactionDto = new TransactionDto();
//        Random rand = new Random();
//
//        transactionDto.setTrxRefId(rand.nextInt());
//        transactionDto.setStatus("Submitted");
//        transactionDto.setRemarks("Submitted");
//        transactionDto.setCreatedAt();
//
//
//
//
//        //save order into db
//        if (orderRequest.getOrder().size() > 0) {
//            orderRequest.getOrder().forEach(orderDto -> {
//
//                CustOrder custOrder = new CustOrder();
//                custOrder.setCode(orderDto.getCode());
//                custOrder.setCustomerId(orderRequest.getCustomer().getId());
//                custOrder.setQuantity(orderDto.getQuantity());
//                custOrder.setStatus("Submitted");
//                orderRepository.save(custOrder);
//            });
//
//        }

        //save to backend host api
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(purchaseOrderURL)
//                .queryParam("customerId", customerId);

//       Meta response = restTemplate.postForObject(builder.toUriString(),orderdto, Meta.class);


//use update status service to change status in progress

        return orderResponse;
    }

    @Override
    public ResponseEntity updateStatusOrder(Integer trxRefId, UpdateOrderStatusRequest updateOrderStatusRequest) {

        CustOrder custOrder = new CustOrder();
        Optional<CustOrder> custOrderOptional = orderRepository.findByTrxrefid(trxRefId);
        if (custOrderOptional.isPresent()) {
            custOrder.setStatus(updateOrderStatusRequest.getStatus());
//            custOrder.setRemarks(updateOrderStatusRequest.getRemarks());
            orderRepository.save(custOrder);

            return ResponseHandler.generateResponseMeta(HttpStatus.OK);
        } else {
            return ResponseHandler.generateResponseMeta(HttpStatus.MULTI_STATUS);

        }


    }

    @Override
    public PageDto<CustOrderDto> searchOrder(String keyword, PageRequest pageRequest) {
        Page<CustOrder> custOrderPage;

        custOrderPage = orderRepository.findByStatusIgnoreCaseContaining(keyword, pageRequest);
        return PageMapper.map(custOrderPage.getContent().stream().map(custOrderMapper::toDto).collect(Collectors.toList()), custOrderPage);
    }
}
