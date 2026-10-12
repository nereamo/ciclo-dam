package nerea.banconereamontoya.modelo;

/**
 * Clase Customer, contiene los atributos para crear el objeto CUSTOMER.
 * 
 * @author Nerea Montoya
 */

public class Customer {
    
    private int cust_id;
    private String city;

    public Customer() {
        
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
