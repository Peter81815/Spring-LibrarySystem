package com.neu.pojo;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/* 
 * borrow=0: book is in library
 * borrow=1: book is borrowed by someone(username)
 */
public class Book {
    private long id;
    private String isbn; 
    private int borrow;
    private String username;
    @Temporal(TemporalType.DATE)
    private Date time;

    
    public Book(){}

    public void setId(long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getBorrow() {
        return borrow;
    }
    
    public Date getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    } 
}
