package com.sparta.project_d.repository;

import com.sparta.project_d.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, String> {
    List<Products> findAllByOrderByGradeAsc();
}
