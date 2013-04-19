/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */

import java.util.*;
import java.io.*;

public class Client implements Serializable
{
    String name;
    String email;
    String phone;
    double totalSpent;
    ArrayList<Booking> bookings;
    CreditCard card;
    
    //Constructor
    public Client(String n, String e, String p) {
        name = n;
        email = e;
        phone = p;
        totalSpent = 0;
        bookings = new ArrayList<Booking>(0);
        card = null;
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
    
    CreditCard getCard() {
        return card;
    }
    
    //Mutators
    void setCard(CreditCard c) {
        card = c;
    }
    
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
        bookings.add(b);
        totalSpent = totalSpent + b.getTour().getPrice();
    }
    
    void cancelBooking(Tour t) {
        int i;
        for (i=0; i<bookings.size(); i++) {
            if (bookings.get(i).getTour().equals(t) ) {
                bookings.remove(i);
                totalSpent = totalSpent - t.getPrice();
            }
        }
    }
    
    void addTotalSpent(double i) {
        totalSpent += i;
    }
    
    void listBookings() {
        for (int i = bookings.size()-1; i > 0; i--) {
            //Print booking information
            System.out.println(bookings.get(i).getTour().printTour());
        }
    }
    
    boolean isEqual(Client c) {
        if (name.equals(c.name) && email.equals(c.email) && phone.equals(c.phone)) {
            return true;
        }
        return false;
    }
    
    String printClientShort() {
        String out = "Name: " + name + "\nEmail: " + email + "\n";
        return out;
    }
    
    String printClientFull() {
        String out = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone 
                + "\nTotal Spent: " + String.format("$%.2f", totalSpent) + "\n" 
                + card.printCard();
        return out;
    }
}
