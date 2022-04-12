package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query(value = "select customerName from customers", nativeQuery = true)
    List<String> findCustomers();
}
