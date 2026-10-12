package nerea.protrainer.jDialogs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import nerea.protrainer.dao.UsuarisDAO;
import nerea.protrainer.dao.WorkoutsDAO;
import nerea.protrainer.dto.Usuari;
import nerea.protrainer.dto.Workouts;
import static nerea.protrainer.eventosVisuales.EventosMouse.cambiarCursorEnJList;
import static nerea.protrainer.eventosVisuales.EventosMouse.resaltarBotonesJDialog;

/**
 * Clase EliminarEntrenamiento maneja las operaciones con la base de datos relacionado con Workouts {@code EliminarEntrenamiento}.
 * La clase extiende {@code javax.swing.JDialog}, permite que esta clase sea un diálogo modal.
 * 
 * @author Nerea
 */
public class EliminarEntrenamiento extends javax.swing.JDialog {

    /**
     * Variables utilizadas en la gestión de eliminar un workout de la base de datos que no tenga asignado ningún ejercicio.
     * <p>
     * - `usuariosList` almacena la lista de usuarios disponibles.
     * - `workoutList` almacena la lista de workouts disponibles.
     * - `usuarioId` almacena el ID del usuario.
     * - `mousePos` almacena la posición actual del mouse.
     */
    private List<Usuari> usuariosList = new ArrayList<>();
    private List<Integer> workoutList = new ArrayList<>();
    private int usuarioId;
    private Point mousePos = null;

    /**
     * Constructor que inicializa el JDialog, carga el combobox con los usuarios asignados a un instructor e inicializa los métodos resaltarBotones y cambiarCursorEnJList para propagar los eventos en los componentes.
     * 
     * @param parent JFrame principal (ventana padre) del JDiálog.
     * @param modal Si es {@code true}, el foco se mantiene en el JDiálog hasta que se cierra.
     */
    public EliminarEntrenamiento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(400, 400);
        setLocationRelativeTo(this);

        comboBoxUsuarios();
        
        cambiarCursorEnJList(lstWorkouts);
        resaltarBotonesJDialog(btnEliminar);
    }
    
    /**
     * Inicializa el JComboBox de usuarios en el JDialog. 
     * Recupera la lista de usuarios asignados a un instructor actual y los muestra en el JComboBox. 
     * Establece un "marcador" que indica que el usuario debe seleccionar un usuario.
     */
    private void comboBoxUsuarios() {
        usuariosList = UsuarisDAO.usuariosAsignadosInstructor();
        
        DefaultComboBoxModel<Usuari> dcm = new DefaultComboBoxModel<>();
        
        Usuari placeholder = new Usuari();
        placeholder.setNom("Selecciona un usuario...");
        dcm.addElement(placeholder);

        for (Usuari usuario : usuariosList) {
            dcm.addElement(usuario);
        }

        cmbBoxUsuario.setModel(dcm);
        cmbBoxUsuario.setSelectedIndex(0);
    }

    /**
     * Inicializa la JList de workouts en el JDialog. 
     * Recupera la lista de los workouts asociados al usuario seleccionado y los muestra en la JList. 
     */
    private void listaEntrenamientos(int userId) {

        ArrayList<Workouts> workouts = WorkoutsDAO.workoutUsuari(userId);

        workoutList.clear();

        DefaultListModel<Workouts> dlm = new DefaultListModel<>();

        for (Workouts workout : workouts) {
            workoutList.add(workout.getId());
            dlm.addElement(workout);
        }

        lstWorkouts.setModel(dlm);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEliminarEntrenamiento = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        cmbBoxUsuario = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstWorkouts = new javax.swing.JList<>();
        lblMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(null);

        pnlEliminarEntrenamiento.setBackground(new java.awt.Color(51, 51, 51));
        pnlEliminarEntrenamiento.setMinimumSize(new java.awt.Dimension(400, 400));
        pnlEliminarEntrenamiento.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlEliminarEntrenamiento.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Anton", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("ELIMINAR ENTRENAMIENTO");
        pnlEliminarEntrenamiento.add(lblTitulo);
        lblTitulo.setBounds(0, 10, 400, 50);

        cmbBoxUsuario.setBackground(new java.awt.Color(255, 255, 255));
        cmbBoxUsuario.setEditable(true);
        cmbBoxUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbBoxUsuario.setForeground(new java.awt.Color(0, 0, 0));
        cmbBoxUsuario.setToolTipText("Usuario");
        cmbBoxUsuario.setBorder(null);
        cmbBoxUsuario.setName(""); // NOI18N
        cmbBoxUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxUsuarioActionPerformed(evt);
            }
        });
        pnlEliminarEntrenamiento.add(cmbBoxUsuario);
        cmbBoxUsuario.setBounds(30, 70, 330, 30);

        btnEliminar.setBackground(new java.awt.Color(51, 51, 51));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlEliminarEntrenamiento.add(btnEliminar);
        btnEliminar.setBounds(170, 290, 40, 50);

        lstWorkouts.setBackground(new java.awt.Color(255, 255, 255));
        lstWorkouts.setBorder(null);
        lstWorkouts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lstWorkouts.setForeground(new java.awt.Color(0, 0, 0));
        lstWorkouts.setToolTipText("Entrenamiento");
        lstWorkouts.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(lstWorkouts);

        pnlEliminarEntrenamiento.add(jScrollPane2);
        jScrollPane2.setBounds(30, 130, 330, 120);

        lblMsg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlEliminarEntrenamiento.add(lblMsg);
        lblMsg.setBounds(40, 260, 320, 40);

        getContentPane().add(pnlEliminarEntrenamiento);
        pnlEliminarEntrenamiento.setBounds(0, 0, 400, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de acción al seleccionar un elemento del JComboBox.
     * Obtiene el ID del usuario seleccionado y muestra los entrenamientos asignados en la lista.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void cmbBoxUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxUsuarioActionPerformed

        int selectIndex = cmbBoxUsuario.getSelectedIndex();

        if (selectIndex > 0 && selectIndex - 1 < usuariosList.size()) {

            Usuari selectUsuario = usuariosList.get(selectIndex - 1);
            usuarioId = selectUsuario.getId();
            listaEntrenamientos(usuarioId);
        }
    }//GEN-LAST:event_cmbBoxUsuarioActionPerformed

    /**
     * Maneja el evento de acción para el botón "Eliminar". 
     * Verifica si el usuario y el entrenamiento han sido seleccionados. 
     * Si hay un error, mostrará un mensaje de error, de lo contrario, muestra mensaje de éxito.
     *
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int selectUsuario = cmbBoxUsuario.getSelectedIndex();

        if (selectUsuario <= 0) {
           lblMsg.setText("Por favor, selecciona un usuario.");
           return;
        }

        int selectWorkout = lstWorkouts.getSelectedIndex();

        if (selectWorkout == -1) {
            lblMsg.setText("Por favor, selecciona un entrenamiento." );
            return;
        }

        int workoutId = workoutList.get(selectWorkout);

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Estas seguro de eliminar el Workout?", "Confirm Deletion", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {

            boolean completado = WorkoutsDAO.eliminarWorkoutBD(workoutId);

            if (completado) {

                workoutList.remove(selectWorkout);
                JOptionPane.showMessageDialog(this, "Entrenamiento eliminado correctamente!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No puede eliminarse un entrenamiento que tiene ejercicios asignados.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<Usuari> cmbBoxUsuario;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<Workouts> lstWorkouts;
    private javax.swing.JPanel pnlEliminarEntrenamiento;
    // End of variables declaration//GEN-END:variables
}
