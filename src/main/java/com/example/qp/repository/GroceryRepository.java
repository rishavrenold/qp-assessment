package com.example.qp.repository;


import com.example.qp.Entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface GroceryRepository extends JpaRepository<GroceryItem, Long> {}