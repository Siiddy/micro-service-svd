package com.example.school.service;

import com.example.school.entity.School;
import com.example.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public School updateSchool(Long id, School schoolDetails) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if(optionalSchool.isPresent()){
            School existingSchool = optionalSchool.get();
            existingSchool.setName(schoolDetails.getName());
            existingSchool.setAddress(schoolDetails.getAddress());
            existingSchool.setDirectorName(schoolDetails.getDirectorName());
            return schoolRepository.save(existingSchool);
        } else {
            // Gérer le cas où l'id n'existe pas
            return null;
        }
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
