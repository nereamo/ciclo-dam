
package nerea.protrainer.dto;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
* Modelo de tabla para representar una lista de entrenamientos en un {@code JTable}. 
* Extiende {@code AbstractTableModel} y define las columnas de la tabla.
* 
* @author Nerea
*/

public class WorkoutsTableModel extends AbstractTableModel{
    
    /**
     * Variables relacionadas con la gestión de los entrenamientos en la aplicación.
     * <p>
     * - `workoutsList` almacena la lista de todos los workouts. 
     * - `columnNames` contiene los nombres de las columnas para la tabla de workouts.
     */
    private List<Workouts> workoutsList;
    private final String[] columnNames = {"Fecha", "Comentarios"};

    /**
    * Constructor que inicializa el modelo de la tabla con la lista de los entrenamientos.
    * @param workoutsList Lista de entrenamientos a mostrar en la tabla.
    */
    public WorkoutsTableModel(List<Workouts> workoutsList) {
        this.workoutsList = workoutsList;
    }
    
    /**
     * Obtiene el número de filas de la tabla.
     * @return Devuelve un total de filas dependiedo cuantos entrenamientos haya.
     */
    @Override
    public int getRowCount() {
        return workoutsList.size();
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
        Workouts workout = workoutsList.get(rowIndex);
        switch (columnIndex) {
            case 0: return workout.getForDate();
            case 1: return workout.getComments();
            default: return null;
        }
    }

    /**
     * Actualiza la lista de entrenamientos, notifica a la tabla y actualiza los datos.
     * @param workoutsList Lista de entrenamientos.
     */
    public void setWorkouts(List<Workouts> workoutsList) {
        this.workoutsList = workoutsList;
        fireTableDataChanged();
    }
}
