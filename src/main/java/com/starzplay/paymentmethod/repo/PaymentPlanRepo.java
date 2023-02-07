package com.starzplay.paymentmethod.repo;

import com.starzplay.paymentmethod.entity.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Repository

public interface PaymentPlanRepo extends JpaRepository<PaymentPlan,Long> {
}
