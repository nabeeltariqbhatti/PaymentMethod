package com.starzplay.paymentmethod.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.entity.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/08
 */


@Component
public class Util {

    public static List<PaymentMethodDto> mapPaymentMethodsToPaymentMethodsDto(List<PaymentMethod> paymentMethods, ObjectMapper objectMapper) {
        return paymentMethods.
                stream().map(paymentMethod -> objectMapper.convertValue(paymentMethod, PaymentMethodDto.class))
                .collect(Collectors.toList());
    }
}
