package com.example.ecommerce.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByLogin(String login);
}
