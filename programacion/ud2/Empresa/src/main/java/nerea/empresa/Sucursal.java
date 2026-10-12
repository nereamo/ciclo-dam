package nerea.empresa;

/**
 * Class Sucursal con getters/setters, constructores y metodos que se implementan en Class Empresa
 *
 * @author Nerea Montoya
 */
public class Sucursal {

    private String nombre;

    private int numeroEmpleados;

    private double beneficios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(double beneficios) {
        this.beneficios = beneficios;
    }

    public Sucursal() {

    }

    public Sucursal(String nombre, int numeroEmpleado, double beneficios) {
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleado;
        this.beneficios = beneficios;

    }

    public double calcularPagaExtra() {

        double beneficioEmpleado = this.beneficios / this.numeroEmpleados;
        return beneficioEmpleado;

    }

    public char clasificar() {

        double clasificar = this.calcularPagaExtra();

        if (clasificar < 150) {
            return 'C';
        } else if (clasificar < 300) {
            return 'B';
        } else {
            return 'A';
        }

    }
}
