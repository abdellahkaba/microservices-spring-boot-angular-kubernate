package com.isi.microservices.order.service;

import com.isi.microservices.order.dto.OrderRequest;
import com.isi.microservices.order.model.Order;
import com.isi.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    public void placeOrder(OrderRequest request){
        // Map OrderRequest to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(request.price().multiply(BigDecimal.valueOf(request.quantity())));
        order.setSkuCode(request.skuCode());
        order.setQuantity(request.quantity());
        // save in repository
        repository.save(order) ;
    }
}
