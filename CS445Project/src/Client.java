/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */

import java.util.*;

public class Client 
{
    String name;
    String email;
    String phone;
    double totalSpent;
    ArrayList<Booking> bookings;
    
    //Constructor
    public Client(String n, String e, String p) {
        name = n;
        email = e;
        phone = p;
        totalSpent = 0;
        bookings = new ArrayList<Booking>(0);
    }
    
    //Accessors
    String getName() {
        return name;
    }
    
    String getEmail() {
        return email;
    }
    
    String getPhone() {
        return phone;
    }
    
    double getTotalSpent() {
        return totalSpent;
    }
    
    ArrayList<Booking> getBookings() {
        return bookings;
    }
    
    //Mutators
    void setName(String n) {
        name = n;
    }
    
    void setEmail(String e) {
        email = e;
    }
    
    void setPhone(String p) {
        phone = p;
    }
    
    //Actions
    void makeBooking(Booking b) {
        
    }
    
    void cancelBooking(Tour t) {
        
    }
    
    void listBookings() {
        for (int i = bookings.size()-1; i > 0; i--) {
            //Print booking information
        }
    }
    
    boolean isEqual(Client c) {
        if (name.equals(c.name) && email.equals(c.email) && phone.equals(c.phone)) {
            return true;
        }
        return false;
    }
    
    String printClientShort() {
        String out = "Name: " + name + "\nEmail: " + email;
        return out;
    }
    
    String printClientFull() {
        String out = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nTotal Spent: " + totalSpent;
    }
}
