package com.rashmi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user")
@Data
@Table(name = "user")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    // @NotEmpty(message="Please Enter your name")
    private String userName;

    @Column(name = "password")
    //@Size(max = 20, min = 3, message = "{user.password.invalid}")
    // @NotEmpty(message="Please Enter your name")
    private String password;

    @Column(name = "first_name")
   // @Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String firstName;

    @Column(name = "last_name")
    //@Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String lastName;

    public UsersEntity() {
    }
}
