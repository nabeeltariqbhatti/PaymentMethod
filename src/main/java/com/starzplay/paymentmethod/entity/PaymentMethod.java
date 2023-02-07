package com.starzplay.paymentmethod.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starzplay.paymentmethod.constants.PaymentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Getter
@Setter
@Entity
@Table(name = "payment_methods")
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_method_id")
    @JsonIgnore
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @JoinTable(
            name = "payment_method_plans",
           joinColumns = @JoinColumn(
                    name = "payment_method_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="payment_plan_id",
                    referencedColumnName = "id"
            )
    )
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PaymentPlan> paymentPlans;




}
