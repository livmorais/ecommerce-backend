package com.example.ecommerce.domain.cart;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import com.example.ecommerce.domain.user.User;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }

    public void clearItems() {
        items.clear();
    }

    @Column(name = "total_price")
    private Double totalPrice;

    public double getTotalPrice() {
    double totalPrice = 0;
    for (CartItem cartItem : items) {
        double itemPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
        totalPrice += itemPrice;
    }
    return totalPrice;
    }
}
