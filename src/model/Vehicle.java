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

    // intial counter for generating unique vehicle IDs
    private static int carCounter = 1000;
    private static int bikeCounter = 2000;

    // creating private variables to restrict access.
    private String vehicleId; // Auto-generated ID (e.g., "C_1001", "B_2001")
    private String vehicleType; // e.g., "Car", "Bike" (Used to distinguish vehicle types)
    private String make;
    private String model;
    private int year;
    private int amount;
    private double price;
    private String status; // "Available", "Out of Stock" or "Deleted"

    // Constructor to initialize a Vehicle object.
    // Automatically generates vehicleId based on vehicle type and sets status based
    // on amount.
    public Vehicle(String vehicleType, String make, String model, int year, int amount, double price) {
        // Auto-generate Vehicle ID based on type
        if (vehicleType.equals("Car")) {
            carCounter = carCounter + 1;
            this.vehicleId = "C-" + (carCounter);
        } else if (vehicleType.equals("Bike")) {
            bikeCounter = bikeCounter + 1;
            this.vehicleId = "B-" + (bikeCounter);
        }

        this.vehicleType = vehicleType;
        this.make = make;
        this.model = model;
        this.year = year;
        this.amount = amount;
        this.price = price;

        // Auto-calculate status based on amount
        if (amount > 0) {
            this.status = "Available";
        } else {
            this.status = "Out of Stock";
        }
    }

    // Getters and Setters for each attribute
    // Gets the unique vehicle ID
    public String getVehicleId() {
        return vehicleId;
    }

    // Sets the vehicle ID
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        // Update status when amount changes
        if (this.amount > 0) {
            this.status = "Available";
        } else {
            this.status = "Out of Stock";
        }
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
