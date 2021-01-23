package com.rashmi.util;

import lombok.Getter;

@Getter
public class Constants {

    // values of these fields are used in MessageGeneratorImpl"s getMessage(String code, Object... args) method
    //which gets the messages from messages.properties file

    public static final String LOGIN_ERROR_MESSAGE = "makeUp.error.message";

    public static final String BOOKING_ERROR_MESSAGE = "makeUp.error.message.booking";

    public static final String REGISTER_ERROR_MESSAGE = "makeup.error.message.register";

    public static final String ERROR_MESSAGE_SESSION_TIMEDOUT = "makeup.error.message.session.timed.out";

    public static final String NO_BOOKED_APPOINTMENTS_MESSAGE ="no.booked.appointments.message";

    public static final String APPOINTMENT_DELETED_MESSAGE = "makeUp.appointment.delete.message";


}
