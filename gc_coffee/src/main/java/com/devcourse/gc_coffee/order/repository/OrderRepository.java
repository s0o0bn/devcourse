package com.devcourse.gc_coffee.order.repository;

import com.devcourse.gc_coffee.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// 이런 쿼리는 그냥 querydsl로 하는 게 나을듯..?
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems ORDER BY o.createdAt DESC")
    List<Order> findAllWithItemsOrderByCreatedAtDesc();

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.createdAt BETWEEN :from AND :to ORDER BY o.createdAt DESC")
    List<Order> findAllWithItemsByCreatedAtBetween(LocalDateTime from, LocalDateTime to);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.email = :email ORDER BY o.createdAt DESC")
    List<Order> findAllWithItemsByEmail(String email);
}
