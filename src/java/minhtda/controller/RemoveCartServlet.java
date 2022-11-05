/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minhtda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtda.cart.CartObject;
import minhtda.product.ProductDTO;

/**
 *
 * @author minhd
 */
@WebServlet(name="RemovecartServlet", urlPatterns={"/RemovecartServlet"})
public class RemoveCartServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null){
                    Map<String, ProductDTO> items = cart.getItems();
                    if(items != null){
                        String[] selectedItems = request.getParameterValues("checkbox");
                        if(selectedItems != null){
                            for(String item : selectedItems){
                                cart.removeItemFromCart(item);
                            }
                            session.setAttribute("CART", cart);
                        }
                    }
                }
            }
        }finally{
            // system refresh cart = call function viewcart using url rewriting
            String urlRewriting = "DispatchController"
                    + "?btAction=View Your Cart";
            response.sendRedirect(urlRewriting);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
