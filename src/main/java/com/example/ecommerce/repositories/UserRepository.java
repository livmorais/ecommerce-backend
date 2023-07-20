package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}
