package com.example.ecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.domain.product.ProductResponseDTO;
import com.example.ecommerce.repositories.ProductRepository;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    @PreAuthorize("hasRole('USER') and !hasRole('ADMIN')")  
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
    List<Product> products = productRepository.findAll();
    List<ProductResponseDTO> responseDTOs = mapProductsToDTOs(products);

    return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
}

private List<ProductResponseDTO> mapProductsToDTOs(List<Product> products) {
    return products.stream()
            .map(ProductResponseDTO::new)
            .collect(Collectors.toList());
}

}
