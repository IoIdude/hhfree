package com.example.hhfree.repository;

import com.example.hhfree.entity.Citizenship;
import com.example.hhfree.entity.User;
import com.example.hhfree.entity.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenshipRepo extends JpaRepository<Citizenship, Integer> {
    Citizenship findByNameCitizenship(String info);
}
