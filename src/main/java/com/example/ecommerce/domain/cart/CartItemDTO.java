package com.example.ecommerce.domain.cart;


public record CartItemDTO(
        String id,
        String productId,
        String name,
        double price,
        Integer quantity
    ) {
    public CartItemDTO(CartItem cartItem) {
        this(cartItem.getId(), cartItem.getProduct().getId(), cartItem.getProduct().getName(), cartItem.getProduct().getPrice(), cartItem.getQuantity());
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
