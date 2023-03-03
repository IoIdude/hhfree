package com.example.hhfree.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "CompaniesTypes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Integer id;
    @Column()
    String tittle;
}
