package com.rashmi.controller;
import com.rashmi.Appointment;
import com.rashmi.NewUser;
import com.rashmi.service.MakeUpService;
import com.rashmi.util.AttributeNames;
import com.rashmi.util.Constants;
import com.rashmi.util.MakeUpMappings;
import com.rashmi.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class MakeupController {

    //for hindi http://localhost:8080/login?locale=hi
    // for spanish http://localhost:8080/login?locale=es

    // == fields ==
    private final MakeUpService makeUpService;

    // == constructor ==
    @Autowired
    public MakeupController(MakeUpService makeUpService){
        this.makeUpService = makeUpService;
    }

    // == request methods ==
    //====== makeUp controller methods

    @GetMapping(MakeUpMappings.ERROR)
    public String errorPage(Model model){
        log.info("Entering /ERROR");
        return ViewNames.ERROR_PAGE;
    }

    @GetMapping(MakeUpMappings.LOGIN)
    public String login(){
        log.info("Entering /LOGIN - GET method");
        return ViewNames.LOGIN;
    }

    //http://localhost:8080/login
    @PostMapping(MakeUpMappings.LOGIN)
    public String processLogin(@RequestParam String userName, String password, Model model, HttpServletRequest request){
        log.info("Entering /LOGIN - POST method");

        log.info("userName={}", userName);

        if(userName == null || password == null) {
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("exiting authenticateLogin /LOGIN- POST method ");

            return ViewNames.LOGIN;
        }

        boolean value = makeUpService.authenticateLogin(userName, password);

        log.info("return value from authenticateLogin: ", value);

        if(value){
            request.getSession().setAttribute("MY_SESSION", request.getSession().getId());
            request.getSession().setAttribute("USER_NAME", userName);

            log.info("exiting authenticateLogin /LOGIN- POST method ");

            return ViewNames.AFTER_LOGIN;
        }else{
            model.addAttribute(AttributeNames.LOGIN_ERROR_MESSAGE,
                    makeUpService.getMessageInfo(Constants.LOGIN_ERROR_MESSAGE));

            log.info("exiting authenticateLogin /LOGIN- POST method ");

            return ViewNames.LOGIN;
        }

    }

    @GetMapping(MakeUpMappings.REGISTER_NEW_USER)
    public String registerNewUser(Model model){
        log.info("Entering /REGISTER_NEW_USER - GET method");

        return ViewNames.REGISTER_NEW_USER;
    }

    @PostMapping(MakeUpMappings.REGISTER_NEW_USER)
    public String registerNewUser(Model model, @ModelAttribute(AttributeNames.REGISTER_NEW_USER) NewUser newUser){

        log.info("Entering /REGISTER_NEW_USER - POST method");

        log.debug("registerNewUser from form={}", newUser.toString());

        if(newUser.getFirstName() == null || newUser.getLastName() == null || newUser.getPassword() == null || newUser.getUserName() == null) {
            model.addAttribute(AttributeNames.REGISTER_ERROR_MESSAGE,
                    makeUpService.getMessageInfo(Constants.REGISTER_ERROR_MESSAGE));

            log.debug("registerNewUser from form={} - POST method some fields are empty" + newUser.toString());

            return ViewNames.REGISTER_NEW_USER;
        }

        if(makeUpService.checkIfUserExists(newUser.getUserName())){
            model.addAttribute(AttributeNames.REGISTER_ERROR_MESSAGE,
                    makeUpService.getMessageInfo(Constants.REGISTER_ERROR_MESSAGE));

            log.debug("registerNewUser from form={} - POST method  username exists" + newUser.toString());

            return ViewNames.REGISTER_NEW_USER;
        }

        boolean value = makeUpService.registerUser(newUser);

        log.debug("registerNewUser value - POST method: " + value);

        if(value){
            log.info("exiting registerNewUser - POST method ");

            return ViewNames.REGISTERED_NEW_USER;
        }else{
            model.addAttribute(AttributeNames.REGISTER_ERROR_MESSAGE,
                    makeUpService.getMessageInfo(Constants.REGISTER_ERROR_MESSAGE));

            log.info("exiting registerNewUser - POST method ");

            return ViewNames.REGISTER_NEW_USER;
        }

    }

    @GetMapping(MakeUpMappings.SERVICES)
    public String services(Model model, HttpServletRequest request){

        log.info("Entering /SERVICES - GET method");

        if(!makeUpService.validateSession(request)){
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("exiting services - POST method ");

            return ViewNames.LOGIN;
        }

        log.info("exiting services - POST method ");

        return ViewNames.SERVICES;
    }


    @GetMapping(MakeUpMappings.BOOK_APPOINTMENT)
    public String bookAppointment(Model model, HttpServletRequest request){

        log.info("Entering /BOOK_APPOINTMENT - GET method");

        if(!makeUpService.validateSession(request)){
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("exiting bookAppointment - GET method ");

            return ViewNames.LOGIN;
        }

        model.addAttribute(AttributeNames.CHECK_TODAYS_DATE, makeUpService.checkTodaysDate());
        model.addAttribute(AttributeNames.BOOKED_DATES, makeUpService.checkBookedDates());

        log.info("exiting bookAppointment - GET method ");

        return ViewNames.BOOK_APPOINTMENT;

    }

    @PostMapping(MakeUpMappings.BOOK_APPOINTMENT)
    public String bookAppointment(@ModelAttribute(AttributeNames.BOOKED_APPOINTMENT) Appointment appointment,
                                  @RequestParam String typeOfAppointment, String guestMakeupRequired,
                                  String numberOfGuests, Model model, HttpServletRequest request){

        log.info("Entering /BOOK_APPOINTMENT - POST method" );

        if(!makeUpService.validateSession(request)){
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("exiting bookAppointment - POST method ");

            return ViewNames.LOGIN;
        }

        log.info("bookAppointment from form={}", appointment);

        appointment.setUserName(request.getSession().getAttribute("USER_NAME").toString());

        if(appointment != null &&
                (appointment.getTypeOfAppointment().equals("") ||
                        appointment.getCustomerName().equals("") ||
                        appointment.getAppointmentTime() == null ||
                        appointment.getAppointmentDate().equals("") ||
                        appointment.getGuestMakeupRequired().equals("") ||
                        appointment.getPhoneNumber().equals("")
                        )) {
            model.addAttribute(AttributeNames.BOOKING_ERROR_MESSAGE,
                    makeUpService.getMessageInfo(Constants.BOOKING_ERROR_MESSAGE));

            log.debug("exiting bookAppointment - POST method one of the values in appointment is null"
                    +appointment.toString());

            return ViewNames.BOOK_APPOINTMENT;
        }

        boolean value = makeUpService.bookAppointment(appointment);

        log.debug(" bookAppointment() value - POST method " +value);

        if(value){
            model.addAttribute(AttributeNames.BOOKED_APPOINTMENT, appointment);

            log.info("exiting bookAppointment - POST method booking successful");

            return ViewNames.APPOINTMENT_BOOKED;
        }else{
            model.addAttribute(AttributeNames.BOOKING_ERROR_MESSAGE,
                    makeUpService.getMessageInfo(Constants.BOOKING_ERROR_MESSAGE));

            log.info("exiting bookAppointment - POST method booking error");

            return ViewNames.BOOK_APPOINTMENT;
        }

    }

    @GetMapping(MakeUpMappings.BOOKED_APPOINTMENTS)
    public String bookedAppointments(Model model, HttpServletRequest request){

        log.info("Entering /BOOKED_APPOINTMENTS - GET method" );

        if(!makeUpService.validateSession(request)){
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("Exiting /BOOKED_APPOINTMENTS - GET method session time out" );

            return ViewNames.LOGIN;
        }

        List<Appointment> bookedAppointments = makeUpService.getBookedAppointments
                (request.getSession().getAttribute("USER_NAME").toString());

        log.debug(" /BOOKED_APPOINTMENTS  makeUpService.getBookedAppointments values- GET method"
                + bookedAppointments.toString());

        if(bookedAppointments.isEmpty()){
            model.addAttribute(AttributeNames.NO_BOOKED_APPOINTMENTS_MESSAGE,
                    makeUpService.getMessageInfo(Constants.NO_BOOKED_APPOINTMENTS_MESSAGE) );

            log.info("Exiting /BOOKED_APPOINTMENTS - GET method bookedAppointments list is empty");
        }

        model.addAttribute(AttributeNames.BOOKED_APPOINTMENTS, bookedAppointments);


        return ViewNames.BOOKED_APPOINTMENTS;

    }

    @GetMapping(MakeUpMappings.CONTACT)
    public String contact(Model model, HttpServletRequest request){

        log.info("Entering /CONTACT - GET method" );

        if(!makeUpService.validateSession(request)){
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("Exiting /CONTACT - GET method session timed out" );

            return ViewNames.LOGIN;
        }

        log.info("Exiting /CONTACT - GET method returning ViewNames.CONTACT" );

        return ViewNames.CONTACT;

    }

    @GetMapping(MakeUpMappings.AFTER_LOGIN)
    public String afterLogin(Model model, HttpServletRequest request){

        log.info("Entering /AFTER_LOGIN - GET method" );

        if(!makeUpService.validateSession(request)){
            model.addAttribute(AttributeNames.ERROR_MESSAGE_SESSION_TIMEDOUT,
                    makeUpService.getMessageInfo(Constants.ERROR_MESSAGE_SESSION_TIMEDOUT));

            log.info("Exiting /AFTER_LOGIN - GET method session time out returning ViewNames.LOGIN" );

            return ViewNames.LOGIN;
        }
        log.info("Exiting /AFTER_LOGIN - GET method  returning ViewNames.AFTER_LOGIN" );

        return ViewNames.AFTER_LOGIN;

    }


    @GetMapping(MakeUpMappings.PORTFOLIO)
    public String portfolio(){

        log.info("Entering /PORTFOLIO - GET method" );

        log.info("Exiting  /PORTFOLIO - GET method  returning ViewNames.PORTFOLIO" );

        return ViewNames.PORTFOLIO;

    }

    @GetMapping(MakeUpMappings.LOGOUT)
    public String processLogout(HttpServletRequest request){

        log.info("Entering /LOGOUT - GET method" );

        request.getSession().invalidate();
        log.info("Session Invalidated. "+request.getSession().getId());

        log.info("Exiting  /LOGOUT  - GET method  returning ViewNames.LOGIN" );

        return ViewNames.LOGIN;
    }

}
