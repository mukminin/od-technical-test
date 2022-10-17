package com.od.demo.repository;

import com.od.demo.entity.CustOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<CustOrder,Integer> {

     List<CustOrder> findAll();

     CustOrder findByTrxrefid(String trxrefid);
}
