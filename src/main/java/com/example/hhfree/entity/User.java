package com.example.hhfree.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "Users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 30, message = "Логин должен быть больше 3 и меньше 30 символов")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Логин может содержать только английские буквы")
    private String login;
    @Column(nullable = false)
    @NotEmpty
    private String password;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 30, message = "Фамилия должна быть больше 3 и меньше 30 символов")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Фамилия должна содержать только кириллицу")
    private String surname;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 30, message = "Отчество должно быть больше 3 и меньше 30 символов")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Отчество должно содержать только кириллицу")
    private String middle_name;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 30, message = "Имя должно быть больше 3 и меньше 30 символов")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Имя должно содержать только кириллицу")
    private String first_name;
    @Column(nullable = false)
    @NotNull
    @Min(value = 18, message = "Возраст должен быть больше 17")
    @Max(value = 80, message = "Возраст должен быть не больше 80")
    private Integer age;
    @ManyToOne(fetch = EAGER)
    private Sphere sphere_id;
    @ManyToOne(fetch = EAGER)
    private Position positionId;
    @ManyToOne(fetch = EAGER)
    private Status status_id;
    @ManyToOne(fetch = EAGER)
    private Country country_id;
    @ManyToOne(fetch = EAGER)
    private Citizenship citizenship_id;
    @ManyToOne(fetch = EAGER)
    private Education education_id;
    @ManyToOne(fetch = EAGER)
    private Employment employment_id;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public User(Integer id, String login, String password, String surname, String name, String middle_name, Integer age) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.first_name = name;
        this.middle_name = middle_name;
        this.age = age;
    }
}
