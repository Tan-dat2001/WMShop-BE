package com.shoes.repository;

import com.shoes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    Optional<List<Product>> findByCategory_Id(Long id);

    boolean existsByName(String name);

    //Home page - customer
    @Query(value = "select * from product where status = true order by created_at desc limit 15", nativeQuery = true)
    Optional<List<Product>> getNewsProductsList();

    //Home page - customer
    @Query(value = "select * " +
            "from product " +
            "where status = true and sold_quantity is not null " +
            "order by sold_quantity " +
            "desc limit 15", nativeQuery = true)
    Optional<List<Product>> getBestSellingProductsList();

    //Shop - customer with category(optional) - if(category is null && keyword is null) -> get all
    @Query(value = "select p.* " +
            "from product p " +
            "inner join category c on p.category_id = c.id " +
            "where (:categoryId is null or (c.id = :categoryId)) " +
            "and p.status = true " +
            "and (1<>1 or cast(p.name as text) ilike concat('%',:keyword,'%')) " +
            "order by p.sold_quantity desc", nativeQuery = true)
    Optional<List<Product>> getProductsListForCustomerByCategory(String keyword, Long categoryId);

    //Shop - customer -search
    @Query(value = "select p.* " +
            "from product p " +
            "inner join category c on p.category_id = c.id " +
            "where p.status = true " +
            "and (1<>1 or cast(p.name as text) ilike concat('%',:keyword,'%')) " +
            "order by p.sold_quantity desc ", nativeQuery = true)
    Optional<List<Product>> getProductsListForCustomerByKeyword(String keyword);

    //Shop
//    @Query(value = "SELECT p.* " +
//            "FROM product p " +
//            "INNER JOIN category c ON p.category_id = c.id " +
//            "WHERE ", nativeQuery = true)
//    Optional<List<Product>> getAllProducts();

 }
