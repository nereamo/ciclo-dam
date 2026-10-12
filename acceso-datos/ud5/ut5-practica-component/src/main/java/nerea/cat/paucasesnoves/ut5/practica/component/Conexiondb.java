
package nerea.cat.paucasesnoves.ut5.practica.component;

import java.sql.SQLException;
import org.postgresql.util.PGobject;

/**
* POJO Conexiondb 
* @author Nerea Montoya
*/

public class Conexiondb extends PGobject{

    private String sgbd;
    private String ipDominio;
    private int puerto;
    
    public Conexiondb(String sgbd, String ipDominio, int puerto){
        this.sgbd = sgbd;
        this.ipDominio = ipDominio;
        this.puerto = puerto;
    }
    
    public String getSgbd(){
        return sgbd;
    }
    
    public void setSgbd(String sgbd){
        this.sgbd = sgbd;
    }
    
    public String getIpDominio(){
        return ipDominio;
    }
    
    public void setIpDominio(String ipDominio){
        this.ipDominio = ipDominio;
    }
    
    public int getPuerto(){
        return puerto;
    }
    
    public void setPuerto(int puerto){
        this.puerto = puerto;
    }
    
    @Override
    public void setValue(String value) throws SQLException {
        String[] fields = value.split(",");
        if (fields.length == 4) {
            this.sgbd = fields[0].trim();
            this.ipDominio = fields[1].trim();
            this.puerto = Integer.parseInt(fields[2].trim());
        } else {
            throw new SQLException("Formato de datos incorrecto para tipo compuesto.");
        }
    }
    
    @Override
    public String getValue() {
        return "(" + sgbd + "," + ipDominio + "," + puerto + ")";
    }

}
