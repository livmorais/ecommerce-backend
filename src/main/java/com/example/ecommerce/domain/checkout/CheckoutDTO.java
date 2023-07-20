package com.example.ecommerce.domain.checkout;

import java.time.LocalDateTime;
import java.util.List;
import com.example.ecommerce.domain.cart.CartItemDTO;

public class CheckoutDTO {
    private String id;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private String fullName;
    
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private double phoneNumber;
    
    public double getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String street;
    
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    private String city;
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    private String state;
    
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    private String postalCode;
    
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    private String holderName;
    
    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    private double cardNumber;
    
    public double getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(double cardNumber) {
        this.cardNumber = cardNumber;
    }

    private double expirationDate;

    public double getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(double expirationDate) {
        this.expirationDate = expirationDate;
    }

    private double securityCode;
    
    public double getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(double securityCode) {
        this.securityCode = securityCode;
    }

    private String cardType;

    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    private List<CheckoutItemDTO> items;

    
    public List<CheckoutItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CheckoutItemDTO> items) {
        this.items = items;
    }
    public void setCartItems(List<CartItemDTO> cartItemDTOs) {
    }

    public CheckoutDTO() {
        // Construtor vazio
    }

    private LocalDateTime checkoutDateTime;

    public LocalDateTime getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public void setCheckoutDateTime(LocalDateTime checkoutDateTime) {
        this.checkoutDateTime = checkoutDateTime;
    }

}
