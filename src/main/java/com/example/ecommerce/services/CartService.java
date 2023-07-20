package com.example.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.domain.cart.Cart;
import com.example.ecommerce.domain.cart.CartDTO;
import com.example.ecommerce.domain.cart.CartItem;
import com.example.ecommerce.domain.cart.CartItemDTO;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.domain.user.User;
import com.example.ecommerce.repositories.CartItemRepository;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;

@Service
public class CartService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

public void addProductToCart(String login, String productId, Integer quantity) {
    User user = userRepository.findByLogin(login);
    Optional<Product> optionalProduct = productRepository.findById(productId);
    
    if (user != null && optionalProduct.isPresent()) {
        Product product = optionalProduct.get();
        
        if (quantity <= product.getQuantity()) {
            Cart cart = user.getCart();
            
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);
            }

            // Verifica se o produto já está presente no carrinho
            Optional<CartItem> optionalCartItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst();

            if (optionalCartItem.isPresent()) {
                // Atualiza a quantidade do item existente
                CartItem existingItem = optionalCartItem.get();
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
            } else {
                // Cria um novo item e adicione ao carrinho
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart); // Define o carrinho para o CartItem
            cart.addItem(cartItem); // Adiciona o CartItem ao carrinho
            }
            userRepository.save(user);
            cartRepository.save(cart);

        } else {
            throw new IllegalArgumentException("A quantidade solicitada excede o limite disponível.");
        }
    }
}

public void updateCartItemQuantity(String login, String cartItemId, int quantity) {
    User user = userRepository.findByLogin(login);
    Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

    if (user != null && optionalCartItem.isPresent()) {
        CartItem cartItem = optionalCartItem.get();
        Product product = cartItem.getProduct();

        if (quantity <= product.getQuantity()) {
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice() * quantity); 
            cartItemRepository.save(cartItem);
        } else {
            throw new IllegalArgumentException("A quantidade solicitada excede o limite disponível.");
        }
    }
}


public void removeProductFromCart(String login, String cartItemId) {
    User user = userRepository.findByLogin(login);
    Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
    
    if (user != null && optionalCartItem.isPresent()) {
        CartItem cartItem = optionalCartItem.get();
        Cart cart = user.getCart();
        
        if (cartItem.getCart().equals(cart)) {
            cart.removeItem(cartItem);
            cartRepository.save(cart);
            cartItemRepository.delete(cartItem);
        } else {
            throw new IllegalArgumentException("O item não pertence ao carrinho do usuário.");
        }
    }
}


public CartDTO getCartItems(String login) {
    User user = userRepository.findByLogin(login);
    List<CartItemDTO> cartItems = new ArrayList<>();
    double totalPrice = 0.0;

    if (user != null && user.getCart() != null) {
        for (CartItem cartItem : user.getCart().getItems()) {
            double itemPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            totalPrice += itemPrice;
            cartItems.add(new CartItemDTO(
                    cartItem.getId(),
                    cartItem.getProduct().getId(),
                    cartItem.getProduct().getName(),
                    itemPrice,  // Usar o itemPrice calculado ao invés do preço do produto
                    cartItem.getQuantity()
            ));
        }
    }

    return new CartDTO(cartItems, totalPrice);
}

}

   