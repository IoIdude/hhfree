package com.example.hhfree.repository;

import com.example.hhfree.entity.Position;
import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepo extends JpaRepository<Position, Integer> {
    Position findByPositionName(String name);
}
