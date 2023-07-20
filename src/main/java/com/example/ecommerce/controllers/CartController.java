package com.example.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;
import com.example.ecommerce.domain.cart.CartDTO;
import com.example.ecommerce.services.CartService;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{productId}")
    public ResponseEntity<Void> addProductToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String productId,
            @RequestParam Integer quantity
    ) {
        String login = userDetails.getUsername();
        cartService.addProductToCart(login, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCartItems(@AuthenticationPrincipal UserDetails userDetails) {
    String login = userDetails.getUsername();
    CartDTO cartDTO = cartService.getCartItems(login);

    if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
        throw new AccessDeniedException("Usuário admin não pode adicionar produtos ao carrinho.");
    }

    return ResponseEntity.ok(cartDTO);
    }


    @PutMapping("/{cartItemId}")
    public ResponseEntity<Void> updateCartItemQuantity(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable String cartItemId,
        @RequestParam Integer quantity
    ) {
    String login = userDetails.getUsername();
    cartService.updateCartItemQuantity(login, cartItemId, quantity);
    return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeProductFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String cartItemId
    ) {
        String login = userDetails.getUsername();
        cartService.removeProductFromCart(login, cartItemId);
        return ResponseEntity.noContent().build();
    }

}
