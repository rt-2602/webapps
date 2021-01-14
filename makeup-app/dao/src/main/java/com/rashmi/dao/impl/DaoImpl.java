package com.rashmi.dao.impl;

import com.rashmi.Appointment;
import com.rashmi.NewUser;
import com.rashmi.dao.dao.Dao;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Data
@Repository("orgDao")
public class DaoImpl implements Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {

        log.debug("In setDataSource" + dataSource.toString());
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean checkIfUserExits(String userName){

        log.info("In checkIfUserExits userName: " + userName);

        boolean flag = false;

        String sqlQuery = "SELECT * FROM makeup_artist_application_db.user WHERE user_name  = ? ";

        Object[] args = new Object[] {userName};

        try {

            NewUser newUser = jdbcTemplate.queryForObject(sqlQuery, args, new NewUserRowMapper());

            if(newUser != null) {

                log.debug("In checkIfUserExits user with username found. Returning true. userName: " + userName);

                flag = true;
            }
        }catch (Exception e) {

            log.debug("In checkIfUserExits exception e. Returning false . userName: " + userName +e.getMessage());
            return false;
        }

        return flag;

    }


    @Override
    public boolean createNewUser(NewUser newUser) {

        log.info("In createNewUser. username: " +newUser.getUserName());

        log.debug("In createNewUser. NewUser: " + newUser.toString());

        String sqlQueryBridal = "INSERT INTO user(user_name, password, first_name, last_name)" +
                "VALUES(?, ?, ?, ?)";

        Object[] args = new Object[]{newUser.getUserName(), newUser.getPassword(), newUser.getFirstName(), newUser.getLastName()};

        try
        {
            if (jdbcTemplate.update(sqlQueryBridal, args) == 1) {

                log.debug("In createNewUser. Inserted into table. NewUser: " + newUser.toString());

                return true;
            }
            else {

                log.debug("In createNewUser. Could'nt insert=into table. NewUser: " + newUser.toString());

                return false;
            }
        } catch (Exception e) {

            log.debug("In createNewUser. Exception e. NewUser: " + newUser.toString(), e.getMessage());
            return false;
        }
    }

    public boolean createAppointment(Appointment appointment) {

        log.info("In createAppointment(). userName:" +appointment.getUserName());

        String sqlQueryBridal = "INSERT INTO bridal_makeup_appointments(type_of_appointment, appointment_date, guest_makeup_required, customer_name, phone_number, number_of_guests, appointment_time, user_name)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        if(appointment.getGuestMakeupRequired().equals("N")){
            log.info("In createAppointment().guestMakeUpRequired= N. Setting number of guests to 0");
            appointment.setNumberOfGuests("0");
        }

        Object[] args = new Object[]{appointment.getTypeOfAppointment(), appointment.getAppointmentDate(), appointment.getGuestMakeupRequired(),
                appointment.getCustomerName(), appointment.getPhoneNumber(), appointment.getNumberOfGuests(), appointment.getAppointmentTime(), appointment.getUserName()};

        try {
            if (jdbcTemplate.update(sqlQueryBridal, args) == 1) {
                log.info("In createAppointment(). Appointment created. returning true.");
                log.debug("In createAppointment(). Appointment created. returning true. appointment" +appointment.toString());
                return true;
            } else {
                log.info("In createAppointment(). Appointment not created. returning false.");
                return false;
            }
        }catch (Exception e) {
            log.info("In createAppointment(). Appointment not created. Exception e. returning false." +e.getMessage());
            return false;
        }

    }

    public Appointment getAppointment(Integer id) {

        log.info("In getAppointment.");
        log.info("In getAppointment. id" + id);

        String sqlQuery = " SELECT id, type_of_appointment, appointment_date, guest_makeup_required, customer_name, phone_number, number_of_guests, appointment_time" +
                " FROM bridal_makeup_appointments WHERE id = ? ";

        Object[] args = new Object[] {id};
        Appointment appointment = jdbcTemplate.queryForObject(sqlQuery, args, new MakeupRowMapper());

        log.debug("In getAppointment. Returning appointment: " +appointment.toString());

        return appointment;
    }

    @Override
    public List<Appointment> getBookedAppointments(String userName) {

        log.info("In getBookedAppointments. username" +userName);

        //SELECT * FROM makeup_artist_application_db.bridal_makeup_appointments WHERE user_name='Jedu';
        String sqlQuery = "SELECT * FROM makeup_artist_application_db.bridal_makeup_appointments WHERE user_name = ? ";
        Object[] args = new Object[] {userName};

        if(userName.equals("admin")) {
            sqlQuery = "SELECT * FROM makeup_artist_application_db.bridal_makeup_appointments";
            args = new Object[] {};
            log.debug("In getBookedAppointments. Returning all booked appointments for admin. For userName: " + userName);
        }

        List<Appointment> appointment = jdbcTemplate.query(sqlQuery, args, new MakeupRowMapper());

        log.debug("In getBookedAppointments. Returning booked appointments for userName: " + userName);

        return appointment;

    }

    public boolean authenticateUser(String userName, String password){

        log.info("In authenticateUser. userName: " +userName);

        boolean flag = false;

        String sqlQuery = "SELECT * FROM user WHERE user_name = ? and password = ? ";

        Object[] args = new Object[] {userName, password};
        try {
            NewUser newUser = jdbcTemplate.queryForObject(sqlQuery, args, new NewUserRowMapper());

            if(newUser != null) {
                log.debug("In authenticateUser. User authenticate. Returning true. userName" +userName);
                flag = true;
            }
        }catch (Exception e) {
            log.debug("In authenticateUser. Returning false. Exception e :" + e.getMessage());
            return false;
        }

        return flag;
    }

    public List<String> getBookedDates(){

        log.info("In getBookedDates");

        String sqlQuery = "SELECT distinct appointment_date FROM  bridal_makeup_appointments";

        List<String> bookedDatesList = jdbcTemplate.queryForList(sqlQuery,String.class);

        log.debug("In getBookedDates. Returning bookedDatesList : " + bookedDatesList.toString());
        return bookedDatesList;
    }

    public List<Appointment> getAllCustomers() {

        log.info("In getAllCustomers");

        String sqlQuery = "SELECT * FROM  bridal_makeup_appointments";
        List<Appointment> customersList = jdbcTemplate.query(sqlQuery, new MakeupRowMapper());

        log.debug("In getAllCustomers. Returning all customers List:" +customersList.toString());
        return customersList;
    }

    public boolean deleteAppointment(Appointment appointment) {

        log.info("In deleteAppointment");

        String sqlQuery = "DELETE FROM bridal_makeup_appointments WHERE id = ?";
        Object[] args = new Object[] { appointment.getId() };

        if(jdbcTemplate.update(sqlQuery, args) == 1){
            log.debug("In deleteAppointment. Returning true. Deleted appointment :" +appointment.toString());
            return true;
        }else {
            log.debug("In deleteAppointment. Returning false. Could'nt delete appointment :" +appointment.toString());
            return false;
        }
    }

    public boolean update(Appointment appointment) {

        log.info("In update");

        String sqlQuery = "UPDATE bridal_makeup_appointments SET number_of_guests = ? WHERE id = ?";
        Object[] args = new Object[] {appointment.getNumberOfGuests(), appointment.getId() };

        if(jdbcTemplate.update(sqlQuery, args) == 1){
            log.debug("In update. Returning true. update appointment :" +appointment.toString());
            return true;
        }else {
            log.debug("In update. Returning false. Could'nt update appointment :" +appointment.toString());
            return false;
        }

    }

    public void cleanup() {

        log.info("In cleanup");

        String sqlQuery = "TRUNCATE TABLE bridal_makeup_appointments";
        jdbcTemplate.execute(sqlQuery);
    }
}

