package com.example.ecommerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.domain.checkout.CheckoutDTO;
import com.example.ecommerce.domain.product.ProductRequestDTO;
import com.example.ecommerce.domain.product.ProductResponseDTO;
import com.example.ecommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    private ProductService productService;
    

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(
           @AuthenticationPrincipal UserDetails userDetails,
           @PathVariable String productId
    ) {
       String login = userDetails.getUsername();
       ProductResponseDTO product = productService.getProductById(login, productId);
       if (product != null) {
           ProductResponseDTO responseDTO = new ProductResponseDTO(product.id(), product.name(), product.price(), product.quantity(), product.img(), product.userEmail());
           return ResponseEntity.ok(responseDTO);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByLogin(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        List<ProductResponseDTO> products = productService.getProductsByLogin(login);
        return ResponseEntity.ok(products);
    }


    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> createProductForCurrentUser(
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestBody @Valid ProductRequestDTO productRequestDTO
    ) {
    String login = userDetails.getUsername();
    ProductResponseDTO createdProduct = productService.createProduct(login, productRequestDTO);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable String productId,
        @RequestBody @Valid ProductRequestDTO productRequestDTO
    ) {
    String login = userDetails.getUsername();
    ProductResponseDTO updatedProduct = productService.updateProduct(login, productId, productRequestDTO);
    if (updatedProduct != null) {
        return ResponseEntity.ok(updatedProduct);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String productId
    ) {
        String login = userDetails.getUsername();
        productService.deleteProduct(login, productId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/sales")
    public ResponseEntity<List<CheckoutDTO>> getCheckoutsByUserProducts(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        List<CheckoutDTO> checkoutDTOs = productService.getCheckoutsByUserProducts(login);
        return ResponseEntity.ok(checkoutDTOs);
    }
}

