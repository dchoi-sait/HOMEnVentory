/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author 775262
 */
public class Item implements Serializable {
    private int item_id;
    private int category;
    private String item_name;
    private double price;
    private String owner;

    public Item() {
    }

    public Item(int item_id, int cateory_id, String item_name, double price, String owner) {
        this.item_id = item_id;
        this.category = cateory_id;
        this.item_name = item_name;
        this.price = price;
        this.owner = owner;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category_id) {
        this.category = category_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    
    
}
