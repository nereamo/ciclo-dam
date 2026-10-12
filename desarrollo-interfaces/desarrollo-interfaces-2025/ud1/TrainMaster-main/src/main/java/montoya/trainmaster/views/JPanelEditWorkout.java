/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package montoya.trainmaster.views;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import montoya.trainmaster.Main;
import montoya.trainmaster.dto.Exercicis;
import montoya.trainmaster.dto.Workouts;

//Codigo adaptado de chatGPT, ayuda de compañeros.
/**
 *
 * @author Nerea
 */
public class JPanelEditWorkout extends javax.swing.JPanel {

    private Main jFrameMain;    // Referencia al JFrame principal 
    private List<Exercicis> allExercises = new ArrayList<>();
    private List<Workouts> allWorkouts = new ArrayList<>();

    public JPanelEditWorkout(Main jFrameMain) {
        initComponents();
        this.jFrameMain = jFrameMain;
        setSize(700, 500);

        cargarWorkoutsTabla();   //Llama al metodo que cargara el la Tabla con los workouts
        cargarEjerciciosTabla();    //Llama al metodo que cargara el la Tabla con los ejercicios
    }

    //Metodo que muestra los workouts de la DB en la jTableWorkouts
    private void cargarWorkoutsTabla() {

        allWorkouts = Workouts.getAllWorkouts(); //Obtener los workouts de la base de datos

        DefaultTableModel model = new DefaultTableModel();  //Modelo de tabla con las columnas adecuadas
        model.addColumn("ForDate");
        model.addColumn("Comments");

        for (Workouts workout : allWorkouts) {    //Rellenar la tabla con los workouts

            model.addRow(new Object[]{
                workout.getForDate(),
                workout.getComments(),
                workout.getId(),
                workout.getUserId()
            });
        }

        jTableWorkouts.setModel(model); //Modelo de la tabla
    }

    
    //Metodo que muestra los ejercicios de la DB en la jTableExercises
    private void cargarEjerciciosTabla() {

        allExercises = Exercicis.getAllExercises(); //Obtener los ejercicios de la base de datos

        DefaultTableModel model = new DefaultTableModel();  //Modelo de tabla con las columnas adecuadas
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("DemoFoto");

        for (Exercicis exercise : allExercises) {    //Rellenar la tabla con los ejercicios

            model.addRow(new Object[]{
                exercise.getNomExercici(),
                exercise.getDescripcio(),
                exercise.getDemoFoto(),
                exercise.getId(),});
        }

        jTableExercises.setModel(model); //Modelo de la tabla
    }

    
    //Metodo que actualiza las tablas
    private void actualizarTablas() {
        cargarEjerciciosTabla();
        cargarWorkoutsTabla();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelExercises = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableExercises = new javax.swing.JTable();
        jButtonEditExercise = new javax.swing.JButton();
        jButtonDeleteExercise = new javax.swing.JButton();
        jButtonAddExercise = new javax.swing.JButton();
        jButtonNewExercise = new javax.swing.JButton();
        jPanelWorkouts = new javax.swing.JPanel();
        jButtonCreateWorkout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableWorkouts = new javax.swing.JTable();
        jButtonDeleteWorkout = new javax.swing.JButton();
        jLabelWorkout = new javax.swing.JLabel();
        jLabelExercises = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 54, 91));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setLayout(null);

        jPanelExercises.setBackground(new java.awt.Color(0, 102, 153));
        jPanelExercises.setLayout(null);

        jTableExercises.setBackground(new java.awt.Color(204, 204, 204));
        jTableExercises.setForeground(new java.awt.Color(0, 51, 102));
        jTableExercises.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NomExercici", "Descripcio"
            }
        ));
        jScrollPane4.setViewportView(jTableExercises);

        jPanelExercises.add(jScrollPane4);
        jScrollPane4.setBounds(20, 20, 430, 130);

        jButtonEditExercise.setBackground(new java.awt.Color(0, 51, 102));
        jButtonEditExercise.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditExercise.setText("Edit");
        jButtonEditExercise.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonEditExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditExerciseActionPerformed(evt);
            }
        });
        jPanelExercises.add(jButtonEditExercise);
        jButtonEditExercise.setBounds(500, 90, 90, 18);

        jButtonDeleteExercise.setBackground(new java.awt.Color(0, 51, 102));
        jButtonDeleteExercise.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDeleteExercise.setText("Delete");
        jButtonDeleteExercise.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonDeleteExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteExerciseActionPerformed(evt);
            }
        });
        jPanelExercises.add(jButtonDeleteExercise);
        jButtonDeleteExercise.setBounds(500, 120, 90, 18);

        jButtonAddExercise.setBackground(new java.awt.Color(0, 51, 102));
        jButtonAddExercise.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddExercise.setText("Add");
        jButtonAddExercise.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonAddExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddExerciseActionPerformed(evt);
            }
        });
        jPanelExercises.add(jButtonAddExercise);
        jButtonAddExercise.setBounds(500, 60, 90, 18);

        jButtonNewExercise.setBackground(new java.awt.Color(0, 51, 102));
        jButtonNewExercise.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNewExercise.setText("New");
        jButtonNewExercise.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonNewExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewExerciseActionPerformed(evt);
            }
        });
        jPanelExercises.add(jButtonNewExercise);
        jButtonNewExercise.setBounds(500, 30, 90, 18);

        add(jPanelExercises);
        jPanelExercises.setBounds(20, 250, 640, 180);

        jPanelWorkouts.setBackground(new java.awt.Color(0, 102, 153));
        jPanelWorkouts.setLayout(null);

        jButtonCreateWorkout.setBackground(new java.awt.Color(0, 51, 102));
        jButtonCreateWorkout.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCreateWorkout.setText("Create");
        jButtonCreateWorkout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonCreateWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateWorkoutActionPerformed(evt);
            }
        });
        jPanelWorkouts.add(jButtonCreateWorkout);
        jButtonCreateWorkout.setBounds(500, 60, 90, 18);

        jTableWorkouts.setBackground(new java.awt.Color(204, 204, 204));
        jTableWorkouts.setForeground(new java.awt.Color(0, 51, 102));
        jTableWorkouts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ForDate", "Comments"
            }
        ));
        jScrollPane1.setViewportView(jTableWorkouts);

        jPanelWorkouts.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 430, 130);

        jButtonDeleteWorkout.setBackground(new java.awt.Color(0, 51, 102));
        jButtonDeleteWorkout.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDeleteWorkout.setText("Delete");
        jButtonDeleteWorkout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonDeleteWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteWorkoutActionPerformed(evt);
            }
        });
        jPanelWorkouts.add(jButtonDeleteWorkout);
        jButtonDeleteWorkout.setBounds(500, 100, 90, 18);

        add(jPanelWorkouts);
        jPanelWorkouts.setBounds(20, 40, 640, 170);

        jLabelWorkout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelWorkout.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWorkout.setText("Workouts");
        add(jLabelWorkout);
        jLabelWorkout.setBounds(20, 10, 130, 25);

        jLabelExercises.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelExercises.setForeground(new java.awt.Color(255, 255, 255));
        jLabelExercises.setText("Exercises");
        add(jLabelExercises);
        jLabelExercises.setBounds(20, 220, 160, 25);
    }// </editor-fold>//GEN-END:initComponents

    //Boton que abre el jDialogCreateworkout
    private void jButtonCreateWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateWorkoutActionPerformed
        try {
            //Abre el JDialog para crear un nuevo workout
            DialogCreateWorkout dialog = new DialogCreateWorkout(jFrameMain, true);
            dialog.setVisible(true); // Muestra el JDialog
            actualizarTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonCreateWorkoutActionPerformed

    //Al seleccionar un workout de la tabla, permite eliminar el workout si no tiene ejercicios asociados.
    private void jButtonDeleteWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteWorkoutActionPerformed

        // Obtener la fila seleccionada en la tabla
        int selectedRow = jTableWorkouts.getSelectedRow();

        // Verificar si se ha seleccionado una fila
        if (selectedRow != -1) {
            // Obtener el ID del workout seleccionado, asumiendo que el ID es el primer valor de la fila
            Workouts selectedWorkout = allWorkouts.get(selectedRow);  // Aquí debes adaptar a tu modelo de datos

            // Confirmar la eliminación
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Estas seguro de eliminar el Workout?",
                    "Confirm Deletion",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            // Si el usuario confirma, eliminar el workout
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                // Llamar al método de eliminación del workout en la base de datos
                boolean success = Workouts.deleteWorkout(selectedWorkout.getId());  // Aquí debes adaptar según tu modelo

                if (success) {
                    // Si la eliminación fue exitosa, eliminarlo de la lista local
                    allWorkouts.remove(selectedRow);

                    // Actualizar la tabla para reflejar los cambios
                    actualizarTablas();

                    // Mostrar mensaje de éxito
                    javax.swing.JOptionPane.showMessageDialog(this, "Workout eliminado correctamente!");
                } else {
                    // Si hubo un error al eliminar
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar el Workout.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Si no hay fila seleccionada, mostrar un mensaje
            javax.swing.JOptionPane.showMessageDialog(this, "Porfavor, selecciona un Workout de la tabla.", "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDeleteWorkoutActionPerformed

    //Boton que abre el jDialogNewExercise
    private void jButtonNewExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewExerciseActionPerformed
        try {
            //Abre el JDialog para añadir un nuevo ejercicio
            DialogNewExercise dialog = new DialogNewExercise(jFrameMain, true);
            dialog.setVisible(true);  // Muestra el JDialog
            actualizarTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonNewExerciseActionPerformed

    //Al seleccionar un ejercicio que no este en ningun workout permitirá eliminarlo
    private void jButtonDeleteExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteExerciseActionPerformed

        // Obtener la fila seleccionada en la tabla
        int selectedRow = jTableExercises.getSelectedRow();

        // Verificar si se ha seleccionado una fila
        if (selectedRow != -1) {
            // Obtener el ID del workout seleccionado, asumiendo que el ID es el primer valor de la fila
            Exercicis selectedExercise = allExercises.get(selectedRow);  // Aquí debes adaptar a tu modelo de datos

            // Confirmar la eliminación
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Estas seguro de eliminar el Ejercicio?",
                    "Confirm Deletion",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            // Si el usuario confirma, eliminar el workout
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                // Llamar al método de eliminación del workout en la base de datos
                boolean success = Exercicis.deleteExercise(selectedExercise.getId());  // Aquí debes adaptar según tu modelo

                if (success) {
                    // Si la eliminación fue exitosa, eliminarlo de la lista local
                    allExercises.remove(selectedRow);

                    // Actualizar la tabla para reflejar los cambios
                    actualizarTablas();

                    // Mostrar mensaje de éxito
                    javax.swing.JOptionPane.showMessageDialog(this, "Ejercicio eliminado correctamente!");
                } else {
                    // Si hubo un error al eliminar
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar el ejercicio.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Si no hay fila seleccionada, mostrar un mensaje
            javax.swing.JOptionPane.showMessageDialog(this, "Porfavor, selecciona un ejercicio de la tabla.", "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDeleteExerciseActionPerformed

    //Boton que abre el jDialogAddExercise
    private void jButtonAddExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddExerciseActionPerformed
        try {
            //Abre el JDialog para añadir un nuevo ejercicio
            DialogAddExerciseInWorkout dialog = new DialogAddExerciseInWorkout(jFrameMain, true);
            dialog.setVisible(true);  //Muestra el JDialog
            actualizarTablas();
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    }//GEN-LAST:event_jButtonAddExerciseActionPerformed
    //Boton que abre el jDialogEditExercise
    private void jButtonEditExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditExerciseActionPerformed
        // Verifica si hay un ejercicio seleccionado en la tabla
        int selectedRow = jTableExercises.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un ejercicio.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtiene el ejercicio seleccionado de la lista 'allExercises'
        Exercicis selectedExercise = allExercises.get(selectedRow);

        // Abre el JDialog para editar el ejercicio
        DialogModifyExercise dialog = new DialogModifyExercise(jFrameMain, true, selectedExercise);
        dialog.setVisible(true); // Muestra el JDialog 

        // Actualiza las tablas después de editar
        actualizarTablas();

    }//GEN-LAST:event_jButtonEditExerciseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddExercise;
    private javax.swing.JButton jButtonCreateWorkout;
    private javax.swing.JButton jButtonDeleteExercise;
    private javax.swing.JButton jButtonDeleteWorkout;
    private javax.swing.JButton jButtonEditExercise;
    private javax.swing.JButton jButtonNewExercise;
    private javax.swing.JLabel jLabelExercises;
    private javax.swing.JLabel jLabelWorkout;
    private javax.swing.JPanel jPanelExercises;
    private javax.swing.JPanel jPanelWorkouts;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableExercises;
    private javax.swing.JTable jTableWorkouts;
    // End of variables declaration//GEN-END:variables
}
