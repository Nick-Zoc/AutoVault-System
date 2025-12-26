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
 * Main Controller class for AutoVault System
 *
 * @author nick
 */
public class AutoVaultSystem {

    //ATA STRUCTURES
    // Main inventory list using ArrayList
    private ArrayList<Vehicle> vehicleList;

    // Users list using LinkedList
    private LinkedList<User> userList;

    //STACK FOR HISTORY (using array)
    private static final int STACK_MAX = 100;
    private Vehicle[] historyStack;
    private int top;

    //QUEUE FOR RECENTLY ADDED (using array)
    private static final int QUEUE_SIZE = 5;
    private Vehicle[] recentQueue;
    private int front;
    private int rear;

    // Constructor
    public AutoVaultSystem() {
        // Initialize ArrayList and LinkedList
        vehicleList = new ArrayList<Vehicle>();
        userList = new LinkedList<User>();

        // Initialize Stack
        historyStack = new Vehicle[STACK_MAX];
        top = -1;

        // Initialize Queue
        recentQueue = new Vehicle[QUEUE_SIZE];
        front = -1;
        rear = -1;

        // Add initial dummy data
        prepareInitialData();
    }

    // ============== PREPARE INITIAL DATA ==============
    private void prepareInitialData() {
        // Create 5 car objects
        Vehicle car1 = new Vehicle("Car", "Toyota", "Camry", 2022, 5, 25000.00);
        Vehicle car2 = new Vehicle("Car", "Honda", "Civic", 2023, 3, 22000.00);
        Vehicle car3 = new Vehicle("Car", "Ford", "Mustang", 2021, 2, 35000.00);
        Vehicle car4 = new Vehicle("Car", "BMW", "X5", 2023, 4, 55000.00);
        Vehicle car5 = new Vehicle("Car", "Tesla", "Model 3", 2024, 6, 45000.00);

        // Add cars to the ArrayList
        vehicleList.add(car1);
        vehicleList.add(car2);
        vehicleList.add(car3);
        vehicleList.add(car4);
        vehicleList.add(car5);

        // Create 5 bike objects
        Vehicle bike1 = new Vehicle("Bike", "Yamaha", "R15", 2022, 10, 3000.00);
        Vehicle bike2 = new Vehicle("Bike", "Honda", "CBR", 2023, 7, 4500.00);
        Vehicle bike3 = new Vehicle("Bike", "Kawasaki", "Ninja", 2022, 5, 5000.00);
        Vehicle bike4 = new Vehicle("Bike", "Suzuki", "GSX", 2021, 8, 4000.00);
        Vehicle bike5 = new Vehicle("Bike", "Ducati", "Panigale", 2023, 3, 18000.00);

        // Add bikes to the ArrayList
        vehicleList.add(bike1);
        vehicleList.add(bike2);
        vehicleList.add(bike3);
        vehicleList.add(bike4);
        vehicleList.add(bike5);

        // Add last 5 vehicles to recent queue
        enqueue(bike1);
        enqueue(bike2);
        enqueue(bike3);
        enqueue(bike4);
        enqueue(bike5);

        // Create admin user
        User admin = new User("admin", "auto-vault-2026", "Admin");
        userList.add(admin);

        System.out.println("Initial data added: " + vehicleList.size() + " vehicles");
    }

    // STACK OPERATIONS (for History)
    // Push vehicle to history stack
    public void push(Vehicle vehicle) {
        if (top == STACK_MAX - 1) {
            System.out.println("Stack is full - cannot add to history");
            return;
        }
        top++;
        historyStack[top] = vehicle;
        System.out.println("Pushed to history: " + vehicle.getMake() + " " + vehicle.getModel());
    }

    // Pop vehicle from history stack
    public Vehicle pop() {
        if (top == -1) {
            System.out.println("Stack is empty - no history");
            return null;
        }
        Vehicle vehicle = historyStack[top];
        historyStack[top] = null;
        top--;
        return vehicle;
    }

    // Get all items in stack (for loading to table)
    public Vehicle[] getHistoryStack() {
        return historyStack;
    }

    public int getStackTop() {
        return top;
    }

    // QUEUE OPERATIONS (for Recently Added)
    // Enqueue - add vehicle to recent queue
    public void enqueue(Vehicle vehicle) {
        if (rear == QUEUE_SIZE - 1) {
            // Queue is full, remove oldest (dequeue from front)
            for (int i = 0; i < QUEUE_SIZE - 1; i++) {
                recentQueue[i] = recentQueue[i + 1];
            }
            recentQueue[QUEUE_SIZE - 1] = vehicle;
            System.out.println("Queue full, shifted and added: " + vehicle.getMake());
        } else if (front == -1) {
            // Queue is empty
            front = 0;
            rear = 0;
            recentQueue[rear] = vehicle;
        } else {
            rear++;
            recentQueue[rear] = vehicle;
        }
    }

    // Get all items in queue (for loading to table)
    public Vehicle[] getRecentQueue() {
        return recentQueue;
    }

    public int getQueueFront() {
        return front;
    }

    public int getQueueRear() {
        return rear;
    }

    //LOGIN METHOD
    public boolean checkLogin(String username, String password) {
        // Loop through all users
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            // Check if username and password match
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // login success
            }
        }
        return false; // login failed
    }

    //ADD VEHICLE
    public void addVehicle(Vehicle vehicle) {
        // Add to main list
        vehicleList.add(vehicle);

        // Add to recent queue
        enqueue(vehicle);

        System.out.println("Vehicle added: " + vehicle.getMake() + " " + vehicle.getModel());
    }

    // ============== DELETE VEHICLE ==============
    public void deleteVehicle(Vehicle vehicle) {
        // Remove from main list
        vehicleList.remove(vehicle);

        // Change status and push to history stack
        vehicle.setStatus("Deleted");
        push(vehicle);

        System.out.println("Vehicle deleted: " + vehicle.getMake() + " " + vehicle.getModel());
    }

    // ============== FIND VEHICLE BY ID ==============
    public Vehicle findVehicleById(String vehicleId) {
        // Loop through all vehicles
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle v = vehicleList.get(i);
            if (v.getVehicleId().equals(vehicleId)) {
                return v; // found
            }
        }
        return null; // not found
    }

    // ============== CHECK FOR DUPLICATE ==============
    public boolean checkDuplicate(String make, String model) {
        // Loop through all vehicles
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle v = vehicleList.get(i);
            // Check if make and model match
            if (v.getMake().equals(make) && v.getModel().equals(model)) {
                return true; // duplicate found
            }
        }
        return false; // no duplicate
    }

    // ============== GETTERS ==============
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public LinkedList<User> getUserList() {
        return userList;
    }

}
