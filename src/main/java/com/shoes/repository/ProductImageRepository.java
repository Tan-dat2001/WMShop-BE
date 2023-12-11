package com.shoes.repository;

import com.shoes.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Optional<List<ProductImage>> findByProductId_Id(Long id);

    @Query(value = "SELECT pi.* " +
            "FROM product_image pi " +
            "LEFT JOIN product p ON pi.product_id = p.id " +
            "WHERE pi.product_id = :productId ", nativeQuery = true)
    Optional<List<ProductImage>> getProductImagesByProductId(Long productId);
}
