package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            StudentModel newStudent1 = new StudentModel(
                    "Osaretin", "Omofonmwan", "Oso@gmail.com", LocalDate.of(1995, Month.MARCH, 19)
            );
            StudentModel newStudent2 = new StudentModel(
                    "Ade", "Uwensuyi", "Ade@yahoo.com", LocalDate.of(1994, Month.SEPTEMBER, 7)
            );
            StudentModel newStudent3 = new StudentModel(
                    "Boye", "Uwensuyi", "Boye@hotmail.com", LocalDate.of(1992, Month.APRIL, 21)
            );
            repository.saveAll(List.of(newStudent1, newStudent2, newStudent3));
        };
    }
}
