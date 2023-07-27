package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("addStudent method was invoked");

        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        logger.info("findStudent method was invoked");

        return studentRepository.findById(id).orElseGet(() ->{
            logger.warn("Student with id{} has not been found in database ", id);
        return null;
                });
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("getAll method was invoked");

        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("deleteStudent method was invoked");

        studentRepository.deleteById(id);
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("editStudent method was invoked");

        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllByAge(int age) {
        logger.info("getAllByAge method was invoked");

        return getAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("findByAgeBetween method was invoked");

        return studentRepository.findAllByAgeBetweenOrderByAge(min, max);
    }

    @Override
    public Faculty facultyOfStudentBy(Long studentId) {
        logger.info("facultyOfStudentBy method was invoked");

        return studentRepository.findById(studentId)
                .map(Student::getFaculty).orElse(null);
    }

    @Override
    public int getCountOfStudents() {
        logger.info("getCountOfStudents method was invoked");

        return studentRepository.getCountOfStudents();
    }

    @Override
    public double getAverageAge() {
        logger.info("getAverageAge method was invoked");
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }
    @Override
    public List<Student> getFiveStudentsOrderById() {
        logger.info("getFiveStudentsOrderById method was invoked");

        return studentRepository.getFiveStudentsOrderById();
    }
    public List<String> listAstarteNamesUppercase() {
        logger.info("listAstarteNamesUppercase method was invoked");

        return studentRepository.findAll().stream()
                .parallel()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }
}
