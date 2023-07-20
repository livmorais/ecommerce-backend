package com.example.ecommerce.domain.product;

public record ProductResponseDTO(String id, String name, Double price, Integer quantity, String img, String userEmail) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getImg(), product.getUserEmail());
    }

}
