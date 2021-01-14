package com.rashmi.dao.utils;

import com.rashmi.Appointment;
import com.rashmi.dao.dao.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UtilsClassicApp {

    public static void main(String[] args) {

        System.out.println("Inside utils class main");

        //creating the application context
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cp.xml");

        // Create the bean
        Dao dao = (Dao) ctx.getBean("orgDao");

        // Creating seed data
        DaoUtils.createSeedData(dao);

        // List organizations
        List<Appointment> orgs = dao.getAllCustomers();
        DaoUtils.printCustomers(orgs, DaoUtils.readOperation);

        // Create a new organization record
        Appointment org = new Appointment("bridal", "12/1/2020", "yes", "abc", "123-45-679", "2", "10:00am","abc");
        boolean isCreated = dao.createAppointment(org);
        DaoUtils.printSuccessFailure(DaoUtils.createOperation, isCreated);
        DaoUtils.printCustomers(dao.getAllCustomers(), DaoUtils.readOperation);

        // get a single organization
        Appointment org2 = dao.getAppointment(1);
        DaoUtils.printCustomer(org2, "getCustomer");

        // Updating a slogan for an organization
        Appointment org3 = dao.getAppointment(2);
        org3.setNumberOfGuests("4");
        boolean isUpdated = dao.update(org3);
        DaoUtils.printSuccessFailure(DaoUtils.updateOperation, isUpdated);
        DaoUtils.printCustomer(dao.getAppointment(2), DaoUtils.updateOperation);

        // Delete an organization
        boolean isDeleted = dao.deleteAppointment(dao.getAppointment(3));
        DaoUtils.printSuccessFailure(DaoUtils.deleteOperation, isDeleted);
        DaoUtils.printCustomers(dao.getAllCustomers(), DaoUtils.deleteOperation);

        // Cleanup
        dao.cleanup();
        DaoUtils.printCustomerCount(dao.getAllCustomers(), DaoUtils.cleanupOperation);

        // close the application context
        ((ClassPathXmlApplicationContext) ctx).close();
    }

}

