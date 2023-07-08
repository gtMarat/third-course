package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getFaculties(String color) {
        return getAll()
                .stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Faculty> filter(String color, String name) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(color, name);
    }

    @Override
    public Collection<Student> studentsByFaculty(Long id){
        return studentRepository.findAllByFaculty_Id(id);
    }

}
