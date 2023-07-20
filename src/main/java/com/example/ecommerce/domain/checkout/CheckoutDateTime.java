package com.example.ecommerce.domain.checkout;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class CheckoutDateTime {
    @CreationTimestamp
    @Column(name = "checkout_date_time")
    private LocalDateTime dateTime;

    protected CheckoutDateTime() {
    }
}
