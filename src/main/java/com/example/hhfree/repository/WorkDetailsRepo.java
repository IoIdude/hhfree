package com.example.hhfree.repository;

import com.example.hhfree.entity.Position;
import com.example.hhfree.entity.User;
import com.example.hhfree.entity.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WorkDetailsRepo extends JpaRepository<WorkDetails, Integer> {
    WorkDetails findById(int id);
    WorkDetails findByInfo(String info);
    Collection<WorkDetails> findByPositionId(Position position);
}
