/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phucldh.Registration.RegistrationCreateError;
import phucldh.Registration.RegistrationDAO;
import phucldh.Registration.RegistrationDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String insertErrorPage = "createNewAccount.jsp";
    private final String loginPage = "login.html";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        String url = insertErrorPage;
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean bErr = false;
        try {
            //1. check validation of user's errors 
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                bErr = true;
                errors.setUsernameLengthErr("username length requires 6-30 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                bErr = true;
                errors.setPasswordLengthErr("password length requires 6-30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                bErr = true;
                errors.setConfirmNotMatch("confirm must match password ");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                bErr = true;
                errors.setFullnameLengthErr("full name length requires 2-50 chars");

            }
            //2.process
            if (bErr) {
                ///cached erorr to forward to show page
                request.setAttribute("INSERTER", errors);
            } else {
                //call DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, bErr);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.insertRecord(dto);

                if (result) {
                    url = loginPage;
                }
            }

        } catch (NamingException ex) {
            log("CreateNewAccountServlet -Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet -SQL: " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed!!!");
                request.setAttribute("INSERTER", errors);
            }
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
