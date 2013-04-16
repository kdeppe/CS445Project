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
        System.out.println("--- Test Tour Create And Access ---");
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
        System.out.println("--- Test Client Create And Access ---");
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        boolean pass = false;
        if (testC.getName().equals("Bob Smith") && testC.getEmail().equals("bsmith@gmail.com") && testC.getPhone().equals("312-555-1234") && testC.getTotalSpent() == 0 && testC.getBookings().isEmpty()) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testCreateAndAccessBooking() {
        System.out.println("--- Test Booking Create And Access ---");
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
        System.out.println("--- Test Add Booking ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        boolean pass = false;
        if (testC.bookings.contains(testB)) {
            System.out.println("Main booking list Contains testB");
        }
        if (testT.bookings.contains(testB)) {
            System.out.println("Tour list contains testB");
        }
        if (testC.totalSpent == 85.0) {
            System.out.println("Total spent = 85.00");
        }
        if (testC.bookings.contains(testB) && testT.bookings.contains(testB) && testC.totalSpent == 85.0) {
            pass = true;
        }        
        assertTrue(pass);
    }
    
    @Test
    public void testCancelBooking() {
        System.out.println("--- Test Cancel Booking ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        TourManager.cancelBooking(testT, testC);
        
        boolean pass = false;
        if (!testC.bookings.contains(testB) && !testT.bookings.contains(testB) && testC.getTotalSpent() == 0.0) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testChangeBooking() {
        System.out.println("--- Test Change Booking ---");
        GregorianCalendar start1, end1, start2, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        start2 = new GregorianCalendar(2013, 5, 16, 9, 0);
        end2 = new GregorianCalendar(2013, 5, 16, 12, 0);
        Tour testT1 = new Tour(85.0, start1, end1, 10);
        Tour testT2 = new Tour(95.0, start2, end2, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT1, testC);
        
        TourManager.changeBooking(testC.bookings.get(testC.bookings.size()-1),testT2);
        boolean pass = false;
        if (!testT1.bookings.contains(testB) && testT2.bookings.contains(testC.bookings.get(testC.bookings.size()-1)) && testB.tour.equals(testT2) && testC.getTotalSpent() == 95.0) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testSearchByEmail() {
        System.out.println("--- Test Search By Email ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = TourManager.addClient("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        Client searchC = TourManager.searchByEmail("bsmith@gmail.com");
        boolean pass = false;
        if (searchC.isEqual(testC)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testGetRemaining() {
        System.out.println("--- Test Get Remaining ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        boolean pass = false;
        if (testT.getRemaining() == 9) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testSetPrice() {
        System.out.println("--- Test Set Price ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour(85.0, start, end, 10);
        testtour.setPrice(90.0);
        boolean pass = false;
        if (testtour.getPrice() == 90) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testSetStartAndEnd() {
        System.out.println("--- Test Set Start And End ---");
        GregorianCalendar start1, start2, end1, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        start2 = new GregorianCalendar(2013, 5, 15, 9, 30);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        end2 = new GregorianCalendar(2013, 5, 15, 12, 30);
        Tour testtour = new Tour(85.0, start1, end1, 10);
        testtour.setStart(start2);
        testtour.setEnd(end2);
        boolean pass = false;
        if (testtour.getStart() == start2 && testtour.getEnd() == end2) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testSetCapacity() {
        System.out.println("--- Test Set Capacity ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour(85.0, start, end, 10);
        
        testtour.setCapacity(12);
        boolean pass = false;
        if (testtour.getCapacity() == 12) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testCancelTour() {
        System.out.println("--- Test Cancel Tour ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour(85.0, start, end, 10);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testtour, testC);
        
        TourManager.cancelTour(testtour);
        boolean pass = false;
        if (!TourManager.TourList.contains(testtour)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testFullTour() {
        System.out.println("--- Test Full Tour ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour(85.0, start, end, 1);
        
        Client testC1 = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Client testC2 = new Client("John Davis", "jdavis@hotmail.com", "630-555-4321");
        Booking testB1 = TourManager.addBooking(testT, testC1);
        Booking testB2 = TourManager.addBooking(testT, testC2);
        
        boolean pass = false;
        if (testB2 == null) {
            pass = true;
        }
        assertTrue(pass);
    }
}
