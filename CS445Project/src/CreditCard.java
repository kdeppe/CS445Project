/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kristen
 */
public class CreditCard 
{
    long cardNumber;
    int cardCode;
    String exp;
    String NameOnCard;
    String address;
    
    public CreditCard(long n, int c, String e, String noc, String a) {
        cardNumber = n;
        cardCode = c;
        exp = e;
        NameOnCard = noc;
        address = a;
    }
    
    long getNumber() {
        return cardNumber;
    }
    
    int getCode() {
        return cardCode;
    }
    
    String getExp() {
        return exp;
    }
    
    String getName() {
        return NameOnCard;
    }
    
    String getAddress() {
        return address;
    }
    
    void setNumber(long n) {
        cardNumber = n;
    }
    
    void setCode(int c) {
        cardCode = c;
    }
    
    void setName(String n) {
        NameOnCard = n;
    }
    
    void setExp(String e) {
        exp = e;
    }
    
    void setAddress(String a) {
        address = a;
    }
}
