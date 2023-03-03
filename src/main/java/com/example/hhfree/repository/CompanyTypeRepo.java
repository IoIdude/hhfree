package com.example.hhfree.repository;

import com.example.hhfree.entity.CompanyType;
import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepo extends JpaRepository<CompanyType, Integer> {
    CompanyType findByTittle(String type);
}
