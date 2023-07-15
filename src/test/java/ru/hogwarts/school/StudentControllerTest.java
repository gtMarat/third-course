package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception{
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void searchStudentEndpointTest(){
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
        final int age = 11;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?age" + age, String.class))
                .isNotNull();
        final int minAge = 11;
        final int maxAge = 13;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?min=" + minAge + "&max=" + maxAge, String.class))
                .isNotNull();
    }
    @Test
    void readStudentByIdEndpointTest() throws Exception {
        final long id = 3;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class))
                .isNotNull();
    }
    @Test
    void deleteStudentEndpointTest(){
        final long studentId = 10;
        Assertions
                .assertThat(this.restTemplate.exchange("http://localhost:" + port + "/student/" + studentId, HttpMethod.DELETE,
                null, String.class))
                .isNotNull();
    }
    @Test
    void updateStudentEndpointTest(){
        Student student = new Student();
        student.setAge(18);
        student.setName("Sedrik");
        student.setId(4);

        RequestEntity<Student> requestEntity = new RequestEntity<>(student, HttpMethod.PUT,
                URI.create("http://localhost:" + port + "/student"));

        Assertions
                .assertThat(this.restTemplate.exchange(requestEntity, String.class))
                .isNotNull();
    }
}
