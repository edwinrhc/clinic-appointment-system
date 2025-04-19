package com.edwin.clinic.repository;

import com.edwin.clinic.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {


}
