package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.model.SchoolDTO;
import com.example.student.model.StudentResponse;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    // On peut injecter l'URL de l'autre microservice via le fichier .properties
    @Value("${school.service.url:http://localhost:8080/api/schools}")
    private String schoolServiceUrl;

    @Autowired
    public StudentService(StudentRepository studentRepository, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
    }

    // ---------------------
    // OPERATIONS CRUD
    // ---------------------

    // CREATE
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // READ - Tous les students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // READ - Un student par ID (sans school)
    public Optional<Student> getStudentOnlyById(String id) {
        return studentRepository.findById(id);
    }

    // READ - Un student + infos de l'école
    public Optional<StudentResponse> getStudentWithSchoolById(String id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            // Appel au microservice School pour récupérer l'école
            // URL à appeler : schoolServiceUrl + "/" + student.getSchoolId()
            String url = schoolServiceUrl + "/" + student.getSchoolId();
            SchoolDTO schoolDTO = null;

            try {
                schoolDTO = restTemplate.getForObject(url, SchoolDTO.class);
            } catch (Exception e) {
                // Gérer l'erreur si le microservice School n'est pas disponible
                // On peut logguer l'erreur, etc.
                e.printStackTrace();
            }

            // Créer l'objet de réponse
            StudentResponse response = new StudentResponse(student, schoolDTO);
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    // UPDATE
    public Optional<Student> updateStudent(String id, Student updatedStudent) {
        return studentRepository.findById(id).map(s -> {
            s.setName(updatedStudent.getName());
            s.setGenre(updatedStudent.getGenre());
            s.setSchoolId(updatedStudent.getSchoolId());
            return studentRepository.save(s);
        });
    }

    // DELETE
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
