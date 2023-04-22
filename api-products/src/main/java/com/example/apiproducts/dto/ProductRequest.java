package com.example.apiproducts.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price, int quantity) {
}
