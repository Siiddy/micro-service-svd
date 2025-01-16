package com.example.student.model;

import com.example.student.entity.Student;

public class StudentResponse {

    private String id;
    private String name;
    private String genre;
    private String schoolId;
    private SchoolDTO school;   // pour inclure les infos de l'école

    public StudentResponse() {
    }

    // Constructeur pratique pour mapper un Student + SchoolDTO
    public StudentResponse(Student student, SchoolDTO school) {
        this.id = student.getId();
        this.name = student.getName();
        this.genre = student.getGenre();
        this.schoolId = student.getSchoolId();
        this.school = school;
    }

    // Getters / Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void setSchool(SchoolDTO school) {
        this.school = school;
    }
}
