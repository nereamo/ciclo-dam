package nerea.montoya_olivares_nerea_bloc09_tarea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Panel donde se muestra un panel tabulado para las distintas incidencias.
 * Muestra una tabla con la fecha, descripción y localización de la incidencia.
 * 
 * @author Nerea Montoya
 */
public class GestionIncidencias extends JFrame {

    private JTextField descripcionField, localizacionField;
    private JRadioButton btnAlta, btnBaja;
    private DefaultTableModel modelAlta, modelBaja;

    public GestionIncidencias() {
        setTitle("Classificación de las incidencias");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();

        JPanel pnlFormulario = new JPanel(null);
        iniciarFormulario(pnlFormulario);

        modelAlta = new DefaultTableModel(new Object[]{"Fecha", "Descripción", "Localización"}, 0);
        modelBaja = new DefaultTableModel(new Object[]{"Fecha", "Descripción", "Localización"}, 0);

        JTable tblAlta = new JTable(modelAlta);
        JTable tblBaja = new JTable(modelBaja);

        JScrollPane scrollAlta = new JScrollPane(tblAlta);
        JScrollPane scrollBaja = new JScrollPane(tblBaja);

        JPanel pnlAlta = new JPanel(new BorderLayout());
        pnlAlta.add(scrollAlta, BorderLayout.CENTER);

        JPanel pnlBaja = new JPanel(new BorderLayout());
        pnlBaja.add(scrollBaja, BorderLayout.CENTER);

        pestañas.addTab("Incidencias", pnlFormulario);
        pestañas.addTab("Prioridad Alta", pnlAlta);
        pestañas.addTab("Prioridad Baja", pnlBaja);
        pestañas.setBackground(Color.LIGHT_GRAY);

        add(pestañas);

        setVisible(true);
    }

    private void iniciarFormulario(JPanel pnlFormulario) {
        JLabel lblDescripcion = new JLabel("DESCRIPCIÓN:");
        lblDescripcion.setBounds(40, 40, 100, 25);
        pnlFormulario.add(lblDescripcion);

        descripcionField = new JTextField();
        descripcionField.setBounds(160, 40, 300, 25);
        pnlFormulario.add(descripcionField);

        JLabel lblLocalizacion = new JLabel("LOCALIZACIÓN:");
        lblLocalizacion.setBounds(40, 80, 100, 25);
        pnlFormulario.add(lblLocalizacion);

        localizacionField = new JTextField();
        localizacionField.setBounds(160, 80, 200, 25);
        pnlFormulario.add(localizacionField);

        btnAlta = new JRadioButton("Alta");
        btnBaja = new JRadioButton("Baja");
        btnAlta.setBounds(380, 80, 60, 25);
        btnBaja.setBounds(450, 80, 60, 25);

        ButtonGroup bntGroup = new ButtonGroup();
        bntGroup.add(btnAlta);
        bntGroup.add(btnBaja);

        pnlFormulario.add(btnAlta);
        pnlFormulario.add(btnBaja);

        JButton bntAñadir = new JButton("Añadir");
        bntAñadir.setBounds(160, 130, 100, 30);
        pnlFormulario.add(bntAñadir);

        bntAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarIncidencia(bntGroup);
            }
        });
    }

    private void agregarIncidencia(ButtonGroup bntGroup) {
        String descripcion = descripcionField.getText();
        String localizacion = localizacionField.getText();
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
        
        if(descripcion.trim().isEmpty() || localizacion.trim().isEmpty()){
            JOptionPane.showMessageDialog(GestionIncidencias.this, "Debe introducir la descripción y la localización.","Error",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!btnAlta.isSelected() && !btnBaja.isSelected()) {
            JOptionPane.showMessageDialog(GestionIncidencias.this, "Debe seleccionar una prioridad (Alta o Baja).","Error",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (btnAlta.isSelected()) {
            modelAlta.addRow(new Object[]{fecha, descripcion, localizacion});
            JOptionPane.showMessageDialog(GestionIncidencias.this, "Incidencia de alta prioridad añadida correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } else if (btnBaja.isSelected()) {
            modelBaja.addRow(new Object[]{fecha, descripcion, localizacion});
            JOptionPane.showMessageDialog(GestionIncidencias.this, "Incidencia de baja prioridad añadida correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        }

        descripcionField.setText("");
        localizacionField.setText("");
        bntGroup.clearSelection();
    }
}
