package com.sparta.project_d.repository;

import com.sparta.project_d.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductsRepository extends JpaRepository<Products, Long> {
}
