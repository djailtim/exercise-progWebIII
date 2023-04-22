package com.example.apiproducts.controller.router;

import com.example.apiproducts.controller.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class ProductRouter {
    private final ProductHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(GET("/products"), handler::findAll)
                .andRoute(POST("/products"), handler::save);
    }

}
