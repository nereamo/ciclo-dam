package nerea.banconereamontoya.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nerea.banconereamontoya.modelo.Account;

/**
 * Clases AccountDAO, contiene los métodos necesarios para realizar operaciones contra las tablas ACCOUNT.
 * 
 * @author Nerea Montoya
 */

public class AccountDAO {
    
    public AccountDAO(){  
    }
    
    public static Connection getConnection(String url){
        Connection con = null;
        try{
            con = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println("SQL Exception: " + e.toString());
        } 
        return con;
    }

    public static ArrayList<Account> listaCuentas(String url) throws SQLException{
        
        ArrayList<Account> accountList = new ArrayList<>();
        
        String sql = "SELECT ACCOUNT_ID, OPEN_DATE, AVAIL_BALANCE FROM ACCOUNT ORDER BY OPEN_DATE ASC LIMIT 5";
        
        try(Connection conn = getConnection(url);
            PreparedStatement ps = conn.prepareStatement(sql)){
           
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Account account = new Account();
                account.setAccount_id(rs.getInt("ACCOUNT_ID"));
                account.setOpen_date(rs.getDate("OPEN_DATE"));
                account.setAvail_balance(rs.getDouble("AVAIL_BALANCE"));
                accountList.add(account);  
            }

        }catch (SQLException ex) {
            throw new SQLException("Error al obtener las cuentas: " + ex.getMessage(), ex);
        }

        return accountList;
    }
    
    public static ArrayList<Account> listaCuentasCustomerId(String url, int custId) throws SQLException {

        ArrayList<Account> accountList = new ArrayList<>();

        String sql = "SELECT ACCOUNT_ID, AVAIL_BALANCE FROM ACCOUNT WHERE CUST_ID = ?;";

        try (Connection conn = getConnection(url); 
                
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account();
                    account.setAccount_id(rs.getInt("ACCOUNT_ID"));
                    account.setAvail_balance(rs.getDouble("AVAIL_BALANCE"));
                    accountList.add(account);
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al obtener la cuenta por ID: " + ex.getMessage(), ex);
        }

        return accountList;
    }
    
    public static double saldoTotalCustomerId(String url, int custId) throws SQLException {
        double totalBalance = 0.0;

        String sql = "SELECT SUM(AVAIL_BALANCE) AS TOTAL_BALANCE FROM ACCOUNT WHERE CUST_ID = ?;";

        try (Connection conn = getConnection(url); 
                 
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalBalance = rs.getDouble("TOTAL_BALANCE");
                }

                if (rs.wasNull()) {
                    totalBalance = 0.0;
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al obtener el saldo total: " + ex.getMessage(), ex);
        }

        totalBalance = Math.round(totalBalance * 100.0) / 100.0;
        return totalBalance;
    }
    
    public static void modificarSaldoCuenta(int accountId, double amount, String url) throws SQLException {
        String sql = "UPDATE ACCOUNT SET AVAIL_BALANCE = AVAIL_BALANCE + ? WHERE ACCOUNT_ID = ?";

        try (Connection conn = getConnection(url); 
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, accountId);

            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SQLException("Error al modificar el saldo: " + ex.getMessage(), ex);
        }
    }
    
    public static Account obtenerCuentaPorId(int accountId, String url) throws SQLException {
        Account cuenta = null;

        String sql = "SELECT ACCOUNT_ID, OPEN_DATE, AVAIL_BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?";

        try (Connection conn = getConnection(url); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cuenta = new Account();
                    cuenta.setAccount_id(rs.getInt("ACCOUNT_ID"));
                    cuenta.setOpen_date(rs.getDate("OPEN_DATE"));
                    cuenta.setAvail_balance(rs.getDouble("AVAIL_BALANCE"));
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("No se encontró la cuenta con el ID: " + ex.getMessage(), ex);
        }

        return cuenta;
    }
}
