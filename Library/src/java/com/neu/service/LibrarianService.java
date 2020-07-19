package com.neu.service;

import com.neu.pojo.LibrarianLogin;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author peichun
 */
public interface LibrarianService {
    
    /* Login authentication */
    public LibrarianLogin authenticateLogin(String username, String password);

    /* Get user by username */
    public List<LibrarianLogin> getUsers(String searchString);

    /* Create a new librarian */
    public int addUser(String userName, String password);
}
