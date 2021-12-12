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
import model.Item;

public class ItemDB {

    public List<Item> getAll(String email) throws Exception {
        List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM item WHERE owner=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt(1);
                int categoryID = rs.getInt(2);
                String itemName = rs.getString(3);
                double price = rs.getDouble(4);
                String owner = rs.getString(5);
                Item item = new Item(itemID, categoryID, itemName, price, owner);
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return items;
    }

    public Item get(int itemID) throws Exception {
        Item item = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM item WHERE item_id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, itemID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt(2);
                String itemName = rs.getString(3);
                double price = rs.getDouble(4);
                String owner = rs.getString(5);
                item = new Item(itemID, categoryID, itemName, price, owner);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return item;
    }

    public void insert(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO item(category, item_name, price, owner) VALUES (?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getItem_name());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

    }

    public void update(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE item SET category=?, item_name=?, price=?, owner=? WHERE item_id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getItem_name());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner());
            ps.setInt(5, item.getItem_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(int itemID) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM item WHERE item_id= ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, itemID);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
