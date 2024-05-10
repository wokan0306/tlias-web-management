package com.itstan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TliasWebDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebDevelopmentApplication.class, args);
    }

}
