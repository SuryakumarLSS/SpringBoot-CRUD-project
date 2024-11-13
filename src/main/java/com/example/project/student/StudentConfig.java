package com.example.project.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig
{
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository)
    {
        return args -> {
            Student Surya = new Student("Surya","surya@gmail.com", LocalDate.of(2003, Month.DECEMBER,4));
            Student Hari  = new Student( "Hari","hari@gmail.com",LocalDate.of(2003,Month.DECEMBER,12));

            studentRepository.saveAll(List.of(Surya,Hari));
        };
    }

}
