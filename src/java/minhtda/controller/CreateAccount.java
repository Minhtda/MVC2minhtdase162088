/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtda.registration.RegistrationCreateError;
import minhtda.registration.RegistrationDAO;
import minhtda.registration.RegistrationDTO;
import minhtda.utils.MyApplicationConstants;

/**
 *
 * @author minhd
 */
@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {
//    private final String ERROR_PAGE = "CreateAccount.jsp";
//    private final String LOGIN_PAGE = "login.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeature.ERROR_PAGE);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtName");
        boolean errorFound = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
            //1. check user's errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errorFound = true;
                errors.setUsernameLengthError("Username is require typing from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errorFound = true;
                errors.setPasswordLengthError("Password is require typing from 6 to 30 characters");
            } else {
                if (!confirm.trim().equals(password.trim())) {
                    errorFound = true;
                    errors.setConfirmNotMatched("Confirm not matched");
                }
            }

            if (fullname.trim().length() < 6 || fullname.trim().length() > 50) {
                errorFound = true;
                errors.setFullnameLengthError("Fullname is require typing from 6 to 50 characters");
                System.out.println("đã check lỗi fullname");
            }
            if (errorFound) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password,
                        fullname, false);
                boolean result = dao.createAccount(dto);
                //3. check all system's errors
                if (result) {
                    
                    url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeature.LOGIN_PAGE);
                }
            }
        } catch (SQLException ex) {
            String errorMsg = ex.getMessage();
            log("Create new Account SQL" + errorMsg);
            if (errorMsg.contains("duplicate")) {
                errors.setUsernameisExisted(username + "is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }

        } catch (NamingException ex) {
            log("Create New Account Naming" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
