package com.sparta.project_d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectDApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectDApplication.class, args);
    }

}
