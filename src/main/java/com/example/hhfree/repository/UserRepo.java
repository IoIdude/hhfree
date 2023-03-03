package com.example.hhfree.repository;

import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    User findById(int id);
    Collection<User> findBySurname(String surname);
    User deleteById(int id);
}
