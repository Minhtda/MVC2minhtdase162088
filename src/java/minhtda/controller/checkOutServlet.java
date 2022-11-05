/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtda.cart.CartObject;
import minhtda.product.ProductDAO;
import minhtda.product.ProductDTO;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import minhtda.orderDetail.OrderDetailDAO;
import minhtda.orderDetail.OrderDetailDTO;
import mintda.order.OrderDAO;
import mintda.order.OrderDTO;

/**
 *
 * @author minhd
 */
@WebServlet(name = "checkOutServlet", urlPatterns = {"/checkOutServlet"})
public class checkOutServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession hs = request.getSession(false);
        hs.setAttribute("SESSION", hs);
        CartObject cart = (CartObject) hs.getAttribute("CART");
        System.out.println("da den servlet");
        float total = 0;
        String url = "invalid.html";
        try {

            if (hs != null) {
                if (cart != null) {
                    Map<String, ProductDTO> lOrder = cart.getItems();
                    if (lOrder != null) {
                        for (String sku : lOrder.keySet()) {
                            total += lOrder.get(sku).getPrice() * lOrder.get(sku).getQuanity();
                        }
                        String name = "Khách vãng lai";

                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = new Date();
                        java.sql.Date mySqlDate = new java.sql.Date(date.getTime());
                        OrderDAO dao = new OrderDAO();
                        OrderDTO dto = dao.addOrder(name, mySqlDate, total);
                        if (dto != null) {
                            System.out.println("xác nhận dto");
                            int orderID = 0;
                            orderID = dao.findOrderID(name, mySqlDate, total);
                            if (orderID != 0) {
                                System.out.println("xác nhận id");
                                OrderDetailDAO ddao = new OrderDetailDAO();
                                
                                for (String sku : lOrder.keySet()) {
                                    String id = lOrder.get(sku).getSku();
                                    int quanity = lOrder.get(sku).getQuanity();
                                    float price = lOrder.get(sku).getPrice();
                                    float totalOfSI = quanity * price;
                                    ddao.addOrderDetail(sku, orderID, quanity, price, totalOfSI);
                                    }
                                ddao.showOrderDetail(orderID);;
                                List<OrderDetailDTO> oDList = ddao.getOrderDetailList();
                                if (oDList != null) {
                                    hs.setAttribute("ORDER", dto);
                                    hs.setAttribute("ORDERDETAIL", oDList);
                                    url = "showOrder.jsp";
                                }

                            }

                        }

                    }

                }
            }

        } catch (SQLException ex) {
            ex.getMessage();
        } catch (NamingException ex) {
            ex.getMessage();
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
