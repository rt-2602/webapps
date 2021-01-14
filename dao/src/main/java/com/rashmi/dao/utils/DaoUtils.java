package com.rashmi.dao.utils;

import com.rashmi.Appointment;
import com.rashmi.dao.dao.Dao;

import java.util.ArrayList;
import java.util.List;

public class DaoUtils {

    public static final String createOperation = "CREATE";
    public static final String readOperation = "READ";
    public static final String updateOperation = "UPDATE";
    public static final String deleteOperation = "DELETE";
    public static final String cleanupOperation = "TRUNCATE";

    public static void printCustomers(List<Appointment> appointments, String operation){
        System.out.println("\n********* printing customers after " + operation + " operation *********");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public static void printCustomer(Appointment appointment, String operation) {
        System.out.println("\n ******** Printing customer after invoking " + operation + " *** \n" + appointment);
    }

    public static void printSuccessFailure(String operation, boolean param){
        if(param)
            System.out.println("\nOperation " + operation + " successful");
        else
            System.out.println("\nOperation " + operation + " failed");
    }

    public static void createSeedData(Dao dao){
        Appointment appointment1 = new Appointment("bridal", "12/1/2020", "yes", "abc", "123-45-678", "2", "10:00am","abc");
        Appointment appointment2 = new Appointment("bridalHD", "12/1/2020", "yes", "def", "123-78-456", "5", "10:00am","abc");

        List<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(0, appointment1); appointments.add(1, appointment2);
        //int orgCount = orgs.size();
        int createCount = 0;
        for(Appointment appointment : appointments){
            boolean isCreated  = dao.createAppointment(appointment);
            if(isCreated)
                createCount += 1;
        }

        System.out.println("Created "+ createCount + " customers");
    }


    public static void printCustomerCount(List<Appointment> orgs, String operation){
        System.out.println("\n*********Currently we have " + orgs.size()+ " customers after " + operation + " operation" + "   *********");

    }

}
