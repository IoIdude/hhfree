package com.example.hhfree.repository;

import com.example.hhfree.entity.Company;
import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
    Company findByName(String name);
    Company findById(int id);
}
