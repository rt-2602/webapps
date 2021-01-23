package com.rashmi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "bridal_makeup_appointments")
@Data
@Table(name = "bridal_makeup_appointments")
public class UserAppointmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_of_appointment")
    @Size(max = 45, min = 3)
    //@Size(max = 20, min = 3, message = "{user.name.invalid}")
    // @NotEmpty(message="Please Enter your name")
    private String typeOfAppointment;

    @Column(name = "appointment_date")
    //@Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String appointmentDate;

    @Column(name = "guest_makeup_required")
    //@Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String guestMakeupRequired;

    @Column(name = "customer_name")
    //@Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String customerName;

    @Column(name = "phone_number")
   // @Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String phoneNumber;

    @Column(name = "number_of_guests")
    //@Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String numberOfGuests;

    @Column(name = "appointment_time")
   // @Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String appointmentTime;

    @Column(name = "user_name")
    //@Size(max = 20, min = 3)
    // @NotEmpty(message="Please Enter your name")
    private String userName;

    public UserAppointmentsEntity() {
    }
}

