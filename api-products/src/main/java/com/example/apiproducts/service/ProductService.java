package com.example.apiproducts.service;

import com.example.apiproducts.dto.ProductRequest;
import com.example.apiproducts.dto.ProductResponse;
import com.example.apiproducts.entity.Product;
import com.example.apiproducts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public Flux<Product> findAll() {
        return repository.findAll();
    }


    public Mono<ProductResponse> save(ProductRequest productRequest) {
        String uid = UUID.randomUUID().toString();
        Product product = Product.builder()
                .id(uid)
                .name(productRequest.name())
                .price(productRequest.price())
                .quantity(productRequest.quantity())
                .build();

        return Mono.fromCallable(() -> {
            repository.save(product);
            log.info("Saving product: {}", product);

            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build();
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
