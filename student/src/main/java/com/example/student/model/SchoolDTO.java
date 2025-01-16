package com.example.student.model;

public class SchoolDTO {
    private Long id;
    private String name;
    private String address;
    private String directorName;

    public SchoolDTO() {
    }

    // Getters / Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
