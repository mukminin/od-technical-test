package com.od.demo.model.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustOrderDto {

    @NotEmpty(message = "code cannot be empty")
    @NotNull(message = "code cannot be empty")
    private String code;

    @NotEmpty(message = "quantity cannot be empty")
    @NotNull(message = "code cannot be empty")
    private Integer quantity;

}
