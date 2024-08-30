package com.isi.microservices.product.service;

import com.isi.microservices.product.dto.ProductRequest;
import com.isi.microservices.product.dto.ProductResponse;
import com.isi.microservices.product.model.Product;
import com.isi.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .skuCode(request.skuCode())
                .price(request.price())
                .build();
        repository.save(product);
        log.info("Product created successfully");
        return new ProductResponse(product.getId(), product.getName(),
                product.getDescription(),product.getSkuCode(),product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                        product.getSkuCode(),
                        product.getPrice()))
                .toList();
    }
}
