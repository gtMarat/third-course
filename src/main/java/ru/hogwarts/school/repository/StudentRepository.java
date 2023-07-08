package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByAgeBetweenOrderByAge(int min, int max);

    List<Student> findAllByFaculty_Id(long id);
}
