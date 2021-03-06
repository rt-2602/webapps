package com.rashmi.service;

import com.rashmi.*;
import com.rashmi.dao.dao.HibernateDao;
import com.rashmi.request.Appointment;
import com.rashmi.request.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class MakeUpServiceImpl implements MakeUpService{

    private final MakeUp makeUp;
    private final MakeUpMessageGenerator makeUpMessageGenerator;
    private final AppointmentBooking appointmentBooking;
    private final HibernateDao hibernateDao;

    @Autowired
    public MakeUpServiceImpl(MakeUp makeUp, MakeUpMessageGenerator makeUpMessageGenerator, AppointmentBooking appointmentBooking
                             ,HibernateDao hibernateDao) {
        this.makeUp = makeUp;
        this.makeUpMessageGenerator = makeUpMessageGenerator;
        this.appointmentBooking = appointmentBooking;
        this.hibernateDao = hibernateDao;
    }

    //==init ==
    @PostConstruct
    public void init(){
        log.info("Makeup object value {}", makeUp);
        log.info("In init()");
    }


    @Override
    public String getMessageInfo(String message) {
        String messageInfo = makeUpMessageGenerator.getMessageInfo(message);
        log.info("In getMessageInfo(String message). returning " + messageInfo);
        return messageInfo;
    }


    /*before connecting to db
    @Override
    public boolean authenticateLogin(String userName, String password) {
        makeUp.setUserName(userName);
        makeUp.setPassword(password);
        makeUp.checkIsValidUser();

        boolean value = makeUp.isValidUser();

        return value;

    }*/

    @Override
    public boolean authenticateLogin(String userName, String password) {

        log.info("In authenticateLogin(String userName, String password) for userName : "+userName);

        if(hibernateDao.authenticateUser(userName,password)){

            log.info("In authenticateLogin(String userName, String password) user authenticated returning true");

            return true;
        }else{

            log.info("In authenticateLogin(String userName, String password) user not authenticated returning false");

            return false;
        }

    }

    @Override
    public boolean registerNewUser(User user) {

        log.info("In registerUser(NewUser newUser) for userName: "+ user.getUserName());

        if(hibernateDao.registerNewUser(user)){

            log.info("In registerUser(NewUser newUser) created new user in table returning true");

            return true;
        }else{

            log.info("In registerUser(NewUser newUser) new user not created in table returning false");

            return false;
        }
    }

    @Override
    public boolean bookAppointment(Appointment appointment) {

        log.info("In bookAppointment(Appointment appointment): "+appointment.toString());

        if(hibernateDao.createAppointment(appointment)){

            log.info("In bookAppointment(Appointment appointment) created new appointment in table returning true");

            return true;

        }else{

            log.info("In bookAppointment(Appointment appointment) new appointment not created in table returning false");

            return false;

        }
    }

    @Override
    public String checkTodaysDate(){

        log.info("In checkTodaysDate()");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        LocalDate todaysDate = java.time.LocalDate.now();

        log.info("In checkTodaysDate() returing " + todaysDate.plusDays(1).toString());

        return todaysDate.plusDays(1).toString();
    }

    @Override
    public List<Appointment> getBookedAppointments(String userName) {

        List<Appointment> bookedAppointments = hibernateDao.getBookedAppointments(userName);
        log.info("In getBookedAppointments(String userName) returning" +bookedAppointments.toString());

        return bookedAppointments;
    }

    @Override
    public boolean checkIfUserExists(String userName) {

        return hibernateDao.checkIfUserExits(userName);
    }

    @Override
    public boolean deleteAppointment(int id) {
        return hibernateDao.deleteAppointment(id);
    }

    @Override
    public List<String> checkBookedDates() {

        List bookedDates = hibernateDao.getBookedDates();

        log.info("In checkBookedDates() returning" + bookedDates.toString());


        return bookedDates;
    }

    @Override
    public boolean validateSession(HttpServletRequest request) {

        log.info("In validateSession(HttpServletRequest request)");

        if(request.getSession().getId().equals(request.getSession().getAttribute("MY_SESSION"))){
            log.debug("User logged in with existing session");
            return true;
        }else{
            log.debug("User session expired");
            return false;
        }
    }
}
