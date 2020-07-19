package com.neu.pojo;

/**
 * Librarian information
 */
public class LibrarianLogin {
    
    private long id;
    private String username;
    private String password;

    public LibrarianLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public LibrarianLogin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "Login{" + "username=" + username + ", password=" + password + '}';
    }
}