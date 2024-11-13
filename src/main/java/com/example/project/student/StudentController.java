package com.example.project.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController
{

    private final StudentService studentService;

    public StudentController(StudentService studentService ) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> hello()
    {
        return studentService.getStudents();
    }

    @PostMapping
    public void registeruser(@RequestBody Student student)
    {
         studentService.addStudents(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteuser(@PathVariable long studentId)
    {
        studentService.deleteStudents(studentId);
    }

    @PutMapping("{studentId}")
    public void updateuser(@PathVariable long studentId,@RequestParam(required = false)String name,@RequestParam(required = false)String email)
    {
        studentService.updateStudents(studentId,name,email);
    }
}
