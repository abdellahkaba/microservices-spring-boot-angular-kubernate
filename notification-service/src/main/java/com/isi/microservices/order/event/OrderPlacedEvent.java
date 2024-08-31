package com.isi.microservices.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
    private String email;
    private String firstName;
    private String lastName;
}
