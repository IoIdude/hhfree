package com.example.hhfree.repository;

import com.example.hhfree.entity.Sphere;
import com.example.hhfree.entity.User;
import com.example.hhfree.entity.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SphereRepo extends JpaRepository<Sphere, Integer> {
    Sphere findBySphereName(String info);
}
