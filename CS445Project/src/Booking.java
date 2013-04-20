/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */
import java.io.*;

public class Booking implements Serializable
{
    Tour tour;
    Client client;
    double pricePaid;
    
    //Constructor
    public Booking(Tour t, Client c) {
        tour = t;
        client = c;
        pricePaid = t.getPrice();
    }
    
    //Accessors
    Tour getTour() {
        return tour;
    }
    
    Client getClient() {
        return client;
    }
    
    double getPrice() {
        return pricePaid;
    }
    
    //Mutators
    void setTour(Tour t) {
        tour = t;
    }
    
    void setClient(Client c) {
        client = c;
    }
    
    //Actions
    boolean isEqual(Booking b) {
        if (client.isEqual(b.getClient()) && tour.isEqual(b.getTour())) {
            return true;
        }
        return false;
    }
}
