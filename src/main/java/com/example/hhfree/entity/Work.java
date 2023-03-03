package com.example.hhfree.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "Works")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Integer id;
    @Column()
    @Size(min = 3, max = 30, message = "Название работы должно быть больше 3 и меньше 30 символов")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z ]+$", message = "Название работы может содержать только буквы")
    String workName;
    @ManyToOne(fetch = EAGER)
    private Company company_id;
    @ManyToOne(fetch = EAGER)
    private WorkDetails workDetailsId;
}
