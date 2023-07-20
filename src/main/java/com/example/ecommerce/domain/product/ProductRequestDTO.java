package com.example.ecommerce.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductRequestDTO(
        @NotBlank
        String name,

        @NotNull
        Double price,

        @NotNull
        Integer quantity,

        @NotBlank
        String img
    ){
        public Product toProduct() {
        return new Product(this);
    }

        public String getName() {
    return name;
    }

    public Double getPrice() {
    return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getImg() {
        return img;
    }
}
