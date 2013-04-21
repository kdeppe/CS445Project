/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kristen
 */
public class BookingTest {
    
    public BookingTest() {
    }
    
    @Test
    public void testTourCreateAndAccess() {
        System.out.println("\n--- Test Tour Create And Access ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        boolean pass = false;
        if (testtour.getName().equals("some name") && testtour.getDescription().equals("some description") && testtour.getLocation().equals("some location") && testtour.getPrice() == 85 && testtour.getStart() == start && testtour.getEnd() == end && testtour.getCapacity() == 10 && testtour.getMinCapacity() == 3 && testtour.bookings.isEmpty()) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testClientCreateAndAccess() {
        System.out.println("\n--- Test Client Create And Access ---");
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        boolean pass = false;
        if (testC.getName().equals("Bob Smith") && testC.getEmail().equals("bsmith@gmail.com") && testC.getPhone().equals("312-555-1234") && testC.getTotalSpent() == 0 && testC.getBookings().isEmpty()) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testClientIsEqual() {
        System.out.println("\n--- Test Client Is Equal ---");
        Client testC1 = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Client testC2 = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        
        assertTrue(testC1.isEqual(testC2));
    }
    
    @Test
    public void testClientPrintShort() {
        System.out.println("\n--- Test Client Print Short ---");
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        String out = testC.printClientShort();
        assertTrue(out.equals("Name: Bob Smith\nEmail: bsmith@gmail.com\n"));
    }
    
    @Test
    public void testClientIsNotEqual() {
        System.out.println("\n--- Test Client Is Not Equal ---");
        Client testC1 = new Client("Dan Smith", "dsmith@gmail.com", "312-555-1234");
        Client testC2 = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        
        assertTrue(!testC1.isEqual(testC2));
    }
    
    @Test
    public void testClientGetCard() {
        System.out.println("\n--- Test Client Get Card ---");
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        CreditCard card = new CreditCard(3928479827239843L, 473, "11/16", "Bob Dob", "123 Sesame St., Peggleville, PA, 09834");
        testC.setCard(card);
        assertTrue(testC.getCard().equals(card));
    }
    
    @Test
    public void testClientMutators() {
        Client c = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        c.setName("Dan Backslide");
        c.setEmail("DBS@gmail.com");
        c.setPhone("888-262-7123");
        assertTrue(c.getName().equals("Dan Backslide") && c.getEmail().equals("DBS@gmail.com") && c.getPhone().equals("888-262-7123"));
    }
    
    @Test
    public void testClientPrintFull() {
        System.out.println("\n--- Test Client Print Full ---");
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        CreditCard card = new CreditCard(3928479827239843L, 473, "11/16", "Bob Dob", "123 Sesame St., Peggleville, PA, 09834");
        testC.setCard(card);
        String out = testC.printClientFull();
        assertTrue(out.equals("Name: Bob Smith\nEmail: bsmith@gmail.com\nPhone: 312-555-1234\nTotal Spent: $0.00\n\nCard number: 3928479827239843\nSecurity code: 473\nExpiration: 11/16\nName on Card: Bob Dob\nBilling Address: 123 Sesame St., Peggleville, PA, 09834"));
    }
    
    @Test
    public void testCreditCardCreateAndAccess() {
        System.out.println("\n--- Test Credit Card Create And Access ---");
        CreditCard card = new CreditCard(3928479827239843L, 473, "11/16", "Bob Dob", "123 Sesame St., Peggleville, PA, 09834");
        boolean pass = false;
        if (card.getName().equals("Bob Dob") && card.getNumber() == 3928479827239843L && card.getCode() == 473 && card.getExp().equals("11/16") && card.getAddress().equals("123 Sesame St., Peggleville, PA, 09834")) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testCreditCardMutators() {
        System.out.println("\n--- Test Credit Card Mutators ---");
        CreditCard card = new CreditCard(2384792838743029L, 697, "05/17", "Dan Stan", "2319 Monster Ln., Monstropolis, MY, 19298");
        card.setNumber(3928479827239843L);
        card.setCode(473);
        card.setExp("11/16");
        card.setName("Bob Dob");
        card.setAddress("123 Sesame St., Peggleville, PA, 09834");
        boolean pass = false;
        if (card.getName().equals("Bob Dob") && card.getNumber() == 3928479827239843L && card.getCode() == 473 && card.getExp().equals("11/16") && card.getAddress().equals("123 Sesame St., Peggleville, PA, 09834")) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testCreateAndAccessBooking() {
        System.out.println("\n--- Test Booking Create And Access ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = new Booking(testT, testC);
        boolean pass = false;
        
        if (testB.getClient().equals(testC) && testB.getTour().equals(testT)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testAddTour() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        String out = TourManager.addTour("Tour","Description", "Location", 85.0, start, end, 10, 3);
        
        assertTrue(out.equals("Tour added successfully.\n") && TourManager.TourList.get(0).getName().equals("Tour"));
    }
    
    @Test
    public void testAddOverlapTour() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        TourManager.addTour("Tour","Description", "Location", 85.0, start, end, 10, 3);
        String out = TourManager.addTour("Tour","Description", "Location", 85.0, start, end, 10, 3);
        
        assertTrue(out.equals("Error: dates overlap with existing tour.") && TourManager.TourList.size() == 1);
    }
    
    @Test
    public void testAddClient() {
        TourManager.addClient("Bob", "bob@live.com", "555-0147");
        assertTrue(TourManager.ClientList.get(0).equals(TourManager.CurrentClient) && TourManager.CurrentClient.getName().equals("Bob"));
    }
    
    @Test
    public void testAddSameClientTwice() {
        TourManager.addClient("Bob", "bob@live.com", "555-0147");
        String out = TourManager.addClient("Bob", "bob@live.com", "555-0147");
        
        assertTrue(TourManager.ClientList.size() == 1 && out.equals("Client email already exists, using existing record."));
    }
    
    @Test
    public void testAddBooking() {
        System.out.println("\n--- Test Add Booking ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
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
    public void testSetClient() {
        System.out.println("\n--- Test Set Client ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = new Booking(testT, testC);
        Client newC = new Client("Dan Backslide", "db@gmail.com", "630-287-9927");
        testB.setClient(newC);
        
        boolean pass = false;
        if (testB.getClient().equals(newC)) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testCancelBooking() {
        System.out.println("\n--- Test Cancel Booking ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
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
    public void testCancelBookingNoRefund() {
        System.out.println("\n--- Test Cancel Booking No Refund ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT, testC);
        
        String out = TourManager.cancelBookingNoRefund(testT, testC);
        
        boolean pass = false;
        if (!testC.bookings.contains(testB) && !testT.bookings.contains(testB) && testC.getTotalSpent() == 85.0 && out.equals("Late cancellation: not eligible for refund.")) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testChangeBookingHigher() {
        System.out.println("\n--- Test Change Booking ---");
        GregorianCalendar start1, end1, start2, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        start2 = new GregorianCalendar(2013, 5, 16, 9, 0);
        end2 = new GregorianCalendar(2013, 5, 16, 12, 0);
        Tour testT1 = new Tour("some name", "some description", "some location", 85.0, start1, end1, 10, 3);
        Tour testT2 = new Tour("different name", "different description", "different location", 95.0, start2, end2, 10, 3);
        
        Client testC = new Client("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Booking testB = TourManager.addBooking(testT1, testC);
        
        TourManager.changeBooking(testC.bookings.get(testC.bookings.size()-1),testT2);
        boolean pass = false;
        if (!testT1.bookings.contains(testB) && testT2.bookings.contains(testC.bookings.get(testC.bookings.size()-1)) && testB.tour.equals(testT2) && testC.getTotalSpent() == 95.0) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    public void testChangeBookingLower() {
        System.out.println("\n--- Test Change Booking ---");
        GregorianCalendar start1, end1, start2, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        start2 = new GregorianCalendar(2013, 5, 16, 9, 0);
        end2 = new GregorianCalendar(2013, 5, 16, 12, 0);
        Tour testT1 = new Tour("some name", "some description", "some location", 95.0, start1, end1, 10, 3);
        Tour testT2 = new Tour("different name", "different description", "different location", 85.0, start2, end2, 10, 3);
        
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
        System.out.println("\n--- Test Search By Email ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
        TourManager.addClient("Bob Smith", "bsmith@gmail.com", "312-555-1234");
        Client testC = TourManager.CurrentClient;
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
        System.out.println("\n--- Test Get Remaining ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
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
        System.out.println("\n--- Test Set Price ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        testtour.setPrice(90.0);
        boolean pass = false;
        if (testtour.getPrice() == 90) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testSetStartAndEnd() {
        System.out.println("\n--- Test Set Start And End ---");
        GregorianCalendar start1, start2, end1, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        start2 = new GregorianCalendar(2013, 5, 15, 9, 30);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        end2 = new GregorianCalendar(2013, 5, 15, 12, 30);
        Tour testtour = new Tour("some name", "some description", "some location", 85.0, start1, end1, 10, 3);
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
        System.out.println("\n--- Test Set Capacity ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
        testtour.setCapacity(12);
        boolean pass = false;
        if (testtour.getCapacity() == 12) {
            pass = true;
        }
        assertTrue(pass);
    }
    
    @Test
    public void testTourIsOverlap() {
        GregorianCalendar start1, start2, end1, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        start2 = new GregorianCalendar(2013, 5, 15, 9, 30);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        end2 = new GregorianCalendar(2013, 5, 15, 12, 30);
        Tour t1 = new Tour("some name", "some description", "some location", 85.0, start1, end1, 10, 3);
        Tour t2 = new Tour("some name", "some description", "some location", 85.0, start2, end2, 10, 3);    
        assertTrue(t1.isOverlap(t2));
    }
    
    @Test
    public void testTourIsNotOverlap() {
        GregorianCalendar start1, start2, end1, end2;
        start1 = new GregorianCalendar(2013, 5, 15, 9, 0);
        start2 = new GregorianCalendar(2013, 5, 16, 9, 30);
        end1 = new GregorianCalendar(2013, 5, 15, 12, 0);
        end2 = new GregorianCalendar(2013, 5, 16, 12, 30);
        Tour t1 = new Tour("some name", "some description", "some location", 85.0, start1, end1, 10, 3);
        Tour t2 = new Tour("some name", "some description", "some location", 85.0, start2, end2, 10, 3);
        assertTrue(!t1.isOverlap(t2));
    }
    
    @Test
    public void testTourMutators() {
        System.out.println("\n--- Test Tour Mutators ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour t = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        t.setName("other name");
        t.setDescription("other description");
        t.setLocation("other location");
        t.setMinCapacity(4);
        t.setPrice(95.0);
        
        assertTrue(t.getName().equals("other name") && t.getDescription().equals("other description") && t.getLocation().equals("other location") && t.getMinCapacity() == 4 && t.getPrice() == 95.0);
    }
    
    @Test
    public void testPrintTourShort1() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 11, 0);
        Tour t = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        String out = t.printTourShort();
        
        assertTrue(out.equals("Tour Name: some name\nDate: 06/15/2013\nTimes: 9:00 AM-11:00 AM\nPrice: $85.00\n"));
    }
    
    @Test
    public void testPrintTourShort2() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 12, 0);
        end = new GregorianCalendar(2013, 5, 16, 12, 0);
        Tour t = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        String out = t.printTourShort();
        System.out.println(t.printTourShort());
        
        assertTrue(out.equals("Tour Name: some name\nDates: 06/15/2013-06/16/2013\nTimes: 12:00 PM-12:00 PM\nPrice: $85.00\n"));
    }
    
    @Test
    public void testPrintTour1() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 11, 0);
        Tour t = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        String out = t.printTour();
        
        assertTrue(out.equals("Tour Name: some name\nLocation: some location\nDescription: some description\nDate: 06/15/2013\nTimes: 9:00 AM-11:00 AM\nMaximum Capacity: 10\nMinimum capacity: 3\nRemaining: 10\nPrice: $85.00\n"));
    }
    
    @Test
    public void testPrintTour2() {
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 12, 0);
        end = new GregorianCalendar(2013, 5, 16, 12, 0);
        Tour t = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        String out = t.printTour();
        
        assertTrue(out.equals("Tour Name: some name\nLocation: some location\nDescription: some description\nDates: 06/15/2013-06/16/2013\nTimes: 12:00 PM-12:00 PM\nMaximum Capacity: 10\nMinimum capacity: 3\nRemaining: 10\nPrice: $85.00\n"));
    }
    
    @Test
    public void testCancelTour() {
        System.out.println("\n--- Test Cancel Tour ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testtour = new Tour("some name", "some description", "some location", 85.0, start, end, 10, 3);
        
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
        System.out.println("\n--- Test Full Tour ---");
        GregorianCalendar start, end;
        start = new GregorianCalendar(2013, 5, 15, 9, 0);
        end = new GregorianCalendar(2013, 5, 15, 12, 0);
        Tour testT = new Tour("some name", "some description", "some location", 85.0, start, end, 1, 3);
        
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