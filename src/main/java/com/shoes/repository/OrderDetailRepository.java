package com.shoes.repository;

import com.shoes.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "SELECT od.* " +
            "FROM order_detail od " +
            "JOIN orders o ON od.order_id = o.id " +
            "WHERE od.order_id = :orderId", nativeQuery = true)
    Optional<List<OrderDetail>> getOrderDetailsListByOrderId(Long orderId);
}
