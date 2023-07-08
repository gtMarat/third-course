package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllByAge(int age) {
        return getAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetweenOrderByAge(min, max);
    }

    @Override
    public Faculty facultyOfStudentBy(Long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getFaculty).orElse(null);
    }
}
