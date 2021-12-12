/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.ConnectionPool;
import dataaccess.DBUtil;
import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Category;
import model.Item;
import model.Role;
import model.User;

/**
 *
 * @author 775262
 */
public class AccountService {
     public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }
     
     public List<User> getAllUsers(){
         try {
             return new UserDB().getAll();
         } catch (Exception ex) {
             Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
     }
     
     public User getUser(String email){
         UserDB userDB = new UserDB();
         User user;
         try {
             user = userDB.get(email);
         } catch (Exception ex) {
             Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
         return user;
     }
     
     public void updateUser(User user){
         try {
             new UserDB().update(user);
         } catch (Exception ex) {
             Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void deleteUser(String email){
         try {
             new UserDB().delete(email);
         } catch (Exception ex) {
             Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public boolean insert(User user){
         try {
             new UserDB().insert(user);
             return true;
         } catch (Exception ex) {
             Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
     }
     
    public List<Role> getAllRoles(){
         try {
             return new RoleDB().getAll();
         } catch (Exception ex) {
             Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }  
    } 

    public int userRoleID(String email) {
        UserDB userDB = new UserDB();
        try {
            User user = userDB.get(email);
            return user.getRole();
        } catch (Exception e) {
            return -1;
        }
    }
    
    public void setAttributes(HttpServletRequest request) {
        List<User> users = getAllUsers();
        List<Role> roles = getAllRoles();
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
    }
    
}
