package com.rashmi.dao.dao;

import com.rashmi.request.Appointment;
import com.rashmi.request.User;

import java.util.List;

public interface HibernateDao {

    // Create a record in the Customer table
    public boolean createAppointment(Appointment appointment);

    //Create a record in the Users table
    public boolean registerNewUser(User user);

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
    public boolean deleteAppointment(int id);

    // Update an existing Customer
    public boolean update(String userName, Appointment appointment);

    public void cleanup();

    List<String> getBookedDates();
}