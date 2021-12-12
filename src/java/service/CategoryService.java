/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.CategoryDB;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Category;

/**
 *
 * @author 775262
 */
public class CategoryService {

    public List<Category> getAll() {
        try {
            return new CategoryDB().getAll();
        } catch (Exception ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Category get(int category_id) {
        try {
            return new CategoryDB().get(category_id);
        } catch (Exception ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void insert(String category) {
        try {
            new CategoryDB().insert(category);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Category category) {
        try {
            new CategoryDB().update(category);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Category category) {
        try {
            new CategoryDB().delete(category);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAttributes(HttpServletRequest request){
        List<Category> categories = getAll();
        request.setAttribute("categories", categories);
    }

}
