/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.Registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phucldh.utils.DBAcess;

/**
 *
 * @author DELL
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "SELECT username FROM Registration WHERE username = ? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    System.out.println("true");
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public String selectFullname(String username)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String fullname = null;

        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "SELECT fullname FROM Registration WHERE username = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    fullname = rs.getString("fullname");
                    System.out.println("true");

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return fullname;
    }
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchFullname(String searchValue)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {

                String sql = "Select username, password, fullname, isAdmin"
                        + " From Registration"
                        + " Where fullname Like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();

                    }
                    this.accountList.add(dto);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
    
    public boolean deleteAccount(String username)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "Delete From Registration Where username = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                
                int row = stm.executeUpdate();
                 if (row > 0) {
                    return true;
                }
               

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;

    }
     public boolean updatePassRole(String username, String password, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Open connection
            con = DBAcess.makeConnection();
            if (con != null) {
                //2.Create sql
                String sql = "Update Registration Set password = ?, isAdmin = ? Where username =?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4 eXCUTE QUERY    
                int row = stm.executeUpdate();
                //5.process
                if (row > 0) {
                    return true;
                }
            }//end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();

            }
        }
        return false;
    }
     public boolean insertRecord(RegistrationDTO dto)
             throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Open connection
            con = DBAcess.makeConnection();
            if (con != null) {
                //2.Create sql
                String sql = "Insert INTO Registration( username, password, fullname, isAdmin)"
                        + " Values(?, ?, ?, ?)";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setBoolean(4, dto.isRole());
                //4 eXCUTE QUERY    
                int row = stm.executeUpdate();
                //5.process
                if (row > 0) {
                    return true;
                }
            }//end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();

            }
        }
        return false;
         
     }
    
     
 
}
