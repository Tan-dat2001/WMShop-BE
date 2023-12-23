package com.shoes.repository;

import com.shoes.entity.Product;
import com.shoes.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    Optional<List<ProductDetail>> findByProduct_Id(Long productId);

    @Query(value = "SELECT pd.* " +
            "FROM product_detail pd " +
            "WHERE pd.color ilike :color " +
            "And pd.size ilike :size " +
            "AND pd.product_id = :productId", nativeQuery = true)
    ProductDetail getProductDetailByColorAndSizeAndProduct_Id(String color, String size, Long productId);

}
