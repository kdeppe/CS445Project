/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */
public class Booking 
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
        if (client.equals(b.getClient()) && tour.equals(b.getTour())) {
            return true;
        }
        return false;
    }
}
