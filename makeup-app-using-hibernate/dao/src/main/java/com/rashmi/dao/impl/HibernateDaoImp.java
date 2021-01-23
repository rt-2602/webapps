package com.rashmi.dao.impl;

import com.rashmi.converter.UserAppointmentsConverter;
import com.rashmi.converter.UsersConverter;
import com.rashmi.dao.dao.HibernateDao;
import com.rashmi.entity.UserAppointmentsEntity;
import com.rashmi.entity.UsersEntity;
import com.rashmi.request.Appointment;
import com.rashmi.request.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class HibernateDaoImp implements HibernateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserAppointmentsConverter userAppointmentsConverter;

    @Autowired
    private UsersConverter usersConverter;

    @Override
    public boolean createAppointment(Appointment appointment) {
        log.info("In createAppointment(). userName:" + appointment.getUserName());

        Session session =null;

        if (appointment.getGuestMakeupRequired().equals("N")) {
            log.info("In createAppointment().guestMakeUpRequired= N. Setting number of guests to 0");
            appointment.setNumberOfGuests("0");
        }

        try {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            sessionFactory.getCurrentSession().save(userAppointmentsConverter.convertVOtoDO(appointment));
            session.getTransaction().commit();
            System.out.println(appointment.getCustomerName() + " got added");
            return true;

        } catch (Exception e) {
            log.info("In createAppointment(). Appointment not created. Exception e. returning false." + e.getMessage());
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }

    }



    @Override
    public boolean registerNewUser(User user) {
        log.info("In registerNewUser. username: " + user.getUserName());

        log.debug("In registerNewUser. NewUser: " + user.toString());

        Session session =null;

        try
        {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            sessionFactory.getCurrentSession().save(usersConverter.convertVOtoDO(user));
            session.getTransaction().commit();
            System.out.println(user.getUserName() + " got added");
            return true;


        } catch (Exception e) {

            log.debug("In createNewUser. Exception e. NewUser: " + user.toString(), e.getMessage());
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean authenticateUser(String userName, String password) {
        log.info("In authenticateUser. userName: " +userName);

        boolean flag = false;

        //String sqlQuery = "SELECT * FROM user WHERE user_name = ? and password = ? ";

        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query q = session.createQuery("from user where userName=:n and password=:p");
        q.setParameter("n",userName);
        q.setParameter("p",password);

        try {

            UsersEntity usersEntity = (UsersEntity) q.getSingleResult();

            //convert object of usersentity to users
            User user = usersConverter.convertDOtoVO(usersEntity);

            if(user != null) {
                log.debug("In authenticateUser. User authenticate. Returning true. userName" +userName);
                flag = true;
            }
        }catch (Exception e) {
            log.debug("In authenticateUser. Returning false. Exception e :" + e.getMessage());
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }

        return flag;
    }

    @Override
    public List<Appointment> getBookedAppointments(String userName) {
        log.info("In getBookedAppointments. username" +userName);
        List<UserAppointmentsEntity> userAppointmentsEntityList;

        //SELECT * FROM makeup_artist_application_db.bridal_makeup_appointments WHERE user_name='';
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query q = session.createQuery("from bridal_makeup_appointments where userName=:n");
        q.setParameter("n",userName);

        //to get all rows use username, password: admin admin
        if(userName.equals("admin")) {
            q = session.createQuery("from bridal_makeup_appointments");
            log.debug("In getBookedAppointments. Returning all booked appointments for admin. For userName: " + userName);
        }
        userAppointmentsEntityList = q.list();

        List<Appointment> appointment = userAppointmentsConverter.convertDOtoVO(userAppointmentsEntityList);

        if (session != null) {
            session.close();
        }

        log.debug("In getBookedAppointments. Returning booked appointments for userName: " + userName);

        return appointment;
    }

    @Override
    public Appointment getAppointment(Integer id) {

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            UserAppointmentsEntity appointmentsEntity = session.get(UserAppointmentsEntity.class, id);
            Appointment appointment = userAppointmentsConverter.convertDOtoVO(appointmentsEntity);
            session.getTransaction().commit();
            return appointment;
        }catch (Exception e) {
            log.debug("In getAppointment exception e. Returning false .  "  +e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
       return null;
    }

    @Override
    public boolean checkIfUserExits(String userName) {
        log.info("In checkIfUserExits userName: " + userName);
        boolean flag = false;
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            //.get method to get a single row from user table
            UsersEntity usersEntity = session.get(UsersEntity.class, userName);

            /*//to get all rows. write partial query with name of entity class @Entity(name = "user")
            List<UsersEntity> usersList = session.createQuery("from user").getResultList();
            commit before returning file will throw an exception */

            if(usersEntity != null && usersEntity.getUserName().equals(userName)) {
                flag = true;
            }

        }catch (Exception e) {
            log.debug("In checkIfUserExits exception e. Returning false . userName: " + userName +e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
        log.info("Exiting checkIfUserExits flag: " + flag);
        return flag;
    }


    @Override
    public void cleanup() {

        log.info("In cleanUp");

        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("truncate table bridal_makeup_appointments").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            log.debug("In checkIfUserExits exception e. Returning false: " +e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }

        log.info("Exiting cleanUp");
    }

    @Override
    public List<String> getBookedDates() {
        log.info("In getBookedDates");

        Session session = null;
       // String sqlQuery = "SELECT distinct appointment_date FROM  bridal_makeup_appointments";
        try {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();

            Query q = session.createQuery("SELECT appointmentDate FROM bridal_makeup_appointments");

            List<String> bookedDatesList = q.list();


            log.debug("In getBookedDates. Returning bookedDatesList : " + bookedDatesList.toString());
            return bookedDatesList;
        }catch (Exception e) {
            log.debug("In checkIfUserExits exception e. Returning false  : " +e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }


    // for future release1
    @Override
    public boolean deleteAppointment(int id) {

        log.info("In deleteAppointment");

        boolean flag = false;
        Session session = null;
        // String sqlQuery = "SELECT distinct appointment_date FROM  bridal_makeup_appointments";
        try {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            UserAppointmentsEntity deleteThisAppointment = session.get(UserAppointmentsEntity.class, id);
            session.delete(deleteThisAppointment);
            session.getTransaction().commit();

            flag = true;

           /* Query q = session.createQuery("DELETE FROM bridal_makeup_appointments WHERE userName=:n");
            q.setParameter("n", userName);
            int row = q.executeUpdate();

            session.getTransaction().commit();


            if (row == 0) {
                System.out.println("Could'nt delete row!");
            } else {
                System.out.println("Deleted Row: " + row);
                return true;
            }*/
        }catch (Exception e) {
            log.debug("In deleteAppointment exception e. Returning false  : " +e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return flag;

    }

    @Override
    public boolean update(String userName, Appointment appointment) {
        log.info("In deleteAppointment");

        Session session = null;

        try {
            session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();

            UserAppointmentsEntity userAppointmentsDO = userAppointmentsConverter.convertVOtoDO(appointment);

            UserAppointmentsEntity userAppointmentsEntity = session.get(UserAppointmentsEntity.class, userName);
            userAppointmentsEntity.setAppointmentDate(userAppointmentsDO.getAppointmentDate());
            userAppointmentsEntity.setAppointmentTime(userAppointmentsDO.getAppointmentTime());


            /*int row = q.executeUpdate();

            if (row == 0) {
                System.out.println("Could'nt delete row!");
            } else {
                System.out.println("Deleted Row: " + row);
                return true;
            }*/
        }catch (Exception e) {
            log.debug("In deleteAppointment exception e. Returning false  : " +e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public List<Appointment> getAllCustomers() {
        return null;
    }

}

