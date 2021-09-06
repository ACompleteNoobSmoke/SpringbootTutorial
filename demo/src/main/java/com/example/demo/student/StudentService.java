package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> getStudents(){
        return studentRepository.findAll();

    }

    public void addNewStudent(StudentModel student) {
        Optional<StudentModel> validateEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if(validateEmail.isPresent()){ throw new IllegalStateException("Email Exists");}
        System.out.println(student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID){
       boolean exists = studentRepository.existsById(studentID);
       if(!exists) { throw new IllegalStateException("Student Does Not Exist");}
       studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, String lastName, String email){
        StudentModel exists = studentRepository.getById(studentID);
        if(exists == null) { throw new IllegalStateException("Student Does Not Exist"); }
        if(lastName != null && !lastName.isBlank()){ exists.setLastName(lastName); }
        if(email != null && !email.isBlank() && !Objects.equals(exists.getEmail(), email)){ exists.setEmail(email); }
    }
}
