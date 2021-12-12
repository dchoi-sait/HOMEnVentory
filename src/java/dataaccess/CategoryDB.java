/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;


/**
 *
 * @author 775262
 */
public class CategoryDB {
    
    public List<Category> getAll() throws Exception{
        List<Category> categories = new ArrayList<>();
         ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM category";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt(1);
                String name = rs.getString(2);
                Category category = new Category(categoryId, name);    
                categories.add(category);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return categories;
    } 

    public String get(int categoryID) throws Exception {
        String category = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select * from category WHERE category_ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoryID);
            rs = ps.executeQuery();
            if (rs.next()) {
                category = rs.getString(2); 
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return category;
    }
}
