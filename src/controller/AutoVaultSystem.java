/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import model.User;
import model.Vehicle;

/**
 *
 * @author nick
 */
public class AutoVaultSystem {

    // Data structures to store all the data in memory
    private ArrayList<Vehicle> vehicleList; // stores active inventory
    private ArrayList<Vehicle> soldVehicleHistory; // stores deleted/sold items (Stack)
    private ArrayList<Vehicle> recentVehicleQueue; // stores last 5 added items (Queue)
    private LinkedList<User> userList; // stores registered users (using LinkedList)

    // constructor to create an instance of AutoVaultSystem
    public AutoVaultSystem() {
        // initialize all data structures
        vehicleList = new ArrayList<Vehicle>();
        soldVehicleHistory = new ArrayList<Vehicle>();
        recentVehicleQueue = new ArrayList<Vehicle>();
        userList = new LinkedList<User>();

        // load some dummy data to test the application
        initializeDummyData();
    }

    // method to add a new vehicle to the inventory
    public void addVehicle(Vehicle v) {
        // add to main inventory list
        vehicleList.add(v);

        // add to recent queue (keep only last 5 items)

        if (recentVehicleQueue.size() > 5) {
            recentVehicleQueue.remove(0); // remove oldest item
        } else {
            recentVehicleQueue.add(v); // add the item
        }

    }

    // method to delete a vehicle from inventory
    public void deleteVehicle(Vehicle v) {
        // remove from active inventory
        vehicleList.remove(v);

        // update status and add to sold history
        v.setStatus("Deleted");
        soldVehicleHistory.add(v);
    }

    // method to check if vehicle already exists (to prevent duplicates)
    public boolean checkDuplicate(String make, String model) {
        // loop through all vehicles in inventory
        for (Vehicle v : vehicleList) {
            // check if make and model match
            if (v.getMake().equals(make) && v.getModel().equals(model)) {
                return true; // duplicate found
            }
        }
        return false; // no duplicate found
    }

    // method to add some initial data for testing
    private void initializeDummyData() {
        // add 5 cars to meet milestone requirements
        Vehicle car1 = new Vehicle("Car", "Toyota", "Camry", 2022, 5, 25000.00);
        Vehicle car2 = new Vehicle("Car", "Honda", "Civic", 2023, 3, 22000.00);
        Vehicle car3 = new Vehicle("Car", "Ford", "Mustang", 2021, 2, 35000.00);
        Vehicle car4 = new Vehicle("Car", "BMW", "X5", 2023, 4, 55000.00);
        Vehicle car5 = new Vehicle("Car", "Tesla", "Model 3", 2024, 6, 45000.00);

        addVehicle(car1);
        addVehicle(car2);
        addVehicle(car3);
        addVehicle(car4);
        addVehicle(car5);

        // add 5 bikes to meet milestone requirements
        Vehicle bike1 = new Vehicle("Bike", "Yamaha", "R15", 2022, 10, 3000.00);
        Vehicle bike2 = new Vehicle("Bike", "Honda", "CBR", 2023, 7, 4500.00);
        Vehicle bike3 = new Vehicle("Bike", "Kawasaki", "Ninja", 2022, 5, 5000.00);
        Vehicle bike4 = new Vehicle("Bike", "Suzuki", "GSX", 2021, 8, 4000.00);
        Vehicle bike5 = new Vehicle("Bike", "Ducati", "Panigale", 2023, 3, 18000.00);

        addVehicle(bike1);
        addVehicle(bike2);
        addVehicle(bike3);
        addVehicle(bike4);
        addVehicle(bike5);

        // add a default admin user
        User admin = new User("admin", "auto-vault-2026", "Admin");
        userList.add(admin);
    }

    // Getters for all data structures
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public ArrayList<Vehicle> getSoldVehicleHistory() {
        return soldVehicleHistory;
    }

    public ArrayList<Vehicle> getRecentVehicleQueue() {
        return recentVehicleQueue;
    }

    public LinkedList<User> getUserList() {
        return userList;
    }

}
