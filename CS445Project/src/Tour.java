/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */

import java.util.*;

public class Tour 
{
    double price;
    GregorianCalendar start;
    GregorianCalendar end;
    int capacity;
    ArrayList<Booking> bookings;
    
    //Constructor
    public Tour(double p, GregorianCalendar st, GregorianCalendar e, int cap)
    {
        price = p;
        start = st;
        end = e;
        capacity = cap;
        bookings = new ArrayList<Booking>(0);
    }
    //Accessors
    double getPrice() {
        return price;
    }
    
    GregorianCalendar getStart() {
        return start;
    }
    
    GregorianCalendar getEnd() {
        return end;
    }
    
    int getCapacity() {
        return capacity;
    }
    
    ArrayList<Booking> getBookings() {
        return bookings;
    }
    
    int getRemaining() {
        return capacity-bookings.size();
    }
    //Mutators
    
    void setPrice(double p) {
        price = p;
    }
    
    void setStart(GregorianCalendar s) {
        start = s;
    }
    
    void setEnd(GregorianCalendar e) {
        end = e;
    }
    
    void setCapacity(int cap) {
        capacity = cap;
    }
    
    //Actions
    void cancelTour() {
        
    }
    
    void setPrice(int price) {
        
    }
    
    void makeBooking(Booking b) {
        
    }
    
    void cancelBooking(Booking b) {
        
    }
    
    void listEmails() {
        
    }
}
