package com.starzplay.paymentmethod.dto;

import com.starzplay.paymentmethod.constants.Currency;
import com.starzplay.paymentmethod.constants.Duration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */

@Getter
@Setter
@NoArgsConstructor
public class PaymentPlanDto {

    private Long paymentPlanId;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal grossAmount;
    private Currency currency;
    private Duration duration;
    private Set<PaymentMethodDto> paymentMethods;

}
