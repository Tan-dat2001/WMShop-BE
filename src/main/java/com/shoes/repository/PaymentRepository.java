package com.shoes.repository;

import com.shoes.entity.Category;
import com.shoes.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

//    @Query(value = "SELECT * " +
//            "FROM payment", nativeQuery = true)
//    Optional<List<Payment>> getAllPayment();

    Optional<Payment> findById(Long id);

    @Query(value = "SELECT * " +
            "FROM payment " +
            "WHERE status = true", nativeQuery = true)
    Optional<List<Payment>> getPaymentsListByStatus();


}
