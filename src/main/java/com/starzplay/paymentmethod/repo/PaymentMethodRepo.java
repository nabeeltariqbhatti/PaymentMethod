package com.starzplay.paymentmethod.repo;

import com.starzplay.paymentmethod.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Repository
public interface PaymentMethodRepo  extends JpaRepository<PaymentMethod, Long> {

    public List<PaymentMethod> findPaymentMethodByName(@Param("name") String name);


}
