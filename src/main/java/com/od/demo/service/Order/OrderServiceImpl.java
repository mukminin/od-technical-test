package com.od.demo.service.Order;

import com.od.demo.common.ResponseHandler;
import com.od.demo.common.enums.Status;
import com.od.demo.common.enums.StatusDescription;
import com.od.demo.entity.CustOrder;
import com.od.demo.entity.Transaction;
import com.od.demo.mapper.CustOrderMapper;
import com.od.demo.mapper.PageMapper;
import com.od.demo.mapper.TransactionMapper;
import com.od.demo.model.Order.CustOrderDto;
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
    private final TransactionMapper transactionMapper;

    public static final String purchaseOrderURL = "https://avocado.od-tech.my/api/purchase";

    public OrderServiceImpl(OrderRepository orderRepository, TransactionRepository transactionRepository, RestTemplateBuilder restTemplateBuilder, CustOrderMapper custOrderMapper, TransactionMapper transactionMapper) {
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplateBuilder.build();
        this.custOrderMapper = custOrderMapper;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public OrderResponse createOrder(String customerId, List<CustOrderDto> custOrderDto) {


        //save data into db

        //save transaction
        //set random id for transaction
        Transaction transaction = new Transaction();
        Random rand = new Random();
        Integer tempId = rand.nextInt(1000000000);
        transaction.setStatus(Status.SUBMITTED);
        transaction.setStatusDescription(StatusDescription.Submitted);
        transaction.setTrxrefid(tempId);
        transactionRepository.save(transaction);


        //save order
        List<CustOrderDto> CustOrderlist = new ArrayList<>();
        if (custOrderDto != null && custOrderDto.size() > 0) {
            custOrderDto.forEach(orderDto -> {

                CustOrder custOrder = new CustOrder();
                custOrder.setCode(orderDto.getCode());
                custOrder.setCustomerId(customerId);
                custOrder.setQuantity(orderDto.getQuantity());
                custOrder.setStatus(Status.SUBMITTED);
                custOrder.setTrxrefid(tempId);


                orderRepository.save(custOrder);

                CustOrderDto tempCustOrderlist = new CustOrderDto();
                tempCustOrderlist.setCode(orderDto.getCode());
                tempCustOrderlist.setQuantity(orderDto.getQuantity());
                CustOrderlist.add(tempCustOrderlist);

            });



        //set order response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTransaction(transactionMapper.toDto(transaction));
        orderResponse.setOrder(CustOrderlist);

        return orderResponse;


    }else

    {
        throw new NullPointerException();
    }



    //save to backend host api
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(purchaseOrderURL)
//                .queryParam("customerId", customerId);

//       Meta response = restTemplate.postForObject(builder.toUriString(),orderdto, Meta.class);


//use update status service to change status in progress


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
