package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent (Student student);
    Student findStudent(Long id);
    Student deleteStudent(Long id) ;
    Student editStudent(Student student);

    Collection<Student> getAllByAge(int age);

}