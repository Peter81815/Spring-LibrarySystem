package com.neu.service;

import com.neu.pojo.Login;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author peichun
 */
public interface UserService {
    
    /* Login authentication */
    public Login authenticateLogin(String username, String password);

    /* Get user by username */
    public List<Login> getUsers(String searchString);

    /* Create a new user */
    public int addUser(String userName, String password);
}
