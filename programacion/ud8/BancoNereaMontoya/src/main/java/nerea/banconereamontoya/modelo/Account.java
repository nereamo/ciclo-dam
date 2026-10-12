package nerea.banconereamontoya.modelo;

import java.util.Date;

/**
 * Clase Account, contiene los atributos para crear el objeto ACCOUNT.
 * 
 * @author Nerea Montoya
 */

public class Account {
    
    private int account_id;
    private double avail_balance;
    private Date open_date;
    private int cust_id;

    public Account() {

    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public double getAvail_balance() {
        return avail_balance;
    }

    public void setAvail_balance(double avail_balance) {
        this.avail_balance = avail_balance;
    }

    public Date getOpen_date() {
        return open_date;
    }

    public void setOpen_date(Date open_date) {
        this.open_date = open_date;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }
}
