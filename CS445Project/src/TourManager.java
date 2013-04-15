/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */
import java.util.*;

public class TourManager 
{
    static ArrayList<Tour> TourList;
    static ArrayList<Client> ClientList;
    static ArrayList<Booking> BookingList;
    
    public static void main(String [] args) {        
 
    }
    
    static void addTour(double p, GregorianCalendar s, GregorianCalendar e, int cap) {
        Tour newTour = new Tour(p, s, e, cap);
        int i;
        boolean overlaps = false;
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).isOverlap(newTour)) {
                overlaps = true;
                break;
            }
        }
        if (overlaps) {
            System.out.println("Error: dates overlap with existing tour.");
        } else {
            TourList.add(newTour);
        }
    }
    
    static Client addClient(String n, String e, String p) {
        Client newClient = new Client(n, e, p);
        int i;
        boolean exists = false;
        for (i=0; i<ClientList.size(); i++) {
            if (ClientList.get(i).isEqual(newClient)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Client already exists");
            return ClientList.get(i);
        }
        return newClient;
    }
    
    static Booking addBooking(Tour t, Client c) {
        if (t.getRemaining() > 0) {
            Booking newBooking = new Booking(t, c);
            boolean exists = false;
            int i;
            if (BookingList.size() > 0) {
                for (i=0; i<BookingList.size(); i++) {
                    if (BookingList.get(i).isEqual(newBooking)) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    System.out.println("Booking error: this client has already booked this tour.");
                    return BookingList.get(i);
                } else {
                    t.makeBooking(newBooking);
                    c.makeBooking(newBooking);
                    BookingList.add(newBooking);
                    return newBooking;
                }
            } else {
                t.makeBooking(newBooking);
                c.makeBooking(newBooking);
                BookingList.add(newBooking);
                return newBooking;
            } 
        } 
        System.out.println("Booking error: this tour is already full.");          
        return null;
    }
    
    static void cancelBooking(Tour t, Client c) {
        Booking test = new Booking(t, c);
        for (int i=0; i<BookingList.size(); i++) {
            if (BookingList.get(i).isEqual(test)) {
                BookingList.remove(i);
            }
        }
        t.cancelBooking(c);
        c.cancelBooking(t);
        System.out.println("Refund amount: "+String.format("%.2f", t.getPrice()));
    }
    
    static void viewTours() {
        for (int i=0; i<TourList.size(); i++) {
            TourList.get(i).printTour();
        }
    }
    
    static void changeBooking(Booking b, Tour t) {
        double p = b.getTour().getPrice();
        b.getTour().cancelBooking(b.getClient());
        b.setTour(t);
        t.makeBooking(b);
        if (t.getPrice() > p) {
            System.out.println("Customer owes " + String.format("%.2f", t.getPrice() - p));
        } else {
            System.out.println("Refund amount: " + String.format("%.2f", p - t.getPrice()));
        }
        
    }
    
    static void displayPastBookings(GregorianCalendar start, GregorianCalendar end) {
        for (int i = 0; i < BookingList.size(); i++) {
            if (BookingList.get(i).getTour().getStart().getTimeInMillis() > start.getTimeInMillis() && BookingList.get(i).getTour().getStart().getTimeInMillis() < end.getTimeInMillis()) {
                System.out.println(BookingList.get(i).getClient().printClientShort());
                System.out.println(BookingList.get(i).getTour().printTour());
                System.out.println();
            }
        }
    }
    
    static void displayRevenue(GregorianCalendar start, GregorianCalendar end) {
        double total = 0;
        for (int i = 0; i < TourList.size(); i++) {
            total = total + TourList.get(i).getPrice()*TourList.get(i).getBookings().size();
        }
        System.out.println("Total revenue over selected time period: " + total);
    }
    
    static Client searchByEmail(String email) {
        for (int i=0; i<ClientList.size(); i++) {
            if (ClientList.get(i).getEmail().equals(email)) {
                return ClientList.get(i);
            }
        }
        return null;
    }

    static void cancelTour(Tour t) {
        System.out.println("Email list: ");
        int i;
        for (i=0; i<t.getBookings().size(); i++) {
            System.out.println(t.getBookings().get(i).getClient().getEmail());
        }
        for (i=0; i<BookingList.size(); i++) {
            
        }
    }
}
