package com.example.ecommerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.domain.checkout.CheckoutDTO;
import com.example.ecommerce.services.CheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    
    @Autowired
    private CheckoutService checkoutService;
    

    @GetMapping("/success")
    public ResponseEntity<List<CheckoutDTO>> getCheckoutsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        String login = userDetails.getUsername();
        List<CheckoutDTO> checkoutDTOs = checkoutService.getCheckoutsByUser(login);
        if (!checkoutDTOs.isEmpty()) {
            return ResponseEntity.ok(checkoutDTOs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCheckout(
            @RequestBody CheckoutDTO checkoutDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String login = userDetails.getUsername();
        checkoutService.createCheckout(checkoutDTO, login);
        
        return ResponseEntity.ok("Compra finalizada com sucesso!");
    }
}