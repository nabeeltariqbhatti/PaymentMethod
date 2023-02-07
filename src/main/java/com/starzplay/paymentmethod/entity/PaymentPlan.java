package com.starzplay.paymentmethod.entity;

import com.starzplay.paymentmethod.constants.Currency;
import com.starzplay.paymentmethod.constants.Duration;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="payment_plans")
@EqualsAndHashCode
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_plan_id" )
    private Long paymentPlanId;
    @Column(name = "net_amount")
    private BigDecimal netAmount;
    @Column(name = "tax_amount")
    private BigDecimal taxAmount;
    @Column(name = "gross_amount")
    private BigDecimal grossAmount;
    @Column(name = "currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @Column(name="duration")
    @Enumerated(EnumType.STRING)
    private Duration duration;
    @ManyToMany(mappedBy = "paymentPlans", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PaymentMethod>  paymentMethods;

}
