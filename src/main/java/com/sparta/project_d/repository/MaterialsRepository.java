package com.sparta.project_d.repository;

import com.sparta.project_d.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialsRepository extends JpaRepository<Materials, String> {
    List<Materials> findAllByOrderByCategoryAscGradeAsc();
}
