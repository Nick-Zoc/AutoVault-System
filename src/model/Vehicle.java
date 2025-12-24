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

    // creating private variables for access restriction
    private String carId;
    private String make;
    private String model;
    private int year;
    private double price;
    private String status;      // e.g., "Available", "Sold"
    private String vehicleType; // e.g., "Car", "Bike" (Used to distinguish types)

    // constructor to initialize the variables
    public Vehicle(String carId, String make, String model, int year, double price, String status, String vehicleType) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.status = status;
        this.vehicleType = vehicleType;
    }
}
