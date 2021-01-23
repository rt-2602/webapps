package com.rashmi;

import com.rashmi.request.Appointment;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j

@Component
public class MakeUpImpl implements MakeUp{


    // ==fields==
    @Setter
    private String userName;

    @Setter
    private String password;

    @Getter
    private boolean isValidUser ;

    @Autowired
    AppointmentBooking appointmentBooking;

    @Override
    public void checkIsValidUser(){
        if(userName.equals("abc") && password.equals("123")){
            isValidUser = true;
        }else {
            isValidUser = false;
        }
    }

    @Override
    public boolean bookAppointment(Appointment appointment) {
        if(appointmentBooking.bookAppointment(appointment)){
            return true;
        }else{
            return false;
        }
    }


}
