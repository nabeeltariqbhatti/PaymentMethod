package com.starzplay.paymentmethod.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starzplay.paymentmethod.constants.Currency;
import com.starzplay.paymentmethod.constants.Duration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="payment_plans")
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_plan_id" )
    @JsonProperty("id")
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
    @JsonIgnore
    private List<PaymentMethod> paymentMethods;

}
