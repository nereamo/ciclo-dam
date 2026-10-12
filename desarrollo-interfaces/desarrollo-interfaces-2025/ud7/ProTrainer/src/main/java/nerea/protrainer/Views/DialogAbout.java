package nerea.protrainer.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase DialogAbout muestra un cuádro de diálogo en él se muestra la información de la aplicación asi como el autor {@code DialogAbout}.
 * La clase extiende {@code javax.swing.JDialog}, permite que esta clase sea un diálogo modal.
 * 
 * @author Nerea
 */
public class DialogAbout extends javax.swing.JDialog {
    
    /**
     * Constructor que inicializa el JDialog e inicializa el JPanel donde se muestra la información.
     * 
     * @param parent JFrame principal (ventana padre) del JDiálog.
     * @param modal Si es {@code true}, el foco se mantiene en el JDiálog hasta que se cierra.
     */
    public DialogAbout(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(450, 220);
        setLocationRelativeTo(this);

        String aboutText = "<html><h2>Nerea Montoya</h2>"
                + "<p>Curso: DAM</p>"
                + "<p>Módulo: Desarrollo de Interfaces</p>"
                + "<br>"
                + "<p>ProTrainer: Aplicación que permite gestionar entrenamientos y ejercicios.</p></html>";

        jLblAbout.setText(aboutText);
        jLblAbout.setVerticalAlignment(JLabel.TOP);

        JButton btnClose = new JButton("Cerrar");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnClose);
        add(panel, BorderLayout.SOUTH);

        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAbout = new javax.swing.JPanel();
        jLblAbout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 150));
        getContentPane().setLayout(null);

        pnlAbout.setBackground(new java.awt.Color(51, 51, 51));
        pnlAbout.setMaximumSize(new java.awt.Dimension(450, 220));
        pnlAbout.setMinimumSize(new java.awt.Dimension(450, 220));
        pnlAbout.setPreferredSize(new java.awt.Dimension(450, 220));
        pnlAbout.setLayout(null);

        jLblAbout.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLblAbout.setForeground(new java.awt.Color(255, 255, 255));
        pnlAbout.add(jLblAbout);
        jLblAbout.setBounds(9, 21, 430, 160);

        getContentPane().add(pnlAbout);
        pnlAbout.setBounds(0, 0, 450, 220);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblAbout;
    private javax.swing.JPanel pnlAbout;
    // End of variables declaration//GEN-END:variables
}
