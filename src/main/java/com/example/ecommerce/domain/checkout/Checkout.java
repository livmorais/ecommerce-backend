package com.example.ecommerce.domain.checkout;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.ecommerce.domain.cart.CartItem;
import com.example.ecommerce.domain.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "checkout")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Checkout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fullName;
    private double phoneNumber;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String holderName;
    private double cardNumber;
    private double expirationDate;
    private double securityCode;
    private String cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "checkout_date_time")
    private CheckoutDateTime checkoutDateTime;

    public LocalDateTime getCheckoutDateTime() {
        if (checkoutDateTime != null) {
            return checkoutDateTime.getDateTime();
        }
        return null;
    }

    public void setCheckoutDateTime(CheckoutDateTime checkoutDateTime) {
        this.checkoutDateTime = checkoutDateTime;
    }

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
        item.setCheckout(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCheckout(null);
    }

}
