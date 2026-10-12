package nerea.protrainer.jDialogs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import nerea.protrainer.dao.ExercicisDAO;
import nerea.protrainer.dto.Exercicis;
import nerea.protrainer.dto.ExercicisTableModel;
import static nerea.protrainer.eventosVisuales.EventosMouse.cambiarCursorEnTablas;
import static nerea.protrainer.eventosVisuales.EventosMouse.resaltarBotonesJDialog;

/**
 * Clase EliminarEjercicio maneja las operaciones con la base de datos relacionado con Ejercicios {@code EliminarEjercicio}.
 * La clase extiende {@code javax.swing.JDialog}, permite que esta clase sea un diálogo modal.
 * 
 * @author Nerea
 */
public class EliminarEjercicio extends javax.swing.JDialog {

    /**
     * Variables utilizadas en la gestión de eliminar un ejercicio de la base de datos que no esté asignado a ningún entrenamiento.
     * <p>
     * - `ejerciciosList` almacena la lista de ejercicios disponibles. 
     */
    private List<Exercicis> ejerciciosList = new ArrayList<>();

    /**
     * Constructor que inicializa el JDialog, carga la lista de ejercicios e inicializa el método resaltarBotones y cambiarCursorEnJList para propagar los eventos en los componentes.
     * 
     * @param parent JFrame principal (ventana padre) del JDiálog.
     * @param modal Si es {@code true}, el foco se mantiene en el JDiálog hasta que se cierra.
     */
    public EliminarEjercicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(500, 400);
        setLocationRelativeTo(this);
        
        tblEjerciciosUsuario();

        resaltarBotonesJDialog(btnEliminar);
        cambiarCursorEnTablas(tblEjercicios);

    }
    
    /**
     * Método que inicializa una tabla con los ejercicios existentes en la base de datos.
     */
    private void tblEjerciciosUsuario() {

        try {
            ArrayList<Exercicis> ejercicios = ExercicisDAO.exercicisBD();
            ejerciciosList = ejercicios;
            
            ExercicisTableModel etm = new ExercicisTableModel(ejercicios);
            tblEjercicios.setModel(etm);
            tblEjercicios.setRowHeight(25);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEliminarEjercicio = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEjercicios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(null);

        pnlEliminarEjercicio.setBackground(new java.awt.Color(51, 51, 51));
        pnlEliminarEjercicio.setMinimumSize(new java.awt.Dimension(500, 400));
        pnlEliminarEjercicio.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Anton", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("ELIMINAR EJERCICIO");
        pnlEliminarEjercicio.add(lblTitulo);
        lblTitulo.setBounds(0, 10, 500, 50);

        btnEliminar.setBackground(new java.awt.Color(51, 51, 51));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlEliminarEjercicio.add(btnEliminar);
        btnEliminar.setBounds(230, 260, 40, 50);

        lblMsg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlEliminarEjercicio.add(lblMsg);
        lblMsg.setBounds(30, 240, 440, 30);

        jScrollPane1.setBorder(null);

        tblEjercicios.setForeground(new java.awt.Color(0, 0, 0));
        tblEjercicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Descripción", "Imagen"
            }
        ));
        tblEjercicios.setToolTipText("Tabla ejercicios");
        tblEjercicios.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblEjercicios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblEjercicios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblEjercicios);

        pnlEliminarEjercicio.add(jScrollPane1);
        jScrollPane1.setBounds(40, 80, 420, 150);

        getContentPane().add(pnlEliminarEjercicio);
        pnlEliminarEjercicio.setBounds(0, 0, 500, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de acción para el botón "Eliminar".
     * Verifica si ha seleccionado un ejercicio.
     * Si hay un error, mostrará un mensaje de error, de lo contrario, muestra mensaje de éxito.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int selectedRow = tblEjercicios.getSelectedRow();

        if (selectedRow != -1) {

            Exercicis ejercicios = ejerciciosList.get(selectedRow);
            ejercicios.getId();  
            
            if (selectedRow <= 0) {
                lblMsg.setText("Por favor, selecciona un ejercicio.");
                return;
            }
         
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el ejercicio: " + ejercicios.getNomEjercicio() + " " + ejercicios.getDescripcion() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                boolean completado = ExercicisDAO.eliminaExercicis(ejercicios.getId());

                    if (completado) {

                        ejerciciosList.remove(selectedRow);
                        JOptionPane.showMessageDialog(this, "Ejercicio eliminado correctamente!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "No es posible eliminar un ejercicio asociado a un entrenamiento.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }
        } else {
           
            lblMsg.setText("Por favor, selecciona un ejercicio de la lista.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlEliminarEjercicio;
    private javax.swing.JTable tblEjercicios;
    // End of variables declaration//GEN-END:variables
}
