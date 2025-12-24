/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nick
 */
public class Vehicle {

    // creating private variables to restrict access.
    private String vehicleType; // e.g., "Car", "Bike" (Used to distinguish vehicle types)
    private String make;
    private String model;
    private int amount;
    private double price;
    private String status;      // e.g., "Available", "Sold"

    // constructor to initialize the variables
    public Vehicle(String vehicleId, String make, String model, int amount, double price, String status, String vehicleType) {
        this.make = make;
        this.model = model;
        this.amount = amount;
        this.price = price;
        // when amount of stock > 0 then vehicle is available.
        if (amount > 0) {
            this.status = "Available";
        } else {
            this.status = "Sold";
        }
        this.vehicleType = vehicleType;
    }

    // Getters and Setters for each attribute
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int year) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
