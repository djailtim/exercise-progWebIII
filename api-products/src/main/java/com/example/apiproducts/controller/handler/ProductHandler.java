package com.example.apiproducts.controller.handler;

import com.example.apiproducts.dto.ProductRequest;
import com.example.apiproducts.dto.ProductResponse;
import com.example.apiproducts.entity.Product;
import com.example.apiproducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductService productService;
    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Product> products = productService.findAll();
        return ServerResponse.ok().body(products, ProductResponse.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(ProductRequest.class)
                .subscribeOn(Schedulers.parallel())
                .flatMap(productService::save)
                .flatMap(response ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(response))
                );
    }
}
