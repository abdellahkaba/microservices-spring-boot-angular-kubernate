package com.isi.microservices.order.controller;


import com.isi.microservices.order.dto.OrderRequest;
import com.isi.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        service.placeOrder(orderRequest);
        return "Commande passée avec succès";
    }
}
