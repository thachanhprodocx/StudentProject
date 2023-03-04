package vti.studentproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class StudentProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentProjectApplication.class, args);
    }

}
