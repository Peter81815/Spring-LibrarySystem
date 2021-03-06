package com.neu.service;

import com.neu.pojo.LibrarianLogin;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author peichun
 */
public class LibrarianServiceImpl implements LibrarianService{
    private static final SessionFactory sf = new  Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;
    
    private Session getSession(){
        if (session == null || !session.isOpen()){
            session = sf.openSession();
        }
        return session;
    }
   
    private void beginTransaction(){
        getSession().beginTransaction();
    }
    
    private void commit(){
        getSession().getTransaction().commit();;
    }
    
    private void close(){
        if (session !=null){
            getSession().close();
        }
    }
    
    private void rollbackTransaction(){
        getSession().getTransaction().rollback();
    }
    
    /**
     * Login authentication
     *
     * @param username
     * @param password
     */
    @Override
    public LibrarianLogin authenticateLogin(String username, String password) {
        LibrarianLogin loggedInUser = null;
        try {
            beginTransaction();
            Query q= getSession().createQuery("from LibrarianLogin where username= :username AND password= :password");
            q.setString("username", username);
            q.setString("password", password);
            loggedInUser = (LibrarianLogin)q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }
        return loggedInUser;
    }
    
    /**
     * Get librarian by username
     * @param searchString
     */
    @Override
    public List<LibrarianLogin> getUsers(String searchString) {
        List<LibrarianLogin> matchedUsers = new ArrayList<LibrarianLogin>() ;
        try {
            beginTransaction();
            Query q= getSession().createQuery("from LibrarianLogin where username= :username");
            q.setString("username", searchString);
            matchedUsers = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }
        return matchedUsers;
    }

    /**
     * Create a new librarian
     * @param userName
     * @param password
     */
    @Override
    public int addUser(String userName, String password) {
        LibrarianLogin newUser = null;
        int registerSuccess = 0;
        try {
            beginTransaction();
            
            newUser = new LibrarianLogin();
            newUser.setUsername(userName);
            newUser.setPassword(password);
            getSession().save(newUser);
            commit();
            registerSuccess = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }
        return registerSuccess;
    }
}

