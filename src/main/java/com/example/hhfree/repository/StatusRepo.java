package com.example.hhfree.repository;

import com.example.hhfree.entity.Status;
import com.example.hhfree.entity.User;
import com.example.hhfree.entity.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {
    Status findByNameStatus(String info);
}
