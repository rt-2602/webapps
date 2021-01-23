package com.rashmi;

import com.rashmi.request.Appointment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class AppointmentBookingImpl implements AppointmentBooking{

    // == fields ==

    private ArrayList<Appointment> bridalAppointmentList = new ArrayList<>();

    private ArrayList<Appointment> bridalHDAppointmentList = new ArrayList<>();

    private ArrayList<Appointment> engagementAppointmentList = new ArrayList<>();


    @Autowired
    Appointment appointment1;



    public boolean bookAppointment(Appointment appointment) {

        if (appointment.getTypeOfAppointment().equals("bridal")) {

            if (bridalAppointmentList.isEmpty()) {
                bridalAppointmentList.add(appointment);
                return true;
            } else {

                for (Appointment a : bridalAppointmentList) {
                    if (a.getAppointmentDate().equals(appointment.getAppointmentDate())) {
                        return false;
                    } else {
                        bridalAppointmentList.add(appointment);
                        return true;
                    }
                }
            }
        }

        if (appointment.getTypeOfAppointment().equals("bridalHD")) {
            if (bridalHDAppointmentList.isEmpty()) {
                bridalHDAppointmentList.add(appointment);
                return true;
            } else {

                for (Appointment a : bridalHDAppointmentList) {
                    if (a.getAppointmentDate().equals(appointment.getAppointmentDate())) {
                        return false;
                    } else {
                        bridalHDAppointmentList.add(appointment);
                        return true;
                    }
                }
            }
        }

        if (appointment.getTypeOfAppointment().equals("engagement")) {
            if (engagementAppointmentList.isEmpty()) {
                engagementAppointmentList.add(appointment);
                return true;
            } else {

                for (Appointment a : engagementAppointmentList) {
                    if (a.getAppointmentDate().equals(appointment.getAppointmentDate())) {
                        return false;
                    } else {
                        engagementAppointmentList.add(appointment);
                        return true;
                    }
                }
            }
        }
        return false;
    }



}
