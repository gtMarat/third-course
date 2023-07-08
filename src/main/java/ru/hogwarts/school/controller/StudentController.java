package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection<Student> getAllByAge(@RequestParam int age) {
        return studentService.getAllByAge(age);
    }

    @GetMapping("/find")
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getInfoStudent(@PathVariable Long id) {
        Student Student = studentService.findStudent(id);
        if (Student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student Student) {
        return studentService.addStudent(Student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student Student) {
        Student foundStudent = studentService.editStudent(Student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }
    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> facultyByStudent(@PathVariable Long id){
        Faculty result = studentService.facultyOfStudentBy(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

