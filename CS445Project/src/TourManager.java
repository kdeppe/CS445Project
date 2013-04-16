/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */
import java.util.*;
import javax.swing.text.*;
import javax.swing.*;

public class TourManager 
{
    static ArrayList<Tour> TourList = new ArrayList<Tour>(0);
    static ArrayList<Client> ClientList = new ArrayList<Client>(0);
    static ArrayList<Booking> BookingList = new ArrayList<Booking>(0);
    
    static Client CurrentClient;
    static Tour CurrentTour;
    
    public static void main(String [] args) {        
        
    }
    
    static void addTour(String n, String d, double p, GregorianCalendar s, GregorianCalendar e, int cap) {
        Tour newTour = new Tour(n, d, p, s, e, cap);
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
            if (ClientList.get(i).getEmail().equals(newClient.getEmail())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Client email already exists, using existing record.");
            return ClientList.get(i);
        } else {
            ClientList.add(newClient);
            return ClientList.get(ClientList.size()-1);
        }
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
        System.out.println("Refund amount: "+String.format("$%.2f", t.getPrice()));
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
        b.getClient().addTotalSpent(t.getPrice() - p);

        if (t.getPrice() >= p) {
            System.out.println("Customer owes " + String.format("$%.2f", t.getPrice() - p));
        } else if (t.getPrice() < p) {
            System.out.println("Refund amount: " + String.format("$%.2f", p - t.getPrice()));
        }
    }
    
    static void displayPastBookings(GregorianCalendar start, GregorianCalendar end, JTextArea t) {
        for (int i = 0; i < BookingList.size(); i++) {
            if (BookingList.get(i).getTour().getStart().getTimeInMillis() > start.getTimeInMillis() && BookingList.get(i).getTour().getStart().getTimeInMillis() < end.getTimeInMillis()) {
                t.append(BookingList.get(i).getClient().printClientShort());
                t.append(BookingList.get(i).getTour().printTour());
                t.append("\n");
            }
        }
    }
    
    static String displayRevenue(GregorianCalendar start, GregorianCalendar end) {
        double total = 0;
        for (int i = 0; i < TourList.size(); i++) {
            total = total + TourList.get(i).getPrice()*TourList.get(i).getBookings().size();
        }
        String out = String.format("$%.2f", total);
        return out;
    }
    
    static Client searchByEmail(String email) {
        for (int i=0; i<ClientList.size(); i++) {
            if (ClientList.get(i).getEmail().equalsIgnoreCase(email)) {
                return ClientList.get(i);
            }
        }
        System.out.println("Email not found.");
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
    
    static void printAvailableTours(JTextArea t) {
        t.setText("");
        int i;
        GregorianCalendar present = new GregorianCalendar();
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).getStart().after(present) && TourList.get(i).getRemaining() > 0) {
                t.append("Tour #" + (i+1) + "\n");
                t.append(TourList.get(i).printTour());
            }
        }
    }
    
    static void printFutureTours(JTextArea t) {
        t.setText("");
        int i;
        GregorianCalendar present = new GregorianCalendar();
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).getStart().after(present)) {
                t.append("Tour #" + (i+1));
                t.append(TourList.get(i).printTour());
            }
        }
    }
    
    static void printAllTours(JTextArea t) {
        t.setText("");
        int i;
        for (i = 0; i < TourList.size(); i++) {
            t.append("Tour #" + (i+1));
            t.append(TourList.get(i).printTour());
        }
    }
}
