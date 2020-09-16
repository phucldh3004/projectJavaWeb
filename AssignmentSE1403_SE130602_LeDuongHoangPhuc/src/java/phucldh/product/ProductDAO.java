/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucldh.product;

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
public class ProductDAO implements Serializable {

    private List<ProductDTO> listProduct;

    public ProductDAO(List<ProductDTO> listProduct) {
        this.listProduct = listProduct;
    }

    public ProductDAO() {
    }

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductDTO> listProduct) {
        this.listProduct = listProduct;
    }

    public boolean getProduct()
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBAcess.makeConnection();
            if (conn != null) {
                String sql = "select ProductID , Tittle From Products ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                listProduct = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    ProductDTO dto = new ProductDTO(id, name);
                    listProduct.add(dto);

                }
                return true;
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

}
