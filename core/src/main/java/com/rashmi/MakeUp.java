package com.rashmi;

public interface MakeUp {

    void setUserName(String userName);

    void setPassword(String password);

    boolean isValidUser();

    void checkIsValidUser();

    boolean bookAppointment(Appointment appointment);

}
