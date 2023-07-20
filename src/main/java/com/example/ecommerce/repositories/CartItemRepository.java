package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.domain.cart.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

}
