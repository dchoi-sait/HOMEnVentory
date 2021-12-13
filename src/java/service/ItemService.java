/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Category;
import model.Item;

/**
 *
 * @author 775262
 */
public class ItemService {

    public List<Item> getAll(String email) {
        try {
            return new ItemDB().getAll(email);
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Category> getAll() {
        try {
            return new CategoryDB().getAll();
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Item itemGet(int item_id) {
        try {
            return new ItemDB().get(item_id);
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addItem(Item item) {
        ItemDB idb = new ItemDB();
        try {
            idb.insert(item);
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editItem(Item item, String email) {
        try {
            if (email.equals(item.getOwner())) {
                new ItemDB().update(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int itemID, String email) {
        ItemDB idb = new ItemDB();
        try {
            Item item = idb.get(itemID);
            if (item.getOwner().equals(email)) {
                idb.delete(itemID);
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAllFromEmail(String email) {
        ItemDB idb = new ItemDB();
        if (email != null) {
            try {
                idb.deleteAllFromEmail(email);
            } catch (Exception ex) {
                Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setAttributes(HttpServletRequest request, String email) {
        List<Item> items = getAll(email);
        List<Category> categories = getAll();
        request.setAttribute("items", items);
        request.setAttribute("categories", categories);
    }
}
