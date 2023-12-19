package com.shoes.repository;

import com.shoes.entity.Cart;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsByProductDetail_Id(Long id);

    Optional<List<Cart>> findByUser_Id(Long id, Sort sort);

    @Query(value = "SELECT c.* " +
            "FROM cart c " +
            "JOIN \"user\" u ON c.user_id = u.id " +
            "JOIN product_detail pd ON c.product_detail_id = pd.id " +
            "WHERE c.order_id IS NULL " +
            "AND u.id = :userId " +
            "AND pd.id = :productDetailId", nativeQuery = true)
    Optional<List<Cart>> getCartsListToAddToCart(Long userId, Long productDetailId);

//    Optional<List<Cart>> getCartsListToOrder(Long userId);

}
