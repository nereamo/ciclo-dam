
package nerea.protrainer.dto;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla para representar una lista de ejercicios en un {@code JTable}.
 * Extiende {@code AbstractTableModel} y define las columnas de la tabla.
 * 
 * @author Nerea
 */

public class ExercicisTableModel extends AbstractTableModel{
    
    /**
     * Variables relacionadas con la gestión de los ejercicios en la aplicación.
     * <p>
     * - `ejerciciosList` almacena la lista de todos los ejercicios. 
     * - `columnNames` contiene los nombres de las columnas para la tabla de ejercicios.
     */
    private List<Exercicis> ejerciciosList;
    private final String[] columnNames = {"Nombre", "Descripción", "Foto Demo"};

    /**
     * Constructor que inicializa el modelo de la tabla con la lista de los ejercicios.
     * @param ejercicios Lista de ejercicios a mostrar en la tabla.
     */
    public ExercicisTableModel(List<Exercicis> ejercicios) {
        this.ejerciciosList = ejercicios;
    }

    /**
     * Obtiene el número de filas de la tabla.
     * @return Devuelve un total de filas dependiedo cuantos ejercicios haya.
     */
    @Override
    public int getRowCount() {
        return ejerciciosList.size();
    }

    /**
     * Obtiene el número de columnas de la tabla.
     * @return Devuelve el total de columnas de la tabla.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Obtiene el nombre de las columnas con su índice.
     * @param column Índice de la columna.
     * @return Devuelve el nombre de las columnas correspondientes.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Obtiene el valor de una celda específica de la tabla.
     * @param rowIndex Índice de filas.
     * @param columnIndex Índice de columnas.
     * @return Devuelve el valor de cada celda en la posición especificada.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exercicis exercici = ejerciciosList.get(rowIndex);
        switch (columnIndex) {
            case 0: return exercici.getNomEjercicio();
            case 1: return exercici.getDescripcion();
            case 2: return exercici.getDemoFoto();
            default: return null;
        }
    }

    /**
     * Actualiza la lista de ejercicios, notifica a la tabla y actualiza los datos.
     * @param ejercicios Lista de ejercicios.
     */
    public void setEjercicios(List<Exercicis> ejercicios) {
        this.ejerciciosList = ejercicios;
        fireTableDataChanged();
    }
}
