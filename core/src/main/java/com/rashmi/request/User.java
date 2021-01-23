package com.rashmi.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class User {

    int id;
    String firstName;
    String lastName;
    String userName;
    String password;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }


}
