package com.rashmi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan
public class ConsoleMain {

    public static void main(String[] args) {
       log.info("Make up app - Console Main()");

        //this line runs the spring boot application
        SpringApplication.run(ConsoleMain.class, args);
    }
}
