package com.example.hhfree;

import com.example.hhfree.entity.*;
import com.example.hhfree.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class HhFreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhFreeApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService user) {
        return args -> {
            user.addRole(new Role(null, "USER"));
            user.addRole(new Role(null, "ADMIN"));

            user.addUser(new User(null, "Admin", "Admin", "Админ", "Админ", "Админ", 18)).setRoles(new ArrayList<>());
            user.addRoleToUser("Admin", "ADMIN");

            user.addCompanyType(new CompanyType(null, "ОАО"));
            user.addCompanyType(new CompanyType(null, "ООО"));
            user.addCompanyType(new CompanyType(null, "АО"));

            user.addSphere(new Sphere(null, "Программирование"));
            user.addSphere(new Sphere(null, "Дизайн"));
            user.addSphere(new Sphere(null, "Маркетинг"));

            user.addEmployment(new Employment(null, "Полная"));
            user.addEmployment(new Employment(null, "Частичная"));
            user.addEmployment(new Employment(null, "Подработка"));

            user.addPosition(new Position(null, "Начальник"));
            user.addPosition(new Position(null, "Помощник"));
            user.addPosition(new Position(null, "Стажер"));

            user.addEducation(new Education(null, "Высшее"));
            user.addEducation(new Education(null, "Среднее-специальное"));
            user.addEducation(new Education(null, "Среднее"));

            user.addCountry(new Country(null, "Москва"));
            user.addCountry(new Country(null, "Питер"));

            user.addCitizenship(new Citizenship(null, "Русский"));
            user.addCitizenship(new Citizenship(null, "Булорус"));
            user.addCitizenship(new Citizenship(null, "Укринец"));

            user.addStatus(new Status(null, "Не ищу работу"));
            user.addStatus(new Status(null, "В поисках работы"));
        };
    }
}
