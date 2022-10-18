package com.od.demo.repository;

import com.od.demo.entity.CustOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends PagingAndSortingRepository<CustOrder,Integer> {

     List<CustOrder> findAll();

    Optional<CustOrder> findByTrxrefid(Integer trxrefid);

    Page<CustOrder> findByStatusIgnoreCaseContaining(String keyword, Pageable pageRequest);
}
