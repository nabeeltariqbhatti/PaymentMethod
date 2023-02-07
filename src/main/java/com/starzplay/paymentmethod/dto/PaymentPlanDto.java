package com.starzplay.paymentmethod.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.starzplay.paymentmethod.constants.Currency;
import com.starzplay.paymentmethod.constants.Duration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "netAmount", "taxAmount","grossAmount","currency","duration"})
public class PaymentPlanDto {

    @JsonProperty("id")
    private Long paymentPlanId;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal grossAmount;
    private Currency currency;
    private Duration duration;
    @JsonIgnore
    private List<PaymentMethodDto> paymentMethods;

}
