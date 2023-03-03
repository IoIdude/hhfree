package com.example.hhfree.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "Companies")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Integer id;
    @Column()
    @Size(min = 3, max = 30, message = "Название компании должно быть больше 3 и меньше 30 символов")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z0-9!,. ]+$", message = "Название компании может содержать буквы, цифры и знаки припенания")
    String name;
    @ManyToOne(fetch = EAGER)
    private CompanyType company_type_id;
}
