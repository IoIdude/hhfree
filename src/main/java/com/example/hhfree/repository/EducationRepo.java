package com.example.hhfree.repository;

import com.example.hhfree.entity.Education;
import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepo extends JpaRepository<Education, Integer> {
    Education findByEducationName(String name);
}
