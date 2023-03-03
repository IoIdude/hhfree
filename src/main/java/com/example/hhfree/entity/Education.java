package com.example.hhfree.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.LazyCollectionOption.FALSE;

@Entity(name = "Educations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Integer id;
    @Column()
    String educationName;
}
