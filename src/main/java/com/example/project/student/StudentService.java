package com.example.project.student;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addStudents(Student student)
    {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudents(long studentId)
    {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException(("Student with id " + studentId + " does not exist"));
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudents(long studentId, String name, String email)
    {
         Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));
         if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name))
         {
             student.setName(name);
         }
         if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email))
         {
             Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
             if(studentOptional.isPresent())
             {
                 throw new IllegalStateException((("Student with email " + email + " already exists")));
             }
             student.setEmail(email);
         }
    }
}
