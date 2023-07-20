package com.example.ecommerce.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.domain.cart.Cart;
import com.example.ecommerce.domain.cart.CartItem;
import com.example.ecommerce.domain.checkout.Checkout;
import com.example.ecommerce.domain.checkout.CheckoutDTO;
import com.example.ecommerce.domain.checkout.CheckoutItemDTO;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.domain.user.User;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.CheckoutRepository;
import com.example.ecommerce.repositories.UserRepository;

@Service
public class CheckoutService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CartRepository cartRepository;
    
    
    public List<CheckoutDTO> getCheckoutsByUser(String login) {
        User user = userRepository.findByLogin(login);
        List<Checkout> checkouts = user.getCheckouts();
    
        List<CheckoutDTO> checkoutDTOs = new ArrayList<>();
        for (Checkout checkout : checkouts) {
            CheckoutDTO checkoutDTO = new CheckoutDTO();
            // Preenche os atributos do checkoutDTO com as informações relevantes do checkout
            checkoutDTO.setId(checkout.getId());
            checkoutDTO.setFullName(checkout.getFullName());
            checkoutDTO.setPhoneNumber(checkout.getPhoneNumber());
            checkoutDTO.setStreet(checkout.getStreet());
            checkoutDTO.setCity(checkout.getCity());
            checkoutDTO.setState(checkout.getState());
            checkoutDTO.setPostalCode(checkout.getPostalCode());
            checkoutDTO.setHolderName(checkout.getHolderName()); 
            checkoutDTO.setCardNumber(checkout.getCardNumber()); 
            checkoutDTO.setExpirationDate(checkout.getExpirationDate()); 
            checkoutDTO.setSecurityCode(checkout.getSecurityCode()); 
            checkoutDTO.setCardType(checkout.getCardType()); 
            // ...
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
    
        return checkoutDTOs;
    }


    public void createCheckout(CheckoutDTO checkoutDTO, String login) {
        User user = userRepository.findByLogin(login);
        Cart cart = user.getCart();
    
        Checkout checkout = new Checkout();
        checkout.setUser(user);
        checkout.setFullName(checkoutDTO.getFullName());
        checkout.setPhoneNumber(checkoutDTO.getPhoneNumber());
        checkout.setStreet(checkoutDTO.getStreet());
        checkout.setCity(checkoutDTO.getCity());
        checkout.setState(checkoutDTO.getState());
        checkout.setPostalCode(checkoutDTO.getPostalCode());
        checkout.setHolderName(checkoutDTO.getHolderName());
        checkout.setCardNumber(checkoutDTO.getCardNumber());
        checkout.setExpirationDate(checkoutDTO.getExpirationDate());
        checkout.setSecurityCode(checkoutDTO.getSecurityCode());
        checkout.setCardType(checkoutDTO.getCardType());
        

        for (CartItem cartItem : cart.getItems()) {
            CartItem checkoutItem = new CartItem();
            checkoutItem.setProduct(cartItem.getProduct());
            checkoutItem.setQuantity(cartItem.getQuantity());
            // Copia os demais atributos relevantes do carrinho para o checkoutItem
    
            checkoutItem.setCheckout(checkout); // Associa o checkoutItem ao checkout
            checkout.getItems().add(checkoutItem); // Adiciona o checkoutItem à lista de itens do checkout
    
            cartItem.setCheckout(checkout); // Associa o checkoutItem ao checkout

            Product product = cartItem.getProduct();
            int soldQuantity = cartItem.getQuantity();
            int currentQuantity = product.getQuantity();
            int updatedQuantity = currentQuantity - soldQuantity;
            product.setQuantity(updatedQuantity);
        }
    
        checkoutRepository.save(checkout);
        cart.clearItems();
        cartRepository.save(cart);
    
    }
}
