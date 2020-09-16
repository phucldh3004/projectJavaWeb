/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import phucldh.product.ProductDTO;
import phucldh.utils.DBAcess;

/**
 *
 * @author DELL
 */
public class CartDAO implements Serializable {

    public int getId() throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "SELECT Max(OrderID) as LastID FROM Orders";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if(rs.next()){
                    id = rs.getInt("LastID");
                    System.out.println("true");
                }
                
                
                return id;
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
        return 0;
    }
    
    public boolean insertCart(int id, String productName, int quantity)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO OrderDetail(OrderID, ProductName, Quantity) VALUES(?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2,productName );
                stm.setInt(3, quantity);
                int r = stm.executeUpdate();
                if (r > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
     public boolean insertCartID(int id, int quantity)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO Orders(OrderID,Total) VALUES(?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setInt(2, quantity);
                int r = stm.executeUpdate();
                if (r > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
