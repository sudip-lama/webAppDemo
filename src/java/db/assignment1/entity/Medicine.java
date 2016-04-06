/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

/**
 *
 * @author maha
 */
public class Medicine {
    private int id;
    private String medicineName;
    private double price;
    
    public Medicine(){
    }
    public Medicine(String medicineName, double price){
        this.medicineName= medicineName;
        this.price= price;
    }
    
    public Medicine(int id, String medicineName, double price){
        this.id= id;
        this.medicineName= medicineName;
        this.price= price; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
