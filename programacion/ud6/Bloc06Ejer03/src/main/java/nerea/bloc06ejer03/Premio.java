package nerea.bloc06ejer03;

/**
 * Classe que implementa Comparable (compara objetos entre sí y los ordena);
 * @author Nerea
 */
public class Premio implements Comparable<Premio>{
    
    private String numero;
    private int cantidad;

    public Premio(){ 
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(!numero.matches("\\d{5}")){
            throw new IllegalArgumentException("El número debe tener 5 dígitos.");
        }
        this.numero = numero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if(cantidad != 400000 && cantidad != 125000 && cantidad != 50000 && cantidad != 20000){
            throw new IllegalArgumentException("La cantidad introducida no es correcta");
        }
        this.cantidad = cantidad;
    }
    
    @Override
    public int compareTo(Premio otroPremio) {
        return Integer.compare(otroPremio.getCantidad(), this.cantidad);
    } 
}
