
package com.neu.controller;

import com.neu.pojo.LibrarianLogin;
import com.neu.pojo.Login;
import com.neu.service.UserServiceImpl;
import com.neu.service.LibrarianServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

public class AuthenticationController extends AbstractController {

    public AuthenticationController() {
        
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        ModelAndView mv = null;
        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
        
        if (option.equals("registerSuccess")) {
            return new ModelAndView("ChooseRole");
        }

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserServiceImpl user = (UserServiceImpl) getApplicationContext().getBean("userDAO");//通過name獲取 Bean.
        LibrarianServiceImpl librarianUser = (LibrarianServiceImpl) getApplicationContext().getBean("librarianDAO");
        switch (option) {
            
            case "home":
                String choice = request.getParameter("choice");
                if (choice.equals("Librarian")) 
                {
                    mv = new ModelAndView("Librarian");
                } 
                else if (choice.equals("User")) 
                {
                    mv = new ModelAndView("User");
                }
                break;
                
            case "logout":
                session.invalidate();
                mv = new ModelAndView("ChooseRole");
                break;
                
            case "librarianRegister":

                if (userName.length() == 0 || password.length() == 0) {
                    mv = new ModelAndView("Librarian");
                    break;
                }
                if (librarianUser.getUsers(userName).size() != 0) {
                    mv = new ModelAndView("userExisted");
                    break;
                }
                int regiesterLibrarian = librarianUser.addUser(userName, password);
                if (regiesterLibrarian == 1) {
                    LibrarianLogin loggeduser = new LibrarianLogin(userName, password);
                    session.setAttribute("librarian", loggeduser);
                    mv = new ModelAndView("registerSuccess");
                } else {
                    mv = new ModelAndView("Librarian");
                }
                break;
                
            case "librarianLogin":
                LibrarianLogin librarian = librarianUser.authenticateLogin(userName, password);
                if(librarian == null) {
                    mv = new ModelAndView("Librarian");
                } 
                else {
                    session.setAttribute("librarian", librarian);
                    mv = new ModelAndView("library");
                }
                break;
                
            case "userRegister":
                if (userName.length() == 0 || password.length() == 0) {
                    mv = new ModelAndView("User");
                    break;
                }
                if (user.getUsers(userName).size() != 0) {
                    mv = new ModelAndView("userExisted");
                    break;
                }
                int regiesterUser = user.addUser(userName, password);
                if (regiesterUser == 1) {
                    Login loggeduser = new Login(userName, password);
                    session.setAttribute("user", loggeduser);
                    mv = new ModelAndView("registerSuccess");
                } else {
                    mv = new ModelAndView("User");
                }
                break;
                
            case "userLogin":
                Login human = user.authenticateLogin(userName, password);
                if(human == null) {
                    mv = new ModelAndView("User");
                } 
                else {
                    session.setAttribute("user", human);
                    mv = new ModelAndView("platform");
                }
                break;

            default:
                mv = new ModelAndView("ChooseRole");
        }
        return mv;
    }
}
