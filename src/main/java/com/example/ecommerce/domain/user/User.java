package com.example.ecommerce.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ecommerce.domain.cart.Cart;
import com.example.ecommerce.domain.checkout.Checkout;
import com.example.ecommerce.domain.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String login;
    private String password;
    private UserRole role;

    public User(String login, String email, String password, UserRole role){
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cart = new Cart(); 
        this.cart.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;


    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setCart(Cart cart) {
    this.cart = cart;
    cart.setUser(this);
    }
    public Checkout getCheckout() {
        return null;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Checkout> checkouts = new ArrayList<>();

    public void addCheckout(Checkout checkout) {
        checkouts.add(checkout);
        checkout.setUser(this);
    }

    public void removeCheckout(Checkout checkout) {
        checkouts.remove(checkout);
        checkout.setUser(null);
    }

   
}
