package com.wang.jmonkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wang"})
@ServletComponentScan
public class JmonkeyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmonkeyApplication.class, args);
    }
}
