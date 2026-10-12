package nerea.banconereamontoya.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nerea.banconereamontoya.modelo.Customer;

/**
 * Clases CustomerDAO, contiene los métodos necesarios para realizar operaciones contra las tablas CUSTOMER.
 * 
 * @author Nerea Montoya
 */

public class CustomerDAO {
    
    public static Connection getConnection(String url){
        Connection con = null;
        try{
            con = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println("SQL Exception: " + e.toString());
        } 
        return con;
    }
    
    public static Customer obtenerCustomerPorId(int custId, String url) throws SQLException {
        Customer customer = null;

        String sql = "SELECT CUST_ID FROM CUSTOMER WHERE CUST_ID = ?";

        try (Connection conn = getConnection(url);
                
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, custId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCust_id(rs.getInt("CUST_ID"));
                    
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("No se encontró el customer con el ID: " + ex.getMessage(), ex);
        }

        return customer;
    }
    
    public static ArrayList<Customer> idCustomer(String url, int custId) throws SQLException {

        ArrayList<Customer> customerList = new ArrayList<>();

        String sql = "SELECT CUST_ID, CITY FROM CUSTOMER WHERE CUST_ID = ?;";

        try (Connection conn = getConnection(url); 
                
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCust_id(rs.getInt("CUST_ID"));
                    customer.setCity(rs.getString("CITY"));
                    customerList.add(customer);
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("No se encontró el customer con el ID: " + ex.getMessage(), ex);
        }

        return customerList;
    }
}
