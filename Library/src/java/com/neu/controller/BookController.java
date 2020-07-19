package com.neu.controller;

import com.neu.service.BookServiceImpl;
import com.neu.pojo.LibrarianLogin;
import com.neu.pojo.Login;
import com.neu.pojo.Book;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author peichun
 */
public class BookController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        LibrarianLogin loggedUser = (LibrarianLogin) session.getAttribute("librarian");
        Login user = (Login) session.getAttribute("user");
        String option = request.getParameter("choice") == null ? "" : request.getParameter("choice");

        if (loggedUser == null && user==null ) {
            if(loggedUser == null)
                mv = new ModelAndView("Librarian");
            if(user == null)
                mv = new ModelAndView("User");
        } 
        else{
            BookServiceImpl bookDao = (BookServiceImpl) getApplicationContext().getBean("BookDAO");
            switch (option) {
                
                case "Add":
                    mv = new ModelAndView("Bookinfo");
                    break;
                
                case "submitbook":
                    String isbn = request.getParameter("bookIsbn");
                    int borrow = 0;
                
                    if (isbn.length() < 8) {
                        mv = new ModelAndView("Bookinfo");
                    } 
                    else {
                        if (bookDao.addBook(isbn, borrow) == 1) {
                            mv = new ModelAndView("addBookSuccess");
                        } 
                        else {
                            mv = new ModelAndView("error");
                        }
                    }
                    break;
                    
                case "Remove":
                    mv = new ModelAndView("Removeid");
                    break;
                
                case "removebook":
                    int id = Integer.valueOf(request.getParameter("bookid"));
                    if (bookDao.deleteBookById(id) == 1) {
                        mv = new ModelAndView("addBookSuccess");
                    } else {
                        mv = new ModelAndView("error");
                    }
                    break;
                    
                case "Overdue":
                    List<Book> overDueBooks = bookDao.wholeOverDue();
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("overDueBooks", overDueBooks);
                    mv = new ModelAndView("dueList", "model", model);
                    break;
                    
                case "Check":
                    String userName = user.getUsername();
                    List<Book> list = bookDao.getByUsername(userName);
                    boolean threeBook=false;
                    boolean overdue=false;
                    if(list.size()>1)
                        overdue=bookDao.overDue(list);
                    if(list.size()>=3)
                        threeBook=true;
                    if(threeBook || overdue){
                        mv = new ModelAndView("Nopermit");
                    }
                    else{
                        mv = new ModelAndView("CheckOut");
                    }
                    break;
                    
                case "checkout":
                    String u = user.getUsername();
                    String bookIsbn = request.getParameter("bookIsbn");
                    long bookId = Long.valueOf(request.getParameter("bookId"));
                    Book b=bookDao.getById(bookId);
                    if (b.getBorrow()==0 && bookDao.checkOutBookById(u,bookId,1) == 1) {
                        mv = new ModelAndView("checkOutSuccess");
                    } else {
                        mv = new ModelAndView("noExist");
                    }
                    break;
                    
                case "Return":
                    mv = new ModelAndView("Returninfo");
                    break;
                    
                case "returnbook":
                    String userReturn = user.getUsername();
                    long returnId = Long.valueOf(request.getParameter("bookId"));
                    Book returnBook=bookDao.getById(returnId);
                    if (returnBook.getUsername().equals(userReturn) && returnBook.getBorrow()==1 && bookDao.returnBookById(userReturn,returnId,0) == 1) {
                        mv = new ModelAndView("returnSuccess");
                    } else {
                        mv = new ModelAndView("error");
                    }
                    break;
                    
                case "All":
                    String userReturnAll = user.getUsername();
                    List<Book> allBooks = bookDao.getByUsername(userReturnAll);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("allBooks", allBooks);
                    mv = new ModelAndView("makeSure", "map", map);
                    break;
                    
                case "sure":
                    String userSure = user.getUsername();
                    List<Book> sureBooks = bookDao.getByUsername(userSure);
                    if (bookDao.returnAllBookById(userSure,sureBooks,0) == 1) {
                        mv = new ModelAndView("returnSuccess");
                    } else {
                        mv = new ModelAndView("error");
                    }
                    break;
                    
                
                default:
                    mv = new ModelAndView("ChooseRole");
            }
        }
        return mv;
    }
}
