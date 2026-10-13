package nerea.muestra.PostgreSQL_ORDB_UD5.gui;


import java.awt.Frame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Exception.AppException;
import nerea.muestra.PostgreSQL_ORDB_UD5.component.OperacionesDB;
import nerea.muestra.PostgreSQL_ORDB_UD5.component.Servidor;

/**
 *  Class Gui que permite modificar una conexion de un servidor
 * @author Nerea Montoya
 */
public class JDialogUpdateConnection extends javax.swing.JDialog {
    
    OperacionesDB op = new OperacionesDB();
    private Servidor servidor;

    public JDialogUpdateConnection(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblTitle = new javax.swing.JLabel();
        jBttnUpdate = new javax.swing.JButton();
        jTxtPuerto = new javax.swing.JTextField();
        jTxtCodigoServidor = new javax.swing.JTextField();
        jTxtIpDominio = new javax.swing.JTextField();
        jTxtSgbd = new javax.swing.JTextField();
        jLblPerto = new javax.swing.JLabel();
        jLblCodigoServidor = new javax.swing.JLabel();
        jLblIpDominio = new javax.swing.JLabel();
        jLblSgbd = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 320));
        jPanel1.setLayout(null);

        jLblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLblTitle.setForeground(new java.awt.Color(0, 0, 0));
        jLblTitle.setText("Modificar conexión:");
        jPanel1.add(jLblTitle);
        jLblTitle.setBounds(50, 10, 166, 30);

        jBttnUpdate.setText("Update");
        jBttnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBttnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(jBttnUpdate);
        jBttnUpdate.setBounds(110, 250, 72, 23);

        jTxtPuerto.setToolTipText("Puerto");
        jPanel1.add(jTxtPuerto);
        jTxtPuerto.setBounds(130, 200, 150, 40);

        jTxtCodigoServidor.setToolTipText("Código Servidor");
        jPanel1.add(jTxtCodigoServidor);
        jTxtCodigoServidor.setBounds(130, 50, 150, 40);

        jTxtIpDominio.setToolTipText("Ip o Dominio");
        jPanel1.add(jTxtIpDominio);
        jTxtIpDominio.setBounds(130, 100, 150, 40);

        jTxtSgbd.setToolTipText("Sgbd");
        jPanel1.add(jTxtSgbd);
        jTxtSgbd.setBounds(130, 150, 150, 40);

        jLblPerto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLblPerto.setForeground(new java.awt.Color(0, 0, 0));
        jLblPerto.setText("Puerto:");
        jPanel1.add(jLblPerto);
        jLblPerto.setBounds(80, 210, 50, 16);

        jLblCodigoServidor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLblCodigoServidor.setForeground(new java.awt.Color(0, 0, 0));
        jLblCodigoServidor.setText("Código Servidor:");
        jPanel1.add(jLblCodigoServidor);
        jLblCodigoServidor.setBounds(20, 60, 110, 20);

        jLblIpDominio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLblIpDominio.setForeground(new java.awt.Color(0, 0, 0));
        jLblIpDominio.setText("Ip o Dominio:");
        jPanel1.add(jLblIpDominio);
        jLblIpDominio.setBounds(40, 110, 90, 16);

        jLblSgbd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLblSgbd.setForeground(new java.awt.Color(0, 0, 0));
        jLblSgbd.setText("SGBD:");
        jPanel1.add(jLblSgbd);
        jLblSgbd.setBounds(80, 160, 60, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBttnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBttnUpdateActionPerformed
 
        if (jTxtCodigoServidor.getText().isEmpty() || jTxtIpDominio.getText().isEmpty() || 
            jTxtSgbd.getText().isEmpty() || jTxtPuerto.getText().isEmpty()) {
            
            javax.swing.JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return; 
        }

        try {

            String sgbd = jTxtSgbd.getText();
            String codigoServidor = jTxtCodigoServidor.getText();
            String ipDomini = jTxtIpDominio.getText();
            int puerto = Integer.parseInt(jTxtPuerto.getText());

            servidor = op.modificarConexionServidorDB(sgbd,ipDomini,puerto,codigoServidor);

            javax.swing.JOptionPane.showMessageDialog(this, "Servidor actualizado correctamente", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
        } catch (NumberFormatException e) {

            javax.swing.JOptionPane.showMessageDialog(this, "El puerto debe ser un número entero válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar la conexión en la base de datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (AppException ex) {
            Logger.getLogger(JDialogUpdateConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBttnUpdateActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDialogUpdateConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogUpdateConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogUpdateConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogUpdateConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogUpdateConnection dialog = new JDialogUpdateConnection(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBttnUpdate;
    private javax.swing.JLabel jLblCodigoServidor;
    private javax.swing.JLabel jLblIpDominio;
    private javax.swing.JLabel jLblPerto;
    private javax.swing.JLabel jLblSgbd;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtCodigoServidor;
    private javax.swing.JTextField jTxtIpDominio;
    private javax.swing.JTextField jTxtPuerto;
    private javax.swing.JTextField jTxtSgbd;
    // End of variables declaration//GEN-END:variables
}
