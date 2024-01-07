package com.shoes.repository;

import com.shoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "SELECT u.* " +
            "FROM \"user\" u " +
            "INNER JOIN user_role ur ON u.id = ur.user_id " +
            "INNER JOIN role r ON r.id = ur.role_id " +
            "WHERE r.id =  1 " +
            "ORDER BY u.created_at DESC ", nativeQuery = true)
    List<User> getUsersList();
}
