package com.rashmi.dao.impl;

import com.rashmi.request.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeupRowMapper implements RowMapper<Appointment> {

    public Appointment mapRow(ResultSet rs, int rownum) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));
        appointment.setTypeOfAppointment(rs.getString("type_of_appointment"));
        appointment.setAppointmentDate(rs.getString("appointment_date"));
        appointment.setGuestMakeupRequired(rs.getString("guest_makeup_required"));
        appointment.setCustomerName(rs.getString("customer_name"));
        appointment.setPhoneNumber(rs.getString("phone_number"));
        appointment.setNumberOfGuests(rs.getString("number_of_guests"));
        appointment.setAppointmentTime(rs.getString("appointment_time"));
        appointment.setUserName(rs.getString("user_name"));
        return appointment;
    }


    /*public NewUser mapRow(ResultSet rs, int rownum) throws SQLException {
        NewUser newUser = new NewUser();
        newUser.setId(rs.getInt("id"));
        newUser.setUserName(rs.getString("user_name"));
        newUser.setPassword(rs.getString("password"));
        newUser.setFirstName(rs.getString("first_name"));
        newUser.setLastName(rs.getString("last_name"));
        return newUser;
    }
*/


}