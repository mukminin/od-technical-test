package com.od.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustOrderDto {

    private String code;

    private Integer quantity;

}
