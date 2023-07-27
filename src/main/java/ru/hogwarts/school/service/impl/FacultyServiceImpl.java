package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);


    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("addFaculty method was invoked");

        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.info("findFaculty method was invoked");

        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("getAll method was invoked");

        return facultyRepository.findAll();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("editFaculty method was invoked");

        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.info("deleteFaculty method was invoked");

        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getFaculties(String color) {
        logger.info("getFaculties method was invoked");

        return getAll()
                .stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Faculty> filter(String color, String name) {
        logger.info("filter method was invoked");

        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(color, name);
    }

    @Override
    public Collection<Student> studentsByFaculty(Long id) {
        logger.info("studentsByFaculty method was invoked");

        return studentRepository.findAllByFaculty_Id(id);
    }

    public String longestFacultyName() {
        logger.info("longestFacultyName method was invoked");
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }

    public Integer intSum() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }
}
