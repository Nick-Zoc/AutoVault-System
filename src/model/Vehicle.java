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
    private String make;
    private String model;
    private int amount;
    private double price;
    private String status;      // e.g., "Available", "Sold"
    private String vehicleType; // e.g., "Car", "Bike" (Used to distinguish vehicle types)

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

}
