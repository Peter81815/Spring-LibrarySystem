package com.neu.service;

import com.neu.pojo.Book;
import com.neu.service.BookService;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;

/**
 *
 * @author peichun
 */
public class BookServiceImpl implements BookService{
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
        getSession().getTransaction().commit();
    }
    
    
    private void close(){
        if (session !=null)
        {
            getSession().close();
        }
    }
    
    private void rollbackTransaction(){
        getSession().getTransaction().rollback();
    }
    
    /**
     * Add a new book by ISBN
     *
     * @param isbn
     * @param borrow
     */
    @Override
    public int addBook(String isbn, int borrow){
        int result = 0;
        try {
            Book book = new Book();
            book.setIsbn(isbn);
            book.setBorrow(borrow);
            beginTransaction();
            Session session = getSession();
            session.save(book);
            commit();
            result = 1;

        } 
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    /**
     * Delete a book bi id
     *
     * @param id
     */
    @Override
    public int deleteBookById(long id) {
        int result = 0;
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Book where id= :id");
            q.setLong("id", id);
            Book bookToDelete = (Book) q.uniqueResult();
            if(getSession()!=null)
                getSession().delete(bookToDelete);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    /**
     * Login Get a book by id
     *
     * @param id
     */
    @Override
    public Book getById(long id) {
        Book result = null;
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Book where id= :id");
            q.setLong("id", id);
            result = (Book) q.uniqueResult();
            commit();
            
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

    /**
     * List of borrowed books by username
     *
     * @param username
     */
    @Override
    public List<Book> getByUsername(String username) {
        List<Book> list = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Book where username=:username ");
            q.setString("username", username);
            list = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return list;
    }
   
    /**
     * Get over due books
     */
    @Override
    public List<Book> wholeOverDue() {
        List<Book> list = new ArrayList<>();
        try {
            java.util.Date first = new java.util.Date(System.currentTimeMillis() - java.time.Duration.ofDays(14).toMillis());
            java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
            int borrow=1;
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Book where borrow=:borrow and time not between :first and :currentDate");
            q.setParameter("borrow", borrow);
            q.setParameter("first", first);
            q.setParameter("currentDate", currentDate);
            list = q.list();
            System.out.println(list.size());
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return list;
    }
    
    /**
     * check if the books have been over due
     *
     * @param list
     */
    @Override
    public boolean overDue(List<Book> list) {
        boolean res=false;
        java.util.Date first = new java.util.Date(System.currentTimeMillis() - java.time.Duration.ofDays(14).toMillis());
        for (Book b : list) {
            if (b.getTime().before(first)) {
                res = true;
            }
        }
        return res;
    }
    
    /**
     * check out a book by id
     *
     * @param username
     * @param id
     * @param borrow
     */
    @Override
    public int checkOutBookById(String username, long id, int borrow) {
        int result = 0;
        try {
            beginTransaction();
            java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
            Query q = getSession().createQuery("UPDATE Book SET username = :username,time = :currentDate,borrow =:borrow WHERE id= :id");
            q.setString("username", username);
            q.setParameter("currentDate", currentDate);
            q.setParameter("id", id);
            q.setParameter("borrow", borrow);
            q.executeUpdate();
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    /**
     * return a book by id
     *
     * @param username
     * @param id
     * @param borrow
     */
    @Override
    public int returnBookById(String username, long id, int borrow) {
        int result = 0;
        try {
            beginTransaction();
            Query q = getSession().createQuery("UPDATE Book SET username = :temp,borrow =:borrow WHERE id= :id");
            String temp="";
            q.setString("temp", temp);
            q.setParameter("id", id);
            q.setParameter("borrow", borrow);
            q.executeUpdate();
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    /**
     * return all books by username
     *
     * @param username
     * @param list
     * @param borrow
     */
    @Override
    public int returnAllBookById(String username, List<Book> list, int borrow) {
        int result = 0;
        for(Book b:list){
            long id=b.getId();
            try {
                beginTransaction();
                Query q = getSession().createQuery("UPDATE Book SET username = :username,borrow =:borrow WHERE id= :id");
                String temp="";
                q.setString("username", temp);
                q.setParameter("id", id);
                q.setParameter("borrow", borrow);
                q.executeUpdate();
                commit();
                result = 1;
            } catch (HibernateException e) {
                e.printStackTrace();
                rollbackTransaction();
            } finally {
                close();
            }
        }
        return result;
    }
}
