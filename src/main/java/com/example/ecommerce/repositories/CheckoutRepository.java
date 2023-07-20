package com.example.ecommerce.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ecommerce.domain.checkout.Checkout;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.domain.user.User;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, String> {
    List<Checkout> findByUser(User user);
    List<Checkout> findByItemsProduct(Product product);
}
