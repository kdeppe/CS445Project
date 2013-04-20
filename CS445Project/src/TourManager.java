/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.*;

public class TourManager 
{
    
    static ArrayList<Tour> TourList = new ArrayList<Tour>(0);
    static ArrayList<Client> ClientList = new ArrayList<Client>(0);
    static ArrayList<Booking> BookingList = new ArrayList<Booking>(0);
    
    static Client CurrentClient;
    static Tour CurrentTour;
    
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        boolean contains = false;
        try {
            FileInputStream fis= new FileInputStream("lists.ser");
            BufferedInputStream bis= new BufferedInputStream(fis);
            ObjectInputStream ois= new ObjectInputStream(bis);
            TourList = (ArrayList)ois.readObject();
            ClientList = (ArrayList)ois.readObject();
            BookingList = (ArrayList)ois.readObject();

            ois.close();
            contains = true;
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found Exception, initializing arrays");
            TourList = new ArrayList<Tour>(0);
            ClientList = new ArrayList<Client>(0);
            BookingList = new ArrayList<Booking>(0);
	} catch (ClassNotFoundException cnfe) {
            System.out.println("Class Not Found Exception, initializing arrays");
            TourList = new ArrayList<Tour>(0);
            ClientList = new ArrayList<Client>(0);
            BookingList = new ArrayList<Booking>(0);
        } catch (IOException ioe) {
            System.out.println("IO Exception, initializing arrays");
            TourList = new ArrayList<Tour>(0);
            ClientList = new ArrayList<Client>(0);
            BookingList = new ArrayList<Booking>(0);
        }
        if (!contains) {
            TourList = new ArrayList<Tour>(0);
            ClientList = new ArrayList<Client>(0);
            BookingList = new ArrayList<Booking>(0);
        }
        TourManagerGUI tmg = new TourManagerGUI();
        tmg.setVisible(true);
        tmg.setDefaultCloseOperation(TourManagerGUI.EXIT_ON_CLOSE);        
    }
    
    static String addTour(String n, String d, String l, double p, GregorianCalendar s, GregorianCalendar e, int cap, int mincap) {
        Tour newTour = new Tour(n, d, l, p, s, e, cap, mincap);
        int i;
        boolean overlaps = false;
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).isOverlap(newTour)) {
                overlaps = true;
                break;
            }
        }
        if (overlaps) {
            return ("Error: dates overlap with existing tour.");
        } else {
            TourList.add(newTour);
        }
        try {
            FileOutputStream fos = new FileOutputStream("lists.ser");
            BufferedOutputStream bos= new BufferedOutputStream(fos);
            ObjectOutputStream oos= new ObjectOutputStream(bos);

            oos.writeObject(TourList);
            oos.writeObject(ClientList);
            oos.writeObject(BookingList);
            oos.close();
        } catch (IOException ioe) {
        }
        return "Tour added successfully.\n";
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
            try {
                FileOutputStream fos = new FileOutputStream("lists.ser");
                BufferedOutputStream bos= new BufferedOutputStream(fos);
                ObjectOutputStream oos= new ObjectOutputStream(bos);

                oos.writeObject(TourList);
                oos.writeObject(ClientList);
                oos.writeObject(BookingList);
                oos.close();
            } catch (IOException ioe) {
            }
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
                    return null;
                } else {
                    t.makeBooking(newBooking);
                    c.makeBooking(newBooking);
                    BookingList.add(newBooking);
                    try {
                        FileOutputStream fos = new FileOutputStream("lists.ser");
                        BufferedOutputStream bos= new BufferedOutputStream(fos);
                        ObjectOutputStream oos= new ObjectOutputStream(bos);

                        oos.writeObject(TourList);
                        oos.writeObject(ClientList);
                        oos.writeObject(BookingList);
                        oos.close();
                    } catch (IOException ioe) {
                    }
                    return newBooking;
                }
            } else {
                t.makeBooking(newBooking);
                c.makeBooking(newBooking);
                BookingList.add(newBooking);
                try {
                    FileOutputStream fos = new FileOutputStream("lists.ser");
                    BufferedOutputStream bos= new BufferedOutputStream(fos);
                    ObjectOutputStream oos= new ObjectOutputStream(bos);

                    oos.writeObject(TourList);
                    oos.writeObject(ClientList);
                    oos.writeObject(BookingList);
                    oos.close();
                } catch (IOException ioe) {
                }
                return newBooking;
            } 
        }           
        return null;
    }
    
    static String cancelBooking(Tour t, Client c) {
        Booking test = new Booking(t, c);
        double paid = 0;
        for (int i=0; i<BookingList.size(); i++) {
            if (BookingList.get(i).isEqual(test)) {
                paid = BookingList.get(i).getPrice();
                BookingList.remove(i);
            }
        }
        t.cancelBooking(c);
        c.cancelBooking(t);
        String out = ("Refund amount: "+String.format("$%.2f", paid));
        try {
            FileOutputStream fos = new FileOutputStream("lists.ser");
            BufferedOutputStream bos= new BufferedOutputStream(fos);
            ObjectOutputStream oos= new ObjectOutputStream(bos);

            oos.writeObject(TourList);
            oos.writeObject(ClientList);
            oos.writeObject(BookingList);
            oos.close();
        } catch (IOException ioe) {
        }
        return out;
    }
    
    static String changeBooking(Booking b, Tour t) {
        double p = b.getTour().getPrice();
        b.getTour().cancelBooking(b.getClient());
        b.setTour(t);
        t.makeBooking(b);
        b.getClient().addTotalSpent(t.getPrice() - p);

        if (t.getPrice() >= p) {
            return ("Customer owes " + String.format("$%.2f", t.getPrice() - p));
        } else if (t.getPrice() < p) {
            return ("Refund amount: " + String.format("$%.2f", p - t.getPrice()));
        }
        try {
            FileOutputStream fos = new FileOutputStream("lists.ser");
            BufferedOutputStream bos= new BufferedOutputStream(fos);
            ObjectOutputStream oos= new ObjectOutputStream(bos);

            oos.writeObject(TourList);
            oos.writeObject(ClientList);
            oos.writeObject(BookingList);
            oos.close();
        } catch (IOException ioe) {
        }
        return "";
    }
    
    static String displayPastBookings(GregorianCalendar start, GregorianCalendar end) {
        String out = "Bookings from selected time period: \n";
        for (int i = 0; i < BookingList.size(); i++) {
            if (BookingList.get(i).getTour().getStart().after(start) && BookingList.get(i).getTour().getStart().before(end)) {
                out = out + (BookingList.get(i).getClient().printClientShort());
                out = out + (BookingList.get(i).getTour().printTourShort());
                out = out + ("\n");
            }
        }
        return out;
    }
    
    static String displayRevenue(GregorianCalendar start, GregorianCalendar end) {
        double total = 0;
        for (int i = 0; i < TourList.size(); i++) {
            if (TourList.get(i).getStart().after(start) && TourList.get(i).getStart().before(end)) {
                total = total + TourList.get(i).getPrice()*TourList.get(i).getBookings().size();
            }
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
        return null;
    }

    static String cancelTour(Tour t) {
        String out = "Email list: \n";
        int i, j;
        for (i=0; i<t.getBookings().size(); i++) {
            out = out + t.getBookings().get(i).getClient().getEmail() + "\n";
        }
        out = out + "\n";
        for (j = t.getBookings().size() - 1; j >= 0; j--) {
            out = out + t.getBookings().get(j).getClient().getName() + "\n";
            out = out + cancelBooking(t, t.getBookings().get(j).getClient()) + "\n\n";
        }
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).equals(t)) {
                TourList.remove(i);
            }
        }
        return out;
    }
    
    static String printAvailableTours() {
        String out = "";
        int i;
        GregorianCalendar present = new GregorianCalendar();
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).getStart().after(present) && TourList.get(i).getRemaining() > 0) {
                out = out + ("Tour #" + (i+1) + "\n");
                out = out + (TourList.get(i).printTour() + "\n");
            }
        }
        return out;
    }
    
    static String printFutureTours() {
        String out = "";
        int i;
        GregorianCalendar present = new GregorianCalendar();
        for (i=0; i<TourList.size(); i++) {
            if (TourList.get(i).getStart().after(present)) {
                out = out + ("Tour #" + (i+1) + "\n");
                out = out + (TourList.get(i).printTour() + "\n");
            }
        }
        return out;
    }
    
    static String printAllTours() {
        String out = "";
        int i;
        for (i = 0; i < TourList.size(); i++) {
            out = out + ("Tour #" + (i+1) + "\n");
            out = out + (TourList.get(i).printTour() + "\n");
        }
        return out;
    }
}
