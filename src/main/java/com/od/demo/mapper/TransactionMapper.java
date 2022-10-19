package com.od.demo.mapper;

import com.od.demo.entity.Transaction;
import com.od.demo.model.Order.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransactionMapper {
    @Mapping(source = "statusDescription", target = "statusDesc")
    @Mapping(source = "trxrefid", target = "trxRefId")
    @Mapping(source = "createdAt", target = "created")
    @Mapping(source = "updatedAt", target = "lastUpdated")
    TransactionDto toDto(Transaction transaction);

}
