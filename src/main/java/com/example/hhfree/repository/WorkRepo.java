package com.example.hhfree.repository;

import com.example.hhfree.entity.User;
import com.example.hhfree.entity.Work;
import com.example.hhfree.entity.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WorkRepo extends JpaRepository<Work, Integer> {
    Work findById(int id);
    Work deleteById(int id);
    Collection<Work> findByWorkName(String name);
    Work findByWorkDetailsId(WorkDetails workDetails);
    Collection<Work> findByWorkNameContaining(String name);
}
