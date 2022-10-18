package com.od.demo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Setter
@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerId;
    private String code;
    private Integer trxrefid;
    private Integer quantity;
    //todo change to enum
    private String  status;
    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
