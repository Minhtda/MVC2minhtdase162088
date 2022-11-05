/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtda.utils.MyApplicationConstants;

/**
 *
 * @author minhd
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
    //private final String LOGIN_PAGE = "login.html";
    //private final String LOGIN_PAGE = "";
    //private final String LOGIN_CONTROLLER = "LoginServlet"; 
    //private final String SEARCH_LASTNAME_PAGE = "SearchLastNameServlet";
    //private final String DELETE_PAGE = "DeleteServlet";
    //private final String FIRST_TIME_REQUEST = "FirstTimeServlet";
    //private final String UPDATE_PAGE = "UpdatetServlet";    
    //private final String LOG_OUT = "LogoutServlet";
    //private final String ADD_TO_CART = "AddtocartServlet";
    //private final String VIEW_CART_PAGE = "viewcart.jsp";
    //private final String REMOVE_CART_PAGE = "RemovecartServlet";
    //private final String SIGN_UP_PAGE = "CreateAccount";
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
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");

        //Which button did user click/trigger
        String button = request.getParameter("btAction");
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        try {
            if(button == null){
                //url = FIRST_TIME_REQUEST;
            }else if(button.equals("Go to shopping page")){
                url = "showServlet";
            }
            else if (button.equals("Login")){
                System.out.println("di den login");
                //url = LOGIN_CONTROLLER;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")){
                //url = SEARCH_LASTNAME_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SEARCH_LASTNAME_PAGE);
            } else if (button.equals("delete")){
                //url = DELETE_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.DELETE_PAGE);
            } else if (button.equals("Logout")){
                //url = LOG_OUT;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOG_OUT);
            } else if (button.equals("update")){
                //url = UPDATE_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.UPDATE_PAGE);
            } else if (button.equals("Add book to your cart")){
                //url = ADD_TO_CART;
                url = "AddtocartServlet";
            } else if (button.equals("View your cart")){
                //url = VIEW_CART_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.VIEW_CART_PAGE);
            }else if (button.equals("Remove Selected Items")){
                //url = REMOVE_CART_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.REMOVE_CART_PAGE);
            }
            else if (button.equals("Create New Account")){
                //url = SIGN_UP_PAGE;
                System.out.println("đã đến dispatch");
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SIGN_UP_PAGE);
            } 
            else if (button.equals("Check out")){
                //url = SIGN_UP_PAGE;
                url = "checkOutServlet";
            } 
        }finally{
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
