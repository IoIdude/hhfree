package com.example.hhfree.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "WorksDetails")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Integer id;
    @Column()
    String info;
    @ManyToOne(fetch = EAGER)
    private Sphere sphere_id;
    @ManyToOne(fetch = EAGER)
    private Position positionId;
    @ManyToOne(fetch = EAGER)
    private Country country_id;
    @ManyToOne(fetch = EAGER)
    private Education education_id;
    @ManyToOne(fetch = EAGER)
    private Employment employment_id;
}
