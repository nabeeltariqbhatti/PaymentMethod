package com.starzplay.paymentmethod.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Getter
@Setter
@Entity
@Table(name = "payment_methods")
@NoArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_method_id")
    @JsonIgnore
    private Long id;


}
