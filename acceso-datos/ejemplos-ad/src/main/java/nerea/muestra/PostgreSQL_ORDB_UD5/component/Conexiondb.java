package nerea.muestra.PostgreSQL_ORDB_UD5.component;

import java.sql.SQLException;
import nerea.muestra.Exception.AppException;
import org.postgresql.util.PGobject;

/**
 * POJO Conexiondb
 *
 * @author Nerea Montoya
 */
public class Conexiondb extends PGobject {

    private String sgbd;
    private String ipDominio;
    private int puerto;
    
    public Conexiondb(){
    }

    public Conexiondb(String sgbd, String ipDominio, int puerto) throws AppException {
        setSgbd(sgbd);
        setIpDominio(ipDominio);
        setPuerto(puerto);
        this.type = "\"ut5-practica\".\"conexiondb\"";
        
    }

    public String getSgbd() {
        return sgbd;
    }

    public void setSgbd(String sgbd) throws AppException {
        if (sgbd.length() > 20) {
            throw new AppException("El SGBD no puede tener mas de 20 carácteres.");
        }
        this.sgbd = sgbd;
    }

    public String getIpDominio() {
        return ipDominio;
    }

    public void setIpDominio(String ipDominio) throws AppException {
        if (ipDominio.length() > 15) {
            throw new AppException("La dirección ip no puede tener mas de 15 carácteres.");
        }
        this.ipDominio = ipDominio;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) throws AppException {
        if (puerto < 0 || puerto > 65535) {
            throw new AppException("El puerto no puede tener mas de 5 carácteres.");
        }
        this.puerto = puerto;
    }

    @Override
    public void setValue(String value) throws SQLException {
        try {
            String[] fields = value.replace("(", "").replace(")", "").split(",");
            if (fields.length != 3) {
                throw new AppException("Formato incorrecto. Se esperaban 3 campos.");
            }

            setSgbd(fields[0].trim());
            setIpDominio(fields[1].trim());
            setPuerto(Integer.parseInt(fields[2].trim()));

            super.setValue(value); // importante para PGobject

        } catch (AppException | NumberFormatException e) {
            throw new SQLException("Error al asignar valor a Conexiondb: " + e.getMessage(), e);
        }
    }

    @Override
    public String getValue() {
        return "(" + sgbd + "," + ipDominio + "," + puerto + ")";
    }
}
