package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ecommerce.domain.cart.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUserId(String userId);
}
    