package com.rashmi;

import com.rashmi.request.Appointment;

public interface MakeUp {

    void setUserName(String userName);

    void setPassword(String password);

    boolean isValidUser();

    void checkIsValidUser();

    boolean bookAppointment(Appointment appointment);

}
