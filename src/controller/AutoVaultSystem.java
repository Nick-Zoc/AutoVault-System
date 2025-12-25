/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.User;
import model.Vehicle;

/**
 *
 * @author nick
 */
public class AutoVaultSystem {

    // Singleton instance variable
    private static AutoVaultSystem instance = null;

    // Data structures to store all the data in memory
    private ArrayList<Vehicle> vehicleList; // stores active inventory
    private ArrayList<Vehicle> soldVehicleHistory; // stores deleted/sold items (Stack)
    private ArrayList<Vehicle> recentVehicleQueue; // stores last 5 added items (Queue)
    private ArrayList<User> userList; // stores registered users

    // private constructor to prevent creating multiple instances
    private AutoVaultSystem() {
        // initialize all ArrayLists
        vehicleList = new ArrayList<Vehicle>();
        soldVehicleHistory = new ArrayList<Vehicle>();
        recentVehicleQueue = new ArrayList<Vehicle>();
        userList = new ArrayList<User>();

        // load some dummy data to test the application
        initializeDummyData();
    }

    // method to get the single instance of AutoVaultSystem
    public static AutoVaultSystem getInstance() {
        if (instance == null) {
            instance = new AutoVaultSystem();
        }
        return instance;
    }

    // method to add a new vehicle to the inventory
    public void addVehicle(Vehicle v) {
        // add to main inventory list
        vehicleList.add(v);

        // add to recent queue (keep only last 5 items)
        recentVehicleQueue.add(v);
        if (recentVehicleQueue.size() > 5) {
            recentVehicleQueue.remove(0); // remove oldest item
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
        for (int i = 0; i < vehicleList.size(); i = i + 1) {
            Vehicle v = vehicleList.get(i);
            // check if make and model match
            if (v.getMake().equals(make) && v.getModel().equals(model)) {
                return true; // duplicate found
            }
        }
        return false; // no duplicate found
    }

    // method to add some initial data for testing
    private void initializeDummyData() {
        // add 3 cars
        Vehicle car1 = new Vehicle("Car", "Toyota", "Camry", 2022, 5, 25000.00);
        Vehicle car2 = new Vehicle("Car", "Honda", "Civic", 2023, 3, 22000.00);
        Vehicle car3 = new Vehicle("Car", "Ford", "Mustang", 2021, 2, 35000.00);

        addVehicle(car1);
        addVehicle(car2);
        addVehicle(car3);

        // add 2 bikes
        Vehicle bike1 = new Vehicle("Bike", "Yamaha", "R15", 2022, 10, 3000.00);
        Vehicle bike2 = new Vehicle("Bike", "Honda", "CBR", 2023, 7, 4500.00);

        addVehicle(bike1);
        addVehicle(bike2);

        // add a default admin user
        User admin = new User("admin", "admin123", "Admin");
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

    public ArrayList<User> getUserList() {
        return userList;
    }

}
