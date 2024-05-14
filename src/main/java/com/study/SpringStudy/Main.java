package com.study.SpringStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.study.springstudy")
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}
