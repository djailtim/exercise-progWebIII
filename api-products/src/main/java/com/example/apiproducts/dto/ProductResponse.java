package com.example.apiproducts.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
public final class ProductResponse {
    private final String id;
    private final String name;
    private final BigDecimal price;
    private final int quantity;

}
