/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import minhtda.registration.RegistrationDAO;
import minhtda.registration.RegistrationUpdateError;
import minhtda.utils.MyApplicationConstants;

/**
 *
 * @author minhd
 */
@WebServlet(name = "UpdatetServlet", urlPatterns = {"/UpdatetServlet"})
public class UpdatetServlet extends HttpServlet {
//       private final String ERROR_PAGE = "error.html";

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
        response.setContentType("text/html;charset=UTF-8");
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateFeature.ERROR_PAGE);
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String searchValue = request.getParameter("lastSearchValue");
        String roll = request.getParameter("chkAdmin");
        boolean isRoll = false;
        RegistrationUpdateError Error = new RegistrationUpdateError();
        boolean ErrorFound = false;
        try {
            HttpSession hs = request.getSession(false);
            if (hs != null) {
                System.out.println(password);

                if (password.trim().length() < 6 || password.trim().length() > 30) {
                    ErrorFound = true;
                    Error.setPasswordLengthError("Password is require typing from 6 to 30 characters");
                }
                if (ErrorFound) {
                    hs.setAttribute("PASSWORD", password);
                    hs.setAttribute("ERROR_SUPDATE", Error);
                    url = "DispatchController"
                            + "?btAction=Search"
                            + "&txtSearch=" + searchValue;
                    hs.setAttribute("USERNAME", username);
                } else {
                    //1.Call DAO
                    System.out.println("khong co loi");
                    RegistrationDAO dao = new RegistrationDAO();
                    if (roll != null) {
                        isRoll = true;
                    }
                    //2.Refresh data gird call the search value function using url rewriting technology
                    boolean result = dao.updateAccount(username, password, isRoll);
                    hs.removeAttribute("ERROR_SUPDATE");
                    hs.removeAttribute("USERNAME");
                    if (result) {
                        url = "DispatchController"
                                + "?btAction=Search"
                                + "&txtSearch=" + searchValue;
                    }
                }
            }

            //3. Delete action is succeded
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
