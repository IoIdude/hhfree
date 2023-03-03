package com.example.hhfree.repository;

import com.example.hhfree.entity.Country;
import com.example.hhfree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {
    Country findByCountryName(String name);
}
