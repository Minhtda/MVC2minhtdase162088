/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtda.cart.CartObject;
import minhtda.product.ProductDTO;
import minhtda.utils.MyApplicationConstants;

/**
 *
 * @author minhd
 */
@WebServlet(name = "AddtocartServlet", urlPatterns = {"/AddtocartServlet"})
public class AddtocartServlet extends HttpServlet {

    //private final String SHOPPING_PAGE = "cart.html";
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
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.AddToCartFeature.SHOPPING_PAGE);
        PrintWriter out = response.getWriter();
        try {
            //1. Cust goes to 
            HttpSession session = request.getSession();
            //2. Cust takes his/her cartl
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }//end cart has not existed
            //3. Cust chooses a specified item
            String name = request.getParameter("txtName");
            String sku = request.getParameter("txtSku");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            int quanity = 1;
            ProductDTO dto = new ProductDTO(sku, name, price, quanity);
            if (dto != null) {
                cart.addITemsToCart(dto);
                session.setAttribute("SESSION", session);
                session.setAttribute("CART", cart);
            }
            //4. Cust drop it down

            //lam cai text box de nhap so luong
            //5. Cust continuely goes shopping
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
