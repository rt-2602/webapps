package com.rashmi.service;

import com.rashmi.Appointment;
import com.rashmi.NewUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MakeUpService {

    boolean authenticateLogin(String userName, String password);

    boolean registerUser(NewUser newUser);

    boolean bookAppointment(Appointment appointment);

    List<Appointment> getBookedAppointments(String userName);

    String checkTodaysDate();

    List<String> checkBookedDates();

    boolean validateSession(HttpServletRequest request);

    String getMessageInfo(String message);

    boolean checkIfUserExists(String userName);

}
