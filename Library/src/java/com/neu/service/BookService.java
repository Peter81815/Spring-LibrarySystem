package com.neu.service;
import com.neu.pojo.Book;
import java.util.List;

/**
 *
 * @author peichun
 */

public interface BookService {
    
    /* Add a new book by ISBN*/
    int addBook(String isbn, int borrow);

    /* Delete a book bi id*/
    int deleteBookById(long id);

    /* Get a book by id */
    Book getById(long id);
    
    /* List of borrowed books by username */
    List<Book> getByUsername(String username);

    /* Get over due books */
    List<Book> wholeOverDue();

    /* check if the books have been over due */
    boolean overDue(List<Book> list);

    /* check out a book by id */
    int checkOutBookById(String username, long id, int borrow);
    
    /* return a book by id */
    int returnBookById(String username, long id, int borrow);
    
    /* return all books by username */
    int returnAllBookById(String username, List<Book> list, int borrow);
}
