package com.rashmi.service;

import com.rashmi.request.Appointment;
import com.rashmi.request.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MakeUpService {

    boolean authenticateLogin(String userName, String password);

    boolean registerNewUser(User user);

    boolean bookAppointment(Appointment appointment);

    List<Appointment> getBookedAppointments(String userName);

    String checkTodaysDate();

    List<String> checkBookedDates();

    boolean validateSession(HttpServletRequest request);

    String getMessageInfo(String message);

    boolean checkIfUserExists(String userName);

    boolean deleteAppointment(int id);

}
