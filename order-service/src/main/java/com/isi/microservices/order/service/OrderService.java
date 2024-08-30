package com.isi.microservices.order.service;

import com.isi.microservices.order.client.InventoryClient;
import com.isi.microservices.order.dto.OrderRequest;
import com.isi.microservices.order.model.Order;
import com.isi.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository repository;
    private final InventoryClient client ;
    public void placeOrder(OrderRequest request){
        var isProductInStock = client.isInStock(request.skuCode(), request.quantity()) ;
        if (isProductInStock){
            // Map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(request.price().multiply(BigDecimal.valueOf(request.quantity())));
            order.setSkuCode(request.skuCode());
            order.setQuantity(request.quantity());
            // save in repository
            repository.save(order) ;
        }else {
            throw new RuntimeException("Product avec SkuCode " + request.skuCode()+ " non disponible");
        }
    }
}
