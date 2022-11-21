package de.neuefische.spring_request_params.controller;


import de.neuefische.spring_request_params.model.Student;
import de.neuefische.spring_request_params.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> listStudents(@RequestParam Optional<String> search) {
        if (search.isPresent()) {
            return studentService.search(search.get());
        }
        return studentService.list();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.findById(id);
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.delete(id);
    }
}
