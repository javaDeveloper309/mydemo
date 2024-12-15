package com.example.demo.entity;


import jakarta.persistence.*;


@Entity
public class Department {

    @Id
    private Long id;
    private String name;

    // No-arg constructor
    public Department() {}

    // All-args constructor
    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
