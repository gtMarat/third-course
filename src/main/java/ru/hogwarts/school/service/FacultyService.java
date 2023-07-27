package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty (Faculty faculty);

    Faculty findFaculty (Long id);

    Collection<Faculty> getAll();

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    public Collection<Faculty> getFaculties(String color);

    Collection<Faculty> filter(String color, String name);

    Collection<Student> studentsByFaculty(Long facultyId);

    public String longestFacultyName();

    public Integer intSum();
}
