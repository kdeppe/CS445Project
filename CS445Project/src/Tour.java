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
import java.text.*;

public class Tour implements Serializable 
{
    String name;
    String description;
    String location;
    double price;
    GregorianCalendar start;
    GregorianCalendar end;
    int capacity;
    ArrayList<Booking> bookings;
    
    //Constructor
    public Tour(String n, String d, String l, double p, GregorianCalendar st, GregorianCalendar e, int cap)
    {
        name = n;
        description = d;
        location = l;
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
    
    String getName() {
        return name;
    }
    
    String getDescription() {
        return description;
    }
    
    String getLocation() {
        return location;
    }
    
    //Mutators
    
    void setName(String n) {
        name = n;
    }
    
    void setDescription(String d) {
        description = d;
    }
    
    void setLocation(String l) {
        location = l;
    }
    
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
    void setPrice(int p) {
        price = p;
    }
    
    void makeBooking(Booking b) {
        bookings.add(b);
    }
    
    void cancelBooking(Client c) {
        int i;
        for (i=0; i<bookings.size(); i++) {
            if (bookings.get(i).getClient().equals(c) ) {
                bookings.remove(i);
            }
        }
    }
    
    public String printTour() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String ampmStart, ampmEnd;
        if (start.get(GregorianCalendar.HOUR_OF_DAY) >= 12) {
            ampmStart = "PM";
        } else {
            ampmStart = "AM";
        }
        if (end.get(GregorianCalendar.HOUR_OF_DAY) >= 12) {
            ampmEnd = "PM";
        } else {
            ampmEnd = "AM";
        }
        String out;
        int startHour;
        int endHour;
        if (start.get(GregorianCalendar.HOUR) == 0) {
            startHour = 12;
        } else {
            startHour = start.get(GregorianCalendar.HOUR);
        }
        
        if (end.get(GregorianCalendar.HOUR) == 0) {
            endHour = 12;
        } else {
            endHour = end.get(GregorianCalendar.HOUR);
        }
        if (sdf.format(start.getTime()).equals(sdf.format(end.getTime()))) {
            out = "Tour Name: " + name + "\nLocation: " + location + "\nDescription: " 
                    + description + "\nDate: "+ sdf.format(start.getTime()) + "\nTimes: " 
                    + startHour + ":" + String.format("%02d",start.get(GregorianCalendar.MINUTE)) + " " + ampmStart +"-" 
                    + endHour + ":" + String.format("%02d", end.get(GregorianCalendar.MINUTE)) 
                    + " " + ampmEnd + "\nCapacity: " + capacity + "\nRemaining: " + this.getRemaining() 
                    + "\nPrice: " + String.format("$%.2f", price) + "\n";
        } else {
            out = "Tour Name: " + name + "\nLocation: " + location + "\nDescription: " 
                    + description + "\nDates: "+ sdf.format(start.getTime()) + "-" 
                    + sdf.format(end.getTime()) + "\nTimes: " + start.get(GregorianCalendar.HOUR) + ":" 
                    + String.format("%02d",start.get(GregorianCalendar.MINUTE)) + " " 
                    + ampmStart + "-" +end.get(GregorianCalendar.HOUR) + ":" 
                    + String.format("%02d", end.get(GregorianCalendar.MINUTE)) + " " 
                    + ampmEnd + "\nCapacity: " + capacity + "\nRemaining: " + this.getRemaining() 
                    + "\nPrice: " + String.format("$%.2f", price) + "\n";
        }
        return out;
    }
    
    boolean isOverlap(Tour t) {
        if ((start.getTimeInMillis() > t.getStart().getTimeInMillis() && start.getTimeInMillis() < t.getEnd().getTimeInMillis()) || (end.getTimeInMillis() > t.getStart().getTimeInMillis() && end.getTimeInMillis() < t.getEnd().getTimeInMillis())) {
            return true;
        }
        return false;
    }


    boolean isEqual(Tour t) {
        if (t.getStart() == start && t.getEnd() == end && t.getName().equals(name)) {
            return true;
        } 
        return false;
    }
}
