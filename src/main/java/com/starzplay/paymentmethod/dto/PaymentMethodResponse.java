package com.starzplay.paymentmethod.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/08
 */
@Setter
@Getter
public class PaymentMethodResponse {

    List<PaymentMethodDto> paymentMethods;
}
