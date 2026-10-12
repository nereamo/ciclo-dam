package nerea.protrainer.jDialogs;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import nerea.protrainer.dao.UsuarisDAO;
import nerea.protrainer.dao.WorkoutsDAO;
import nerea.protrainer.dto.Usuari;
import static nerea.protrainer.eventosVisuales.EventosMouse.resaltarBotonesJDialog;

/**
 * Clase AñadirEntrenamiento maneja las operaciones con la base de datos relacionado con Workouts asociados a un usuario {@code AñadirEntrenamiento}.
 * La clase extiende {@code javax.swing.JDialog}, permite que esta clase sea un diálogo modal.
 * 
 * @author Nerea
 */
public class AñadirNuevoEntrenamiento extends javax.swing.JDialog {

    /**
     * Variables utilizadas en la gestión de crear un nuevo entrenamiento.
     * <p>
     * - `usuariosList` almacena la lista de usuarios disponibles. 
     * - `seleccionarUsuarioId` almacena el ID del usuario seleccionado.
     */
    private List<Usuari> usuariosList = new ArrayList<>();
    private int seleccionarUsuarioId;

    /**
     * Constructor que inicializa el JDialog, carga los JComboBox de los usuarios e inicializa el método resaltarBotones para propagar los eventos en los componentes.
     *
     * @param parent JFrame principal (ventana padre) del JDiálog.
     * @param modal Si es {@code true}, el foco se mantiene en el JDiálog hasta que se cierra.
     */
    public AñadirNuevoEntrenamiento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(400, 400);
        setLocationRelativeTo(this);

        comboBoxUsuarios();
        
        resaltarBotonesJDialog(btnGuardar);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNuevoWorkout = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        cmbBoxUsuario = new javax.swing.JComboBox<>();
        spnrFecha = new javax.swing.JSpinner();
        btnGuardar = new javax.swing.JButton();
        txtFieldComentario = new javax.swing.JTextField();
        lblMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlNuevoWorkout.setBackground(new java.awt.Color(51, 51, 51));
        pnlNuevoWorkout.setMinimumSize(new java.awt.Dimension(400, 400));
        pnlNuevoWorkout.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlNuevoWorkout.setLayout(null);

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Anton", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("NUEVO ENTRENAMIENTO");
        pnlNuevoWorkout.add(lblTitulo);
        lblTitulo.setBounds(0, 10, 400, 60);

        cmbBoxUsuario.setBackground(new java.awt.Color(255, 255, 255));
        cmbBoxUsuario.setEditable(true);
        cmbBoxUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbBoxUsuario.setForeground(new java.awt.Color(0, 0, 0));
        cmbBoxUsuario.setToolTipText("Usuario");
        cmbBoxUsuario.setBorder(null);
        cmbBoxUsuario.setName("Usuari"); // NOI18N
        cmbBoxUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxUsuarioActionPerformed(evt);
            }
        });
        pnlNuevoWorkout.add(cmbBoxUsuario);
        cmbBoxUsuario.setBounds(90, 80, 220, 30);

        spnrFecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spnrFecha.setModel(new javax.swing.SpinnerDateModel());
        spnrFecha.setToolTipText("Fecha");
        spnrFecha.setBorder(null);
        pnlNuevoWorkout.add(spnrFecha);
        spnrFecha.setBounds(90, 140, 220, 30);

        btnGuardar.setBackground(new java.awt.Color(51, 51, 51));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlNuevoWorkout.add(btnGuardar);
        btnGuardar.setBounds(160, 280, 50, 50);

        txtFieldComentario.setBackground(new java.awt.Color(255, 255, 255));
        txtFieldComentario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFieldComentario.setForeground(new java.awt.Color(102, 102, 102));
        txtFieldComentario.setText("Añade un comentario...");
        txtFieldComentario.setToolTipText("Comentario");
        txtFieldComentario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldComentarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFieldComentarioFocusLost(evt);
            }
        });
        pnlNuevoWorkout.add(txtFieldComentario);
        txtFieldComentario.setBounds(90, 200, 220, 30);

        lblMsg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlNuevoWorkout.add(lblMsg);
        lblMsg.setBounds(50, 250, 290, 30);

        getContentPane().add(pnlNuevoWorkout);
        pnlNuevoWorkout.setBounds(0, 0, 400, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de acción al seleccionar un elemento del JComboBox.
     * Obtiene el ID del usuario seleccionado para poder añadirle un nuevo workout.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void cmbBoxUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxUsuarioActionPerformed

        int selectIndex = cmbBoxUsuario.getSelectedIndex();

        if (selectIndex > 0) {

            Usuari selectUsuario = usuariosList.get(selectIndex - 1);
            seleccionarUsuarioId = selectUsuario.getId();
        }
    }//GEN-LAST:event_cmbBoxUsuarioActionPerformed

    /**
     * Maneja el evento de acción para el botón "Guardar".
     * Verifica si el usuario ha sido seleccionados y si ha añadido un comentario.
     * Si hay un error, mostrará un mensaje de error, de lo contrario, muestra mensaje de éxito.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int selectUsuario = cmbBoxUsuario.getSelectedIndex();

        if (selectUsuario <= 0) {

            lblMsg.setText("Por favor, selecciona un usuario.");
            return;
        }

        Usuari usuario = usuariosList.get(selectUsuario - 1);
        usuario.getId();

        String comentario = txtFieldComentario.getText().trim();

        if (comentario.isEmpty() || comentario.equals("Añade un comentario...")) {
            lblMsg.setText("Debe ingresar un comentario.");
        } else {
            try {

                Date fechaWorkout = (Date) spnrFecha.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaFormateada = sdf.format(fechaWorkout);

                WorkoutsDAO.insertarWorkoutBD(seleccionarUsuarioId, comentario, fechaFormateada);
                JOptionPane.showMessageDialog(this, "Nuevo entrenamiento añadido.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el entrenamiento en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * Activa el foco en el campo de texto para escribir el comentario.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void txtFieldComentarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldComentarioFocusGained
        if (txtFieldComentario.getText().equals("Añade un comentario...")) {
            txtFieldComentario.setText("");
        }
    }//GEN-LAST:event_txtFieldComentarioFocusGained

    /**
     * Desactiva el foco en el campo de texto para escribir el comentario.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void txtFieldComentarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldComentarioFocusLost
        if (txtFieldComentario.getText().isEmpty()) {
            txtFieldComentario.setText("Añade un comentario...");
        }
    }//GEN-LAST:event_txtFieldComentarioFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Usuari> cmbBoxUsuario;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlNuevoWorkout;
    private javax.swing.JSpinner spnrFecha;
    private javax.swing.JTextField txtFieldComentario;
    // End of variables declaration//GEN-END:variables
}
