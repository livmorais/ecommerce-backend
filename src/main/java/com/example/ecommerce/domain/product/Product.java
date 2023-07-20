package com.example.ecommerce.domain.product;

import java.util.ArrayList;
import java.util.List;
import com.example.ecommerce.domain.cart.CartItem;
import com.example.ecommerce.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "product")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private double price;

    private Integer quantity;

    private String img;

    private String login;
    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
        this.quantity = data.quantity();
        this.img = data.img();
        this.user = user;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    public String getUserEmail() {
    if (user != null) {
        return user.getEmail();
    }
    return null;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setImg(String img){
        this.img = img;
    }

}
