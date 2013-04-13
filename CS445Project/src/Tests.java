/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */

import org.junit.runner.*;
import org.junit.runners.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class Tests 
{
    @Test
    public void testTourCreateAndAccess() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour(85.0, start, end, 10);
        boolean pass = false;
        if (testtour.getPrice() == 85 && testtour.getStart() == start && testtour.getEnd() == end && testtour.getCapacity() == 10 && testtour.bookings.isEmpty()) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testClientCreateAndAccess() {
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        boolean pass = false;
        if (testC.getName().equals("Bob Smith") && testC.getEmail().equals("bsmith@gmail.com") && testC.getPhone().equals("312-555-1234") && testC.getTotalSpent() == 0 && testC.getBookings().isEmpty()) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testCreateAndAccessBooking() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = new Booking(testT, testC);
        boolean pass = false;
        
        if (testB.getClient().equals(testC) && testB.getTour().equals(testT)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testAddBooking() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        boolean pass = false;
        if (testC.bookings.contains(testB) && testT.bookings.contains(testB) && testC.totalSpent == 85.0) {
            pass = true;
        }        
        assertTrue(pass);
    }
    
    @Test
    public void testCancelBooking() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        TourManager.cancelBooking(testT, testC);
        
        boolean pass = false;
        if (!testC.bookings.contains(testB) && !testT.bookings.contains(testB)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testChangeBooking() {
        GregorianCalendar start1, end1, start2, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        start2 = new GregorianCalendar(2013, 5, 16, 9, 0);
        end2 = new GregorianCalendar(2013, 5, 16, 12, 0);
        Tour testT1 = new Tour(85.0, start1, end1, 10);
        Tour testT2 = new Tour(85.0, start2, end2, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT1, testC);
        
        TourManager.changeBooking(testC.bookings.get(testC.bookings.size()-1),testT2);
        boolean pass = false;
        if (!testT1.bookings.contains(testB) && testT2.bookings.contains(testC.bookings.get(testC.bookings.size()-1)) && testB.tour.equals(testT2)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testSearchByEmail() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        Client searchC = TourManager.searchByEmail("bsmith@gmail.com");
        boolean pass = false;
        if (searchC.equals(testC)) {
            pass = true;
        }
        assertTrue(pass);
    }
}
