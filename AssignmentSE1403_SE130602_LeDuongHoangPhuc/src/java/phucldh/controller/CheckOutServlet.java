/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phucldh.Registration.RegistrationDAO;
import phucldh.cart.CartDAO;
import phucldh.cart.CartObject;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String SUPERMARKET_PAGE = "SearchBook";

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
        String url = SUPERMARKET_PAGE;
        try {
            //1. user goes to cart's place
            HttpSession session = request.getSession();
            //2. lấy giỏ
            CartObject cart = (CartObject) session.getAttribute("CART");
            Map<String, Integer> items = cart.getItems();
            int id = 0;
            int quantity = 0;
            CartDAO dao = new CartDAO();
            id = dao.getId() + 1;
             for (String itemKey : items.keySet()) {
                if (cart != null) {
                    quantity = quantity+1;
                }
             }
            System.out.println(id);
            boolean insertID = dao.insertCartID(id,quantity);
     
            for (String itemKey : items.keySet()) {
                if (cart != null) {
                    //ghi xuong database

                    boolean result = dao.insertCart(id, itemKey, items.get(itemKey));
                    if (result) {
                        session.removeAttribute("CART");
                    }//end if remove attribute
                }//end if cart
            }
        } catch (NamingException ex) {
            log("CheckoutServlet _ Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckoutServlet _ SQL " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
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
