package nerea.cat.paucasesnoves.ut6.practica.gui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import nerea.cat.paucasesnoves.ut6.practica.auxiliares.AplicacionException;
import nerea.cat.paucasesnoves.ut6.practica.component.Actor;
import nerea.cat.paucasesnoves.ut6.practica.component.Categoria;
import nerea.cat.paucasesnoves.ut6.practica.component.Film;
import nerea.cat.paucasesnoves.ut6.practica.component.MongoDB;

/**
 * Abre una ventana que permite seleccionar una categoría y/o un actor y muestra las películas asociadas.
 * 
 * @author Nerea Montoya
 */
public class gui extends javax.swing.JDialog {
    
    private List<Categoria> categoryList;
    private List<Actor> actorList;
    private int categoryIndex;
    private int actorIndex;

    public gui(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(600,500);
        setLocationRelativeTo(this);
        
        comboBoxCategory();
        comboBoxActor();
        actualizarPeliculas();
    }
    
    private void comboBoxCategory() {

        try {
            categoryList = MongoDB.getCategoria(); //Llamar al método getCategoria
            
            DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();
            
            dcm.addElement("Todas las categorías"); //Muestra un string en la primera posición
            
            for (Categoria cat : categoryList) {
                dcm.addElement(cat.getValue()); //Muestra el dato value (nombre de la categoría)
            }
            
            cmbBoxCategory.setModel(dcm);
            cmbBoxCategory.setSelectedIndex(0);
            
        } catch (AplicacionException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void comboBoxActor() {

        try {
            actorList = MongoDB.getActores();
            
            DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>(); //Llamar al método getActor
            
            dcm.addElement("Todos los actores"); //Muestra un string en la primera posición
            
            for (Actor cat : actorList) {
                dcm.addElement(cat.getFirstName() +" " + cat.getLastName());//Muestra el dato firstName y lastName (nombre del actor)
            }
            
            cmbBoxActor.setModel(dcm);
            cmbBoxActor.setSelectedIndex(0);
            
        } catch (AplicacionException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarPeliculas() {
        try {
            categoryIndex = cmbBoxCategory.getSelectedIndex();
            actorIndex = cmbBoxActor.getSelectedIndex();
            
            if (categoryIndex == 0 && actorIndex == 0) {
                txtAreaFilm.setText("Seleccione una categoría y/o un actor...");
                return;
            }

            //Si la categoría y/o el actor son seleccionado obtiene el id, de lo contrario, es null
            Object categoryId = (categoryIndex > 0) ? categoryList.get(categoryIndex - 1).getCategoryId() : null;
            Object actorId = (actorIndex > 0) ? actorList.get(actorIndex - 1).getActorId() : null;

            List<Film> pelis = MongoDB.getFilmCategoriaActor(categoryId, actorId);

            if (pelis.isEmpty()) {
                txtAreaFilm.setText("No se encontraron películas.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Film peli : pelis) {
                    
                    sb.append("Title: ").append(peli.getTitle()).append("\n");
                    sb.append("Description: ").append(peli.getDescription()).append("\n");
                    sb.append("Year: ").append(peli.getYear()).append("\n");
                    sb.append("Length: ").append(peli.getLength()).append(" min\n\n");
                    
                }
                txtAreaFilm.setText(sb.toString());
            }

        } catch (AplicacionException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
            txtAreaFilm.setText("Error al cargar películas.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGui = new javax.swing.JPanel();
        cmbBoxCategory = new javax.swing.JComboBox<>();
        cmbBoxActor = new javax.swing.JComboBox<>();
        lblCategory = new javax.swing.JLabel();
        lblActor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaFilm = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlGui.setBackground(new java.awt.Color(204, 204, 204));
        pnlGui.setLayout(null);

        cmbBoxCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxCategoryActionPerformed(evt);
            }
        });
        pnlGui.add(cmbBoxCategory);
        cmbBoxCategory.setBounds(130, 22, 400, 30);

        cmbBoxActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxActorActionPerformed(evt);
            }
        });
        pnlGui.add(cmbBoxActor);
        cmbBoxActor.setBounds(130, 60, 400, 30);

        lblCategory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(0, 0, 0));
        lblCategory.setText("Categoria:");
        pnlGui.add(lblCategory);
        lblCategory.setBounds(60, 30, 66, 17);

        lblActor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblActor.setForeground(new java.awt.Color(0, 0, 0));
        lblActor.setText("Actor:");
        pnlGui.add(lblActor);
        lblActor.setBounds(90, 70, 37, 17);

        txtAreaFilm.setColumns(20);
        txtAreaFilm.setRows(5);
        jScrollPane1.setViewportView(txtAreaFilm);

        pnlGui.add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 530, 330);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGui, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGui, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbBoxCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxCategoryActionPerformed

    actualizarPeliculas();
    }//GEN-LAST:event_cmbBoxCategoryActionPerformed

    private void cmbBoxActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxActorActionPerformed
 
        actualizarPeliculas();
    }//GEN-LAST:event_cmbBoxActorActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui dialog = new gui(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbBoxActor;
    private javax.swing.JComboBox<String> cmbBoxCategory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActor;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JPanel pnlGui;
    private javax.swing.JTextArea txtAreaFilm;
    // End of variables declaration//GEN-END:variables
}
