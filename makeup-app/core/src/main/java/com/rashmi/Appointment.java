package com.rashmi;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class Appointment {

    private int id;

    private String typeOfAppointment;

    private String appointmentDate;

    private String appointmentTime;

    private String guestMakeupRequired;

    private String customerName;

    private String phoneNumber;

    private String numberOfGuests;

    private String userName;


    public Appointment() {
    }

    public Appointment(String typeOfAppointment, String appointmentDate, String guestMakeupRequired, String customerName, String phoneNumber, String numberOfGuests, String appointmentTime, String userName) {
        this.typeOfAppointment = typeOfAppointment;
        this.appointmentDate = appointmentDate;
        this.guestMakeupRequired = guestMakeupRequired;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.numberOfGuests = numberOfGuests;
        this.appointmentTime = appointmentTime;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "type='" + typeOfAppointment + '\'' +
                ", date=" + appointmentDate +
                ", guest=" + guestMakeupRequired +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", appointmentTime=" + appointmentTime +
                ", userName=" + userName +
                '}';
    }
}
