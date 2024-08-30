package com.isi.microservices.inventory.service;

import com.isi.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository repository;
    public boolean isInStock(String skuCode, Integer quantity){
        log.info(" Début -- Demande reçue pour vérifier le stock pour le skuCode {}, avec la quantité {}", skuCode, quantity);
        boolean isInStock = repository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        log.info(" Fin -- Le produit avec le skuCode {} et la quantité {} est en stock - {}", skuCode, quantity, isInStock);
        return isInStock;
    }
}
