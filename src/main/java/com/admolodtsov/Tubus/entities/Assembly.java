package com.admolodtsov.Tubus.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "assemblies")
public class Assembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
}
