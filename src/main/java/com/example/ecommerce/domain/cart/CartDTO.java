package com.example.ecommerce.domain.cart;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> cartItems;
    private double totalPrice;

    public CartDTO(List<CartItemDTO> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
