package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee") // Maps the class to the 'employees' table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate IDs
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "name", nullable = false) // Maps to the 'name' column, cannot be null
    private String name;

    @Column(name = "role") // Maps to the 'role' column
    private String role;

    // No-arg constructor
    public Employee() {}

    // All-args constructor
    public Employee(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
