/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author nick
 */
public class Validation {

    // method to check if a text field is empty or null
    public static boolean isEmptyField(String value) {
        // check if value is null or empty
        if (value == null || value.trim().equals("")) {
            return true; // field is empty (invalid)
        }
        return false; // field has value (valid)
    }

    // method to check if a double number is positive (greater than 0)
    public static boolean isPositiveNumber(double number) {
        if (number > 0) {
            return true; // positive number
        }
        return false; // zero or negative
    }

    // method to check if an integer is non-negative (0 or greater)
    public static boolean isPositiveInteger(int number) {
        if (number >= 0) {
            return true; // non-negative integer
        }
        return false; // negative integer
    }

    // method to validate year (should be reasonable, like between 1900 and 2030)
    public static boolean isValidYear(int year) {
        if (year >= 1900 && year <= 2030) {
            return true; // valid year range
        }
        return false; // year out of range
    }

}
