package com.example.apiproducts.repositories;

import com.example.apiproducts.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ProductRepository {
    private static final List<Product> PRODUCTS_DB = new CopyOnWriteArrayList<>();

    public void save(Product product) {
        PRODUCTS_DB.add(product);
    }

    public Flux<Product> findAll() {
        return Flux.fromIterable(PRODUCTS_DB);
    }
}
