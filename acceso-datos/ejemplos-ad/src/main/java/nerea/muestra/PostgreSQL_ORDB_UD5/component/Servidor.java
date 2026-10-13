package nerea.muestra.PostgreSQL_ORDB_UD5.component;

import java.util.List;
import nerea.muestra.Exception.AppException;

/**
* POJO Servidor 
* @author Nerea Montoya
*/

public class Servidor {
    
    private String codigo;
    private String nombre;
    private List<String> usuarios;
    private Conexiondb conexion;
    private boolean activo;
    
    public Servidor (String codigo, String nombre, List<String> usuarios, Conexiondb conexion, boolean activo) throws AppException{
        setCodigo(codigo);
        setNombre(nombre);
        setUsuarios(usuarios);
        setConexiondb(conexion);
        setActivo(activo);
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setCodigo(String codigo) throws AppException{
        if(codigo == null || codigo.length() > 15){
            throw new AppException("El código no puede tener mas de 15 carácteres.");
        }
        this.codigo = codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) throws AppException{
        if(nombre == null || nombre.length() > 50){
            throw new AppException("El nombre no puede tener mas de 50 carácteres.");
        }
        this.nombre = nombre;
    }
    
    public List<String> getUsuarios(){
        return usuarios;
    }
    
    public void setUsuarios(List<String> usuarios) throws AppException{
        if(usuarios == null){
            throw new AppException("Debes introducir un nombre.");
        }
        this.usuarios = usuarios;
    }
    
    public Conexiondb getConexiondb(){
        return conexion;
    }
    
    public void setConexiondb(Conexiondb conexion) throws AppException{
        if(conexion == null){
            throw new AppException("Los datos de conexión no pueden estar vacíos.");
        }
        this.conexion = conexion;
    }
    
    public boolean isActivo(){
        return activo;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Servidor{" + "codigo=" + codigo + 
                ", nombre=" + nombre + 
                ", usuarios=" + usuarios + 
                ", conexion=" + conexion + 
                ", activo=" + activo + '}';
    }

}
