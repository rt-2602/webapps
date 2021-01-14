package com.rashmi.dao.dao;

import com.rashmi.Appointment;
import com.rashmi.NewUser;

import javax.sql.DataSource;
import java.util.List;

public interface Dao {

        // Set the datasource that will be required to create a connection to the database
        public void setDataSource(DataSource ds);

        // Create a record in the Customer table
        public boolean createAppointment(Appointment appointment);

        //Create a record in the Users table
        public boolean createNewUser(NewUser newUser);

        //authenticate user for login
        boolean authenticateUser(String userName, String password);

        //retrieve booked appointments by user
        public List<Appointment> getBookedAppointments(String userName);

        //Retrieve a single customer
        public Appointment getAppointment(Integer id);

        //Retrieve all Customers from the table
        public List<Appointment> getAllCustomers();

        //check user from user table using userName
        public boolean checkIfUserExits(String userName);

        //Delete a specific Customer from the table
        public boolean deleteAppointment(Appointment appointment);

        // Update an existing Customer
        public boolean update(Appointment appointment);

        public void cleanup();

        List<String> getBookedDates();

}
