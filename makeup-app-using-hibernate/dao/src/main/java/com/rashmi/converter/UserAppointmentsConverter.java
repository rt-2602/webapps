package com.rashmi.converter;

import com.rashmi.entity.UserAppointmentsEntity;
import com.rashmi.request.Appointment;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UserAppointmentsConverter {



    public UserAppointmentsEntity convertVOtoDO(Appointment appointment){

        UserAppointmentsEntity userAppointmentsEntity = new UserAppointmentsEntity();
        userAppointmentsEntity.setAppointmentDate(appointment.getAppointmentDate());
        userAppointmentsEntity.setAppointmentTime(appointment.getAppointmentTime());
        userAppointmentsEntity.setCustomerName(appointment.getCustomerName());
        userAppointmentsEntity.setGuestMakeupRequired(appointment.getGuestMakeupRequired());
        userAppointmentsEntity.setNumberOfGuests(appointment.getNumberOfGuests());
        userAppointmentsEntity.setPhoneNumber(appointment.getPhoneNumber());
        userAppointmentsEntity.setTypeOfAppointment(appointment.getTypeOfAppointment());
        userAppointmentsEntity.setUserName(appointment.getUserName());

        return userAppointmentsEntity;

    }

    public Appointment convertDOtoVO(UserAppointmentsEntity userAppointmentsEntity){

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(userAppointmentsEntity.getAppointmentDate());
        appointment.setAppointmentTime(userAppointmentsEntity.getAppointmentTime());
        appointment.setCustomerName(userAppointmentsEntity.getCustomerName());
        appointment.setGuestMakeupRequired(userAppointmentsEntity.getGuestMakeupRequired());
        appointment.setNumberOfGuests(userAppointmentsEntity.getNumberOfGuests());
        appointment.setPhoneNumber(userAppointmentsEntity.getPhoneNumber());
        appointment.setUserName(userAppointmentsEntity.getUserName());
        appointment.setTypeOfAppointment(userAppointmentsEntity.getTypeOfAppointment());
        return appointment;
    }

    public List<Appointment> convertDOtoVO(List<UserAppointmentsEntity> userAppointmentsEntity){

        Appointment appointment;
        List<Appointment> appointmentList = new ArrayList<>();

        for(UserAppointmentsEntity i : userAppointmentsEntity ) {
            appointment = new Appointment();
            appointment.setId(i.getId());
            appointment.setAppointmentDate(i.getAppointmentDate());
            appointment.setAppointmentTime(i.getAppointmentTime());
            appointment.setCustomerName(i.getCustomerName());
            appointment.setGuestMakeupRequired(i.getGuestMakeupRequired());
            appointment.setNumberOfGuests(i.getNumberOfGuests());
            appointment.setPhoneNumber(i.getPhoneNumber());
            appointment.setUserName(i.getUserName());
            appointment.setTypeOfAppointment(i.getTypeOfAppointment());
            appointmentList.add(appointment);
        }

        return appointmentList;
    }


}
