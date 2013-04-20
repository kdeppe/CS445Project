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
    
    //Constructor
    public Booking(Tour t, Client c) {
        tour = t;
        client = c;
    }
    
    //Accessors
    Tour getTour() {
        return tour;
    }
    
    Client getClient() {
        return client;
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
