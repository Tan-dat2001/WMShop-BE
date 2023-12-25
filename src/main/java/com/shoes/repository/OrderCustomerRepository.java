package com.shoes.repository;

import com.shoes.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCustomerRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT o.* " +
            "FROM orders o " +
            "JOIN \"user\" u ON :customerId = u.id " +
            "WHERE o.user_id = u.id " +
            "ORDER BY order_date DESC", nativeQuery = true)
    Optional<List<Order>> getOrdersListForCustomer(Long customerId);


//    @Query(value = "" , nativeQuery = true)
//    Optional<List<Order>> getOrderCustomerList(Long)

}
