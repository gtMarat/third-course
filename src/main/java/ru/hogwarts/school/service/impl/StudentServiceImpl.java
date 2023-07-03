package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long cont = 0;
    @Override
    public Student addStudent(Student student) {
        student.setId(cont++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Student deleteStudent (Long id) {
        return students.remove(id);
    }

    @Override
    public Student editStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            return null;
        }
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Collection<Student> getAllByAge(int age) {
        if (age == 0) {
            return students.values();
        }
        return students.values()
                .stream()
                .filter(it -> it.getAge()==age)
                .collect(Collectors.toSet());
    }
}
