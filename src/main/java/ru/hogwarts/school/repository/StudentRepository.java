package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByAgeBetweenOrderByAge(int min, int max);

    List<Student> findAllByFaculty_Id(long id);

    @Query(value = "SELECT count(*) FROM student ", nativeQuery = true)
    int getCountOfStudents();

    @Query(value = "select avg(age) from student ", nativeQuery = true)
    int getAverageAge();

    @Query(value = "SELECT * FROM student order by id desc limit 5", nativeQuery = true)
    List<Student> getFiveStudentsOrderById();
}











