package com.example.ecommerce.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.ecommerce.domain.cart.CartItem;
import com.example.ecommerce.domain.checkout.Checkout;
import com.example.ecommerce.domain.checkout.CheckoutDTO;
import com.example.ecommerce.domain.checkout.CheckoutItemDTO;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.domain.product.ProductRequestDTO;
import com.example.ecommerce.domain.product.ProductResponseDTO;
import com.example.ecommerce.domain.user.User;
import com.example.ecommerce.repositories.CheckoutRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;




@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CheckoutRepository checkoutRepository;
    
    
    public ProductResponseDTO getProductById(String login, String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getUser().getLogin().equals(login)) {
                return mapProductToDTO(product);
            }
        }
        return null;
    }
    
   public List<ProductResponseDTO> getProductsByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            List<Product> products = user.getProducts();
            return mapProductsToDTO(products);
        }
        return Collections.emptyList();
    }

    private List<ProductResponseDTO> mapProductsToDTO(List<Product> products) {
        return products.stream()
                .map(product -> new ProductResponseDTO(product))
                .collect(Collectors.toList());
    }

    public ProductResponseDTO createProduct(String login, ProductRequestDTO productRequestDTO) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(login);
    if (user == null) {
        throw new UsernameNotFoundException(login);
    }

    Product product = productRequestDTO.toProduct();
    product.setUser(user);

    Product createdProduct = productRepository.save(product);
    return mapProductToDTO(createdProduct, user);
    
    }

    private ProductResponseDTO mapProductToDTO(Product product, User user) {
    return new ProductResponseDTO(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getQuantity(),
        product.getImg(),
        user.getEmail()
    );
}
    public ProductResponseDTO mapProductToDTO(Product product) {
        User user = product.getUser();
        String userEmail = (user != null) ? user.getEmail() : null;
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getQuantity(),
            product.getImg(),
            userEmail
        );
}

    public ProductResponseDTO updateProduct(String login, String productId, ProductRequestDTO productRequestDTO) {
        User user = userRepository.findByLogin(login);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (user != null && optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getUser().getLogin().equals(login)) {
                product.setName(productRequestDTO.getName());
                product.setPrice(productRequestDTO.getPrice());
                product.setQuantity(productRequestDTO.getQuantity());
                product.setImg(productRequestDTO.getImg());
                Product updatedProduct = productRepository.save(product);
                return mapProductToDTO(updatedProduct);
            }
        }
        return null;
    }

    public void deleteProduct(String login, String productId) {
        User user = userRepository.findByLogin(login);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (user != null && optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getUser().getLogin().equals(login)) {
                productRepository.delete(product);
            }
        }
    }
  
    public List<CheckoutDTO> getCheckoutsByUserProducts(String login) {
    User user = userRepository.findByLogin(login);
    List<Product> products = user.getProducts();

    List<CheckoutDTO> checkoutDTOs = new ArrayList<>();
    for (Product product : products) {
        List<Checkout> checkouts = checkoutRepository.findByItemsProduct(product);
        for (Checkout checkout : checkouts) {
            CheckoutDTO checkoutDTO = new CheckoutDTO();
            checkoutDTO.setId(checkout.getId());
            checkoutDTO.setFullName(checkout.getFullName());
            checkoutDTO.setPhoneNumber(checkout.getPhoneNumber());
            checkoutDTO.setStreet(checkout.getStreet());
            checkoutDTO.setCity(checkout.getCity());
            checkoutDTO.setState(checkout.getState());
            checkoutDTO.setPostalCode(checkout.getPostalCode());
            
            LocalDateTime checkoutDateTime = checkout.getCheckoutDateTime();
            checkoutDTO.setCheckoutDateTime(checkoutDateTime);


            List<CheckoutItemDTO> checkoutItemDTOs = new ArrayList<>();
            for (CartItem cartItem : checkout.getItems()) {
                CheckoutItemDTO checkoutItemDTO = new CheckoutItemDTO();
                // Preenche os atributos do checkoutItemDTO com as informações relevantes do cartItem
                checkoutItemDTO.setProductId(cartItem.getProduct().getId());
                checkoutItemDTO.setProductName(cartItem.getProduct().getName());
                checkoutItemDTO.setPrice(cartItem.getProduct().getPrice());
                checkoutItemDTO.setQuantity(cartItem.getQuantity());
                // ...
    
                checkoutItemDTOs.add(checkoutItemDTO);
            }
    
            checkoutDTO.setItems(checkoutItemDTOs);
            checkoutDTOs.add(checkoutDTO);
        }
    }

    return checkoutDTOs;
}
}





   

