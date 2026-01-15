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

    // STATIC DATA STRUCTURES (for runtime persistence between panels)
    // Main inventory list using ArrayList - STATIC for shared data
    private static ArrayList<Vehicle> vehicleList = new ArrayList<>();

    // Users list using LinkedList - STATIC for shared data
    private static LinkedList<User> userList = new LinkedList<>();

    // Boolean to track if initial data has been loaded
    private static boolean dataInitialized = false;

    // STACK FOR HISTORY (using array) - STATIC for shared data
    private static final int STACK_MAX = 100;
    private static Vehicle[] historyStack = new Vehicle[STACK_MAX];
    private static int top = -1;

    // QUEUE FOR RECENTLY ADDED (using array) - STATIC for shared data
    private static final int QUEUE_SIZE = 5;
    private static Vehicle[] recentQueue = new Vehicle[QUEUE_SIZE];
    private static int front = -1;
    private static int rear = -1;

    // ARRAY-BASED QUEUE FOR PURCHASE HISTORY (FIFO)
    private static final int PURCHASE_QUEUE_SIZE = 100;
    private static Vehicle[] purchaseHistory = new Vehicle[PURCHASE_QUEUE_SIZE];
    private static int purchaseFront = -1;
    private static int purchaseRear = -1;

    // Constructor
    public AutoVaultSystem() {
        // Only initialize data once (first time any panel is created)
        if (!dataInitialized) {
            // Add initial dummy data
            prepareInitialData();
            dataInitialized = true;
        }
    }

    // PREPARE INITIAL DATA
    private void prepareInitialData() {

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

        // Add last 5 vehicles to recent queue
        enqueue(bike1);
        enqueue(bike2);
        enqueue(bike3);
        enqueue(bike4);
        enqueue(bike5);

        // Create admin user
        User admin = new User("admin", "admin-autovault-2026", "Admin");
        userList.add(admin);

        // Create regular user
        User user = new User("user", "user-autovault-2026", "User");
        userList.add(user);

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

    // LOGIN METHOD
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

    // GET USER ROLE
    public String getUserRole(String username) {
        // Loop through all users to find the role
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUsername().equals(username)) {
                return user.getRole(); // return role (Admin or User)
            }
        }
        return null; // user not found
    }

    // REGISTER NEW USER
    public boolean registerUser(String username, String password) {
        // Check if username already exists
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                return false; // username taken
            }
        }
        // Create new user with role "User"
        User newUser = new User(username, password, "User");
        userList.add(newUser);
        System.out.println("New user registered: " + username);
        return true;
    }

    // ADD VEHICLE
    public void addVehicle(Vehicle vehicle) {
        // Add to main list
        vehicleList.add(vehicle);

        // Add to recent queue
        enqueue(vehicle);

        System.out.println("Vehicle added: " + vehicle.getMake() + " " + vehicle.getModel());
    }

    // DELETE VEHICLE
    public void deleteVehicle(Vehicle vehicle) {
        // Remove from main list
        vehicleList.remove(vehicle);

        // Change status and push to history stack
        vehicle.setStatus("Deleted");
        push(vehicle);

        System.out.println("Vehicle deleted: " + vehicle.getMake() + " " + vehicle.getModel());
    }

    // FIND VEHICLE BY ID
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

    // CHECK FOR DUPLICATE
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

    // GETTERS
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public LinkedList<User> getUserList() {
        return userList;
    }

    // Selection Sort - sort vehicles by price
    // if ascending is true, sort low to high. if false, sort high to low
    public void sortVehicleListByPrice(boolean ascending) {
        int size = vehicleList.size();

        // loop through each position in the list
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            // find the minimum (or maximum) element in the remaining unsorted part
            for (int j = i + 1; j < size; j++) {
                double currentPrice = vehicleList.get(j).getPrice();
                double minPrice = vehicleList.get(minIndex).getPrice();

                if (ascending) {
                    // for ascending, find the smallest price
                    if (currentPrice < minPrice) {
                        minIndex = j;
                    }
                } else {
                    // for descending, find the largest price
                    if (currentPrice > minPrice) {
                        minIndex = j;
                    }
                }
            }

            // swap the found element with the element at position i
            if (minIndex != i) {
                Vehicle temp = vehicleList.get(i);
                vehicleList.set(i, vehicleList.get(minIndex));
                vehicleList.set(minIndex, temp);
            }
        }

        System.out.println("Sorted by price: " + (ascending ? "Low to High" : "High to Low"));
    }

    // Insertion Sort - sort vehicles by make (A-Z alphabetically)
    public void sortVehicleListByMake() {
        int size = vehicleList.size();

        // start from the second element (index 1)
        for (int i = 1; i < size; i++) {
            Vehicle currentVehicle = vehicleList.get(i);
            String currentMake = currentVehicle.getMake();
            int j = i - 1;

            // shift elements that are greater than currentMake to the right
            while (j >= 0 && vehicleList.get(j).getMake().compareToIgnoreCase(currentMake) > 0) {
                vehicleList.set(j + 1, vehicleList.get(j));
                j = j - 1;
            }

            // insert the current vehicle at the correct position
            vehicleList.set(j + 1, currentVehicle);
        }

        System.out.println("Sorted by make: A to Z");
    }

    // Selection Sort - sort vehicles by Vehicle ID (default order)
    public void sortVehicleListById() {
        int size = vehicleList.size();

        // selection sort algorithm
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            // find the minimum element in remaining unsorted array
            for (int j = i + 1; j < size; j++) {
                String id1 = vehicleList.get(j).getVehicleId();
                String id2 = vehicleList.get(minIndex).getVehicleId();

                // compare IDs alphabetically
                if (id1.compareTo(id2) < 0) {
                    minIndex = j;
                }
            }

            // swap the found minimum element with element at position i
            if (minIndex != i) {
                Vehicle temp = vehicleList.get(i);
                vehicleList.set(i, vehicleList.get(minIndex));
                vehicleList.set(minIndex, temp);
            }
        }

        System.out.println("Sorted by Vehicle ID");
    }

    // Insertion Sort - sort vehicles by Status (Available first, then Out of Stock)
    public void sortVehicleListByStatus() {
        int size = vehicleList.size();

        // insertion sort algorithm
        for (int i = 1; i < size; i++) {
            Vehicle current = vehicleList.get(i);
            String currentStatus = current.getStatus();
            int j = i - 1;

            // "Available" should come before "Out of Stock"
            // so we move "Out of Stock" items to the right
            while (j >= 0 && vehicleList.get(j).getStatus().compareTo(currentStatus) > 0) {
                vehicleList.set(j + 1, vehicleList.get(j));
                j = j - 1;
            }

            vehicleList.set(j + 1, current);
        }

        System.out.println("Sorted by Status: Available first");
    }

    // Binary Search - search for a vehicle by exact Vehicle ID
    // Uses binary search algorithm (list must be sorted by Vehicle ID first)
    // Returns the found vehicle or null if not found
    public static Vehicle performBinarySearchById(String vehicleId, java.util.List<Vehicle> inventory) {
        // if query is empty or null, return null
        if (vehicleId == null || vehicleId.isEmpty()) {
            return null;
        }

        String searchId = vehicleId.toLowerCase();

        // binary search on Vehicle ID
        int low = 0;
        int high = inventory.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Vehicle midVehicle = inventory.get(mid);
            String midId = midVehicle.getVehicleId().toLowerCase();

            // check for exact match on vehicle ID
            if (midId.equals(searchId)) {
                System.out.println("Binary search found by ID: " + midVehicle.getVehicleId());
                return midVehicle;
            }

            // compare vehicle IDs alphabetically
            if (searchId.compareTo(midId) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // vehicle not found via binary search
        System.out.println("Binary search did not find vehicle ID: " + vehicleId);
        return null;
    }

    // Linear Search - search for vehicles by vehicle ID, model, make, or price
    // Returns ALL matching vehicles as an ArrayList (not just one)
    // Uses binary search for exact Vehicle ID match first, then LINEAR SEARCH for
    // partial matches across all fields
    public static ArrayList<Vehicle> performLinearSearch(String query, ArrayList<Vehicle> inventory) {
        ArrayList<Vehicle> results = new ArrayList<>();

        // if query is empty or null, return empty list
        if (query == null || query.isEmpty()) {
            return results;
        }

        String searchQuery = query.toLowerCase();

        // first, try binary search for exact Vehicle ID match
        Vehicle exactMatch = performBinarySearchById(query, inventory);
        if (exactMatch != null) {
            results.add(exactMatch);
            // continue to also find partial matches
        }

        // LINEAR SEARCH to find ALL matching vehicles (partial matches)
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle v = inventory.get(i);

            // skip if already added via binary search (exact match)
            if (exactMatch != null && v.getVehicleId().equalsIgnoreCase(exactMatch.getVehicleId())) {
                continue;
            }

            // check if vehicle ID contains the search query (partial match)
            if (v.getVehicleId().toLowerCase().contains(searchQuery)) {
                results.add(v);
                continue; // move to next vehicle
            }

            // check if model contains the search query
            if (v.getModel().toLowerCase().contains(searchQuery)) {
                results.add(v);
                continue;
            }

            // check if make contains the search query
            if (v.getMake().toLowerCase().contains(searchQuery)) {
                results.add(v);
                continue;
            }

            // check if price matches the search query
            String priceStr = String.valueOf(v.getPrice());
            if (priceStr.contains(searchQuery)) {
                results.add(v);
            }
        }

        System.out.println("Linear search found " + results.size() + " vehicles for query: " + query);
        return results;
    }

    // PURCHASE HISTORY METHODS (using array-based FIFO Queue)
    // Add a purchased vehicle to the history (enqueue operation)
    public static void addToHistory(Vehicle vehicle) {
        // enqueue operation for purchase history
        if (purchaseRear == PURCHASE_QUEUE_SIZE - 1) {
            System.out.println("Purchase history queue is full");
            return;
        }
        if (purchaseFront == -1) {
            purchaseFront = 0;
        }
        purchaseRear++;
        purchaseHistory[purchaseRear] = vehicle;
        System.out.println("Added to purchase history: " + vehicle.getMake() + " " + vehicle.getModel());
    }

    // Get purchase history array
    public static Vehicle[] getPurchaseHistory() {
        return purchaseHistory;
    }

    // Get purchase front index
    public static int getPurchaseFront() {
        return purchaseFront;
    }

    // Get purchase rear index
    public static int getPurchaseRear() {
        return purchaseRear;
    }

    // Clear purchase history (reset queue)
    public static void clearPurchaseHistory() {
        for (int i = 0; i < PURCHASE_QUEUE_SIZE; i++) {
            purchaseHistory[i] = null;
        }
        purchaseFront = -1;
        purchaseRear = -1;
    }

}
