package com.od.demo.repository;

import com.od.demo.entity.CustOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<CustOrder,Integer> {

     List<CustOrder> findAll();

    Optional<CustOrder> findByTrxrefid(Integer trxrefid);

}
