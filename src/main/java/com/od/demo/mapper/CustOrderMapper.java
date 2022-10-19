package com.od.demo.mapper;

import com.od.demo.entity.CustOrder;
import com.od.demo.model.Order.CustOrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustOrderMapper {

    CustOrderDto toDto(CustOrder custOrder);


    CustOrder fromDto(CustOrderDto custOrderDto);
}
