package com.shoes.repository;

import com.shoes.entity.Product;
import com.shoes.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    Optional<List<ProductDetail>> findByProduct_Id(Long productId);


}
