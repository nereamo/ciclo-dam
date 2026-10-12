package nerea.protrainer.jDialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import nerea.protrainer.dao.ExercicisDAO;
import nerea.protrainer.dto.Exercicis;
import static nerea.protrainer.eventosVisuales.EventosMouse.resaltarBotonesJDialog;

/**
 * Clase ModificarEjercicio maneja las operaciones con la base de datos relacionado con Exercicis {@code ModificarEjercicio}.
 * La clase extiende {@code javax.swing.JDialog}, permite que esta clase sea un diálogo modal.
 *
 * @author Nerea
 */
public class ModificarEjercicio extends javax.swing.JDialog {

    /**
     * Variables utilizadas en la gestión de modificar el nombre y la descripción de un ejercicio.
     * <p>
     * - `ejerciciosList` almacena la lista de ejercicios disponibles.
     * - `ejercicios` almacena un objeto de tipo `Exercicis` que representa el ejercicio a modificar.
     */
    private List<Exercicis> ejerciciosList;
    private Exercicis ejercicios = new Exercicis();

    /**
     * Constructor que inicializa el JDialog, carga los JComboBOx de los ejercicios e inicializa el método resaltarBotones para propagar los eventos en los componentes.
     * 
     * @param parent JFrame principal (ventana padre) del JDiálog.
     * @param modal Si es {@code true}, el foco se mantiene en el JDiálog hasta que se cierra.
     */
    public ModificarEjercicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(400, 400);
        setLocationRelativeTo(this);

        comboBoxEjercicios();
        resaltarBotonesJDialog(btnGuardar);  
    }
    
    /**
     * Inicializa el JComboBox de los ejercicios en el JDialog. 
     * Recupera la lista de ejercicios presentes en la base d edatos y los muestra en el JComboBox. 
     * Establece un "marcador" que indica que el usuario debe seleccionar un ejercicio.
     */
    private void comboBoxEjercicios() {
        
        ejerciciosList = ExercicisDAO.exercicisBD();
        
        DefaultComboBoxModel<Exercicis> dcm = new DefaultComboBoxModel<>();
        
        Exercicis placeholder = new Exercicis();
        placeholder.setNomExercici("Selecciona un ejercicio...");
        placeholder.setDescripcion("");
        dcm.addElement(placeholder);

        for (Exercicis ejercicios : ejerciciosList) {
            dcm.addElement(ejercicios);
        }

        cmbBoxEjercicios.setModel(dcm);
        cmbBoxEjercicios.setSelectedIndex(0);

        cmbBoxEjercicios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selectIndex = cmbBoxEjercicios.getSelectedIndex();
                if (selectIndex > 0) {
                    ejercicios = ejerciciosList.get(selectIndex - 1);

                    txtFieldNombre.setText(ejercicios.getNomEjercicio());
                    txtFieldDescripcion.setText(ejercicios.getDescripcion());
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlModificarEjercicio = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        cmbBoxEjercicios = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        txtFieldNombre = new javax.swing.JTextField();
        txtFieldDescripcion = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(null);

        pnlModificarEjercicio.setBackground(new java.awt.Color(51, 51, 51));
        pnlModificarEjercicio.setMinimumSize(new java.awt.Dimension(400, 400));
        pnlModificarEjercicio.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlModificarEjercicio.setLayout(null);

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Anton", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("MODIFICAR EJERCICIO");
        pnlModificarEjercicio.add(lblTitulo);
        lblTitulo.setBounds(0, 10, 400, 50);

        cmbBoxEjercicios.setBackground(new java.awt.Color(255, 255, 255));
        cmbBoxEjercicios.setEditable(true);
        cmbBoxEjercicios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbBoxEjercicios.setForeground(new java.awt.Color(0, 0, 0));
        cmbBoxEjercicios.setToolTipText("Ejercicio");
        pnlModificarEjercicio.add(cmbBoxEjercicios);
        cmbBoxEjercicios.setBounds(80, 70, 230, 30);

        btnGuardar.setBackground(new java.awt.Color(51, 51, 51));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlModificarEjercicio.add(btnGuardar);
        btnGuardar.setBounds(170, 280, 50, 50);

        txtFieldNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtFieldNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFieldNombre.setForeground(new java.awt.Color(102, 102, 102));
        txtFieldNombre.setText("Nuevo nombre");
        txtFieldNombre.setToolTipText("Nuevo nombre ejercicio");
        txtFieldNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFieldNombreFocusLost(evt);
            }
        });
        pnlModificarEjercicio.add(txtFieldNombre);
        txtFieldNombre.setBounds(80, 140, 230, 30);

        txtFieldDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtFieldDescripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFieldDescripcion.setForeground(new java.awt.Color(102, 102, 102));
        txtFieldDescripcion.setText("Nueva descripción");
        txtFieldDescripcion.setToolTipText("Descripción");
        txtFieldDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldDescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFieldDescripcionFocusLost(evt);
            }
        });
        pnlModificarEjercicio.add(txtFieldDescripcion);
        txtFieldDescripcion.setBounds(80, 200, 230, 30);

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setText("Descripción:");
        pnlModificarEjercicio.add(lblDescripcion);
        lblDescripcion.setBounds(80, 180, 100, 20);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre Ejercicio:");
        pnlModificarEjercicio.add(lblNombre);
        lblNombre.setBounds(80, 120, 120, 20);

        lblMsg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlModificarEjercicio.add(lblMsg);
        lblMsg.setBounds(80, 240, 230, 40);

        getContentPane().add(pnlModificarEjercicio);
        pnlModificarEjercicio.setBounds(0, 0, 400, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Activa el foco en el campo de texto para escribir el nuevo nombre.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void txtFieldNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldNombreFocusGained
        if (txtFieldNombre.getText().equals(ejercicios.getNomEjercicio())) {
            txtFieldNombre.setText("");
        }
    }//GEN-LAST:event_txtFieldNombreFocusGained

    /**
     * Desactiva el foco en el campo de texto para escribir el nuevo nombre.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void txtFieldNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldNombreFocusLost
        if (txtFieldNombre.getText().isEmpty()) {
            txtFieldNombre.setText(ejercicios.getNomEjercicio());
        }
    }//GEN-LAST:event_txtFieldNombreFocusLost

    /**
     * Activa el foco en el campo de texto para escribir la nueva descripción.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void txtFieldDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldDescripcionFocusGained
        if (txtFieldDescripcion.getText().equals(ejercicios.getDescripcion())) {
            txtFieldDescripcion.setText("");
        }
    }//GEN-LAST:event_txtFieldDescripcionFocusGained

    /**
     * Desactiva el foco en el campo de texto para escribir la nueva descripción.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void txtFieldDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldDescripcionFocusLost
        if (txtFieldDescripcion.getText().isEmpty()) {
            txtFieldDescripcion.setText(ejercicios.getDescripcion());
        }
    }//GEN-LAST:event_txtFieldDescripcionFocusLost

    /**
     * Maneja el evento de acción para el botón "Guardar". 
     * Verifica si se ha seleccionado un ejercicio. 
     * Si hay un error, mostrará un mensaje de error, de lo contrario, muestra mensaje de éxito.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int selectEjercicio = cmbBoxEjercicios.getSelectedIndex();

        if (selectEjercicio <= 0) {

            lblMsg.setText("Por favor, selecciona un ejercicio.");
            return;
        }
        
        try {
            String nuevoNombre = txtFieldNombre.getText().trim();
            String nuevaDescripcion = txtFieldDescripcion.getText().trim();

            if (nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty() || nuevoNombre.equals("Nuevo nombre") || nuevaDescripcion.equals("Nueva descripción")) {
                lblMsg.setText("El nombre y la descripción no pueden estar vacíos.");
                return;
            }

            ejercicios.setNomExercici(nuevoNombre);
            ejercicios.setDescripcion(nuevaDescripcion);

            if (ExercicisDAO.editarEjerciciosBD(ejercicios)) {
                JOptionPane.showMessageDialog(this, "Ejercicio modificado correctamente!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar, vuelva a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Exercicis> cmbBoxEjercicios;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlModificarEjercicio;
    private javax.swing.JTextField txtFieldDescripcion;
    private javax.swing.JTextField txtFieldNombre;
    // End of variables declaration//GEN-END:variables
}
