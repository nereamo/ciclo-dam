package nerea.protrainer.views;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import nerea.protrainer.jDialogs.AsignarEjercicio;
import nerea.protrainer.jDialogs.AñadirNuevoEjercicio;
import nerea.protrainer.jDialogs.AñadirNuevoEntrenamiento;
import nerea.protrainer.jDialogs.EliminarEjercicio;
import nerea.protrainer.jDialogs.EliminarEntrenamiento;
import nerea.protrainer.jDialogs.ModificarEjercicio;
import nerea.protrainer.ProTrainer;
import nerea.protrainer.dao.ExercicisWorkoutsDAO;
import nerea.protrainer.dao.UsuarisDAO;
import nerea.protrainer.dao.WorkoutsDAO;
import nerea.protrainer.dto.Exercicis;
import nerea.protrainer.dto.ExercicisTableModel;
import nerea.protrainer.dto.Usuari;
import nerea.protrainer.dto.Workouts;
import nerea.protrainer.dto.WorkoutsTableModel;
import static nerea.protrainer.eventosVisuales.EventosMouse.cambiarCursorEnJList;
import net.miginfocom.swing.MigLayout;
import static nerea.protrainer.eventosVisuales.EventosMouse.cambiarCursorEnTablas;
import static nerea.protrainer.eventosVisuales.EventosMouse.resaltarBotonesPnlMenu;

/**
 * Clase PanelMenu maneja las operaciones con la base de datos relacionado con usuarios, Workouts y Exercicis {@code PanelMenu}.
 * La clase extiende {@code javax.swing.JPanel}, permite gestionar la interfaz de usuario.
 *
 * @author Nerea
 */
public class PanelMenu extends javax.swing.JPanel {

    /**
     * Variables utilizadas en la gestión del menú principal de la aplicación.
     * <p>
     * - `frameMain` almacena una referencia al JFrame principal de la aplicación. 
     * - `usuariosList` almacena la lista de usuarios disponibles. 
     * - `workoutList` almacena la lista de workouts disponibles. 
     * - `ejerciciosList` almacena la lista de ejercicios disponibles.
     */
    private ProTrainer frameMain;
    private List<Usuari> usuariosList = new ArrayList<>();
    private List<Integer> workoutList = new ArrayList<>();
    private List<Integer> ejerciciosList = new ArrayList<>();
    
    
    /**
     * Constructor que inicializa la lista con los usuarios asignados al instructor, establece el 'MigLayout' para ordenar los componentes e inicializa los métodos cambiarCursorEnTablas, cambiarCursorEnJList y resaltarBotones para propagar los eventos en los componentes.
     * 
     * @param frameMain Referencia a la ventana principal.
     */
    public PanelMenu(ProTrainer frameMain) {
        
        initComponents();
        setSize(1200, 660);

        this.frameMain = frameMain;

        frameMain.showCalendarMenu();
        listaUsuarios();
       
        /**
         * Configura el diseño del panel utilizando MigLayout
         */
        setLayout(new MigLayout("fill, insets 10", "[grow 30][grow 20][grow 10][grow 10][grow 10][grow 5][grow 5][grow 5][grow 5]", "[][][][]")); // 6 columnas
        
        //Panel DarkGrey con lista de usuarios y el jscroll de la lista de usuarios.
        pnlDarkGrey.setLayout(new MigLayout("fill, insets 10", "[grow]", "[grow]"));
        pnlDarkGrey.add(lblUsuarioIcon, "align center, wrap");
        pnlDarkGrey.add(scrlLstUsuarios, "grow, align center");

        add(pnlDarkGrey, "grow, cell 0 0 3 5"); 
        add(lblWorkouts, "cell 3 0, wrap"); 
        add(scrlTblWorkouts, "grow, cell 3 1 8 1"); 
        add(btnNuevoWorkout, "cell 7 0"); 
        add(btnEliminarWorkout, "cell 8 0");
        
        add(lblEjercicios, "cell 3 2, wrap"); 
        add(scrlTblEjercicios, "grow, cell 3 3 8 3"); 
        add(btnNuevoEjercicio,"cell 5 2");
        add(btnEliminarEjercicio,"cell 6 2");
        add(btnEditarEjercicio,"cell 7 2");
        add(btnAsignarEjercicios, "cell 8 2");
        
        cambiarCursorEnTablas(tblEntrenamientos,tblEjercicios);
        cambiarCursorEnJList(lstUsuarios);
        resaltarBotonesPnlMenu(btnNuevoWorkout, btnEliminarWorkout, btnNuevoEjercicio, btnEliminarEjercicio, btnEditarEjercicio, btnAsignarEjercicios);
    }

    /**
     * Obtiene la lista de usuarios asignados a un instructor y los muestra en la JList.
     */
    private void listaUsuarios() {

        usuariosList = UsuarisDAO.usuariosAsignadosInstructor();

        DefaultListModel<Usuari> listModel = new DefaultListModel<>();

        for (Usuari user : usuariosList) {
            listModel.addElement(user);
        }

        lstUsuarios.setModel(listModel);
        lstUsuarios.revalidate();
        lstUsuarios.repaint();
    }

    /**
     * Método que muestra los entrenamientos asignados a un usuario en la tabla workouts.
     * 
     * @param userId ID del usuario seleccionado.
     */
    private void tblWorkoutsUsuario(int userId) {

        try {
            ArrayList<Workouts> workouts = WorkoutsDAO.workoutUsuari(userId);

            workoutList.clear();
            for (Workouts w : workouts) {
                workoutList.add(w.getId());
            }

            WorkoutsTableModel wtm = new WorkoutsTableModel(workouts);
            tblEntrenamientos.setModel(wtm);
            tblEntrenamientos.setRowHeight(25);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que muestra los ejercicios asignados a un entrenamiento en la tabla ejercicios.
     * 
     * @param workoutId ID del entrenamiento seleccionado.
     */
    private void tblEjerciciosUsuario(int workoutId) {

        try {
            ArrayList<Exercicis> ejercicios = ExercicisWorkoutsDAO.ejerciciosDelWorkout(workoutId);
            
            ExercicisTableModel etm = new ExercicisTableModel(ejercicios);
            tblEjercicios.setModel(etm);
            tblEjercicios.setRowHeight(25);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que actualiza las tablas de entrenamientos y ejercicios según el usuario y workout seleccionados.
     * 
     * @param userId ID del usuario seleccionado.
     * @param workoutId ID del entrenamiento seleccionado.
     */
    public void actualizarTablas(int userId, int workoutId) {
        tblWorkoutsUsuario(userId);
        tblEjerciciosUsuario(workoutId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWorkouts = new javax.swing.JLabel();
        scrlTblWorkouts = new javax.swing.JScrollPane();
        tblEntrenamientos = new javax.swing.JTable();
        btnNuevoWorkout = new javax.swing.JButton();
        btnEliminarWorkout = new javax.swing.JButton();
        btnAsignarEjercicios = new javax.swing.JButton();
        btnEliminarEjercicio = new javax.swing.JButton();
        btnEditarEjercicio = new javax.swing.JButton();
        lblEjercicios = new javax.swing.JLabel();
        pnlDarkGrey = new javax.swing.JPanel();
        btnNuevoEjercicio = new javax.swing.JButton();
        scrlLstUsuarios = new javax.swing.JScrollPane();
        lstUsuarios = new javax.swing.JList<>();
        lblUsuarioIcon = new javax.swing.JLabel();
        scrlTblEjercicios = new javax.swing.JScrollPane();
        tblEjercicios = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setLayout(null);

        lblWorkouts.setFont(new java.awt.Font("Anton", 0, 24)); // NOI18N
        lblWorkouts.setForeground(new java.awt.Color(0, 0, 0));
        lblWorkouts.setText("Entrenamientos");
        add(lblWorkouts);
        lblWorkouts.setBounds(500, 40, 220, 40);

        tblEntrenamientos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblEntrenamientos.setForeground(new java.awt.Color(0, 0, 0));
        tblEntrenamientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Fecha", "Comentario"
            }
        ));
        tblEntrenamientos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblEntrenamientos.setMinimumSize(new java.awt.Dimension(150, 300));
        tblEntrenamientos.setRowHeight(25);
        tblEntrenamientos.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblEntrenamientos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblEntrenamientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntrenamientosMouseClicked(evt);
            }
        });
        scrlTblWorkouts.setViewportView(tblEntrenamientos);

        add(scrlTblWorkouts);
        scrlTblWorkouts.setBounds(500, 80, 420, 190);

        btnNuevoWorkout.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevoWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Añadir.png"))); // NOI18N
        btnNuevoWorkout.setToolTipText("Nuevo entrenamiento");
        btnNuevoWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoWorkoutActionPerformed(evt);
            }
        });
        add(btnNuevoWorkout);
        btnNuevoWorkout.setBounds(850, 40, 40, 40);

        btnEliminarWorkout.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarWorkout.setForeground(new java.awt.Color(204, 204, 204));
        btnEliminarWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Borrar.png"))); // NOI18N
        btnEliminarWorkout.setToolTipText("Eliminar entrenamiento");
        btnEliminarWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarWorkoutActionPerformed(evt);
            }
        });
        add(btnEliminarWorkout);
        btnEliminarWorkout.setBounds(890, 40, 40, 40);

        btnAsignarEjercicios.setBackground(new java.awt.Color(204, 204, 204));
        btnAsignarEjercicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Asignar.png"))); // NOI18N
        btnAsignarEjercicios.setToolTipText("Asignar ejercicio");
        btnAsignarEjercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarEjerciciosActionPerformed(evt);
            }
        });
        add(btnAsignarEjercicios);
        btnAsignarEjercicios.setBounds(890, 330, 40, 40);

        btnEliminarEjercicio.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarEjercicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Borrar.png"))); // NOI18N
        btnEliminarEjercicio.setToolTipText("Eliminar ejercicio");
        btnEliminarEjercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEjercicioActionPerformed(evt);
            }
        });
        add(btnEliminarEjercicio);
        btnEliminarEjercicio.setBounds(810, 330, 40, 40);

        btnEditarEjercicio.setBackground(new java.awt.Color(204, 204, 204));
        btnEditarEjercicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Editar.png"))); // NOI18N
        btnEditarEjercicio.setToolTipText("Editar ejercicio");
        btnEditarEjercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEjercicioActionPerformed(evt);
            }
        });
        add(btnEditarEjercicio);
        btnEditarEjercicio.setBounds(850, 330, 40, 40);

        lblEjercicios.setFont(new java.awt.Font("Anton", 0, 24)); // NOI18N
        lblEjercicios.setForeground(new java.awt.Color(0, 0, 0));
        lblEjercicios.setText("Ejercicios");
        add(lblEjercicios);
        lblEjercicios.setBounds(500, 320, 170, 40);

        pnlDarkGrey.setBackground(new java.awt.Color(51, 51, 51));
        pnlDarkGrey.setLayout(null);
        add(pnlDarkGrey);
        pnlDarkGrey.setBounds(0, 0, 410, 700);

        btnNuevoEjercicio.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevoEjercicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Añadir.png"))); // NOI18N
        btnNuevoEjercicio.setToolTipText("Nuevo ejercicio");
        btnNuevoEjercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoEjercicioActionPerformed(evt);
            }
        });
        add(btnNuevoEjercicio);
        btnNuevoEjercicio.setBounds(780, 330, 40, 40);

        scrlLstUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        scrlLstUsuarios.setForeground(new java.awt.Color(0, 0, 0));

        lstUsuarios.setFont(new java.awt.Font("Anton", 0, 18)); // NOI18N
        lstUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        lstUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstUsuarios.setSelectionBackground(new java.awt.Color(51, 51, 51));
        lstUsuarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        lstUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstUsuariosMouseClicked(evt);
            }
        });
        scrlLstUsuarios.setViewportView(lstUsuarios);

        add(scrlLstUsuarios);
        scrlLstUsuarios.setBounds(110, 260, 190, 260);

        lblUsuarioIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Icon Usuario.png"))); // NOI18N
        add(lblUsuarioIcon);
        lblUsuarioIcon.setBounds(150, 80, 110, 100);

        tblEjercicios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblEjercicios.setForeground(new java.awt.Color(0, 0, 0));
        tblEjercicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Descripción", "Imagen"
            }
        ));
        tblEjercicios.setMinimumSize(new java.awt.Dimension(150, 300));
        tblEjercicios.setPreferredSize(new java.awt.Dimension(150, 200));
        tblEjercicios.setRowHeight(25);
        tblEjercicios.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblEjercicios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblEjercicios.getTableHeader().setResizingAllowed(false);
        tblEjercicios.getTableHeader().setReorderingAllowed(false);
        scrlTblEjercicios.setViewportView(tblEjercicios);

        add(scrlTblEjercicios);
        scrlTblEjercicios.setBounds(500, 370, 420, 190);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que obtiene el ID del usuario seleccionado de la lista usuarios para mostrar los entrenamientos asignados a éste en la tabla.
     * 
     * @param evt Evento de acción activa la ejecución del método.
     */
    private void lstUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstUsuariosMouseClicked
        int selectIndex = lstUsuarios.getSelectedIndex();

        if (selectIndex >= 0 && selectIndex < usuariosList.size()) {
            Usuari selectUsuario = usuariosList.get(selectIndex);

            tblWorkoutsUsuario(selectUsuario.getId());

        }
    }//GEN-LAST:event_lstUsuariosMouseClicked

    /**
     * Abre un JDialog para añadir un nuevo entrenamiento y actualiza la tabla de entrenamientos.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void btnNuevoWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoWorkoutActionPerformed
        try {
            AñadirNuevoEntrenamiento añadirEntrenamiento = new AñadirNuevoEntrenamiento(frameMain, true);
            añadirEntrenamiento.setVisible(true); 

            int selectIndex = lstUsuarios.getSelectedIndex();
            if (selectIndex >= 0) {
                int usuarioId = usuariosList.get(selectIndex).getId();

                actualizarTablas(usuarioId, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNuevoWorkoutActionPerformed

    /**
     * Abre un JDialog para eliminar un entrenamiento y actualiza la tabla de entrenamientos.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void btnEliminarWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarWorkoutActionPerformed
        try {
            EliminarEntrenamiento eliminarEntrenamiento = new EliminarEntrenamiento(frameMain, true);
            eliminarEntrenamiento.setVisible(true);

            int selectIndex = lstUsuarios.getSelectedIndex();
            if (selectIndex >= 0) {
                int usuarioId = usuariosList.get(selectIndex).getId();

                actualizarTablas(usuarioId, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarWorkoutActionPerformed

    /**
     * Abre un JDialog para añadir un nuevo ejercicio y actualiza la tabla de ejercicios.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void btnNuevoEjercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoEjercicioActionPerformed
        try {
            AñadirNuevoEjercicio añadirEjercicio = new AñadirNuevoEjercicio(frameMain, true);
            añadirEjercicio.setVisible(true);

            int selectIndex = lstUsuarios.getSelectedIndex();
            if (selectIndex >= 0) {
                int usuarioId = usuariosList.get(selectIndex).getId();

                actualizarTablas(usuarioId, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNuevoEjercicioActionPerformed

    /**
     * Abre un JDialog para eliminar un ejercicio y actualiza la tabla de ejercicios.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void btnEliminarEjercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEjercicioActionPerformed
        try {
            EliminarEjercicio eliminarEjercicio = new EliminarEjercicio(frameMain, true);
            eliminarEjercicio.setVisible(true);

            int selectIndex = lstUsuarios.getSelectedIndex();
            if (selectIndex >= 0) {
                int usuarioId = usuariosList.get(selectIndex).getId();

                actualizarTablas(usuarioId, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarEjercicioActionPerformed

    /**
     * Abre un JDialog para editar un ejercicio y actualiza la tabla de ejercicios.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void btnEditarEjercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEjercicioActionPerformed
        try {
            ModificarEjercicio modificarEjercicio = new ModificarEjercicio(frameMain, true);
            modificarEjercicio.setVisible(true);

            int selectIndex = lstUsuarios.getSelectedIndex();
            if (selectIndex >= 0) {
                int usuarioId = usuariosList.get(selectIndex).getId();

                actualizarTablas(usuarioId, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditarEjercicioActionPerformed

    /**
     * Abre un JDialog para asignar un ejercicio a un workout y actualiza la tabla de ejercicios.
     *
     * @param evt Evento de acción generado por el botón.
     */
    private void btnAsignarEjerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEjerciciosActionPerformed
        try {
            AsignarEjercicio asignarEjercicio = new AsignarEjercicio(frameMain, true);
            asignarEjercicio.setVisible(true);

            int selectIndex = lstUsuarios.getSelectedIndex();
            if (selectIndex >= 0) {
                int usuarioId = usuariosList.get(selectIndex).getId();

                actualizarTablas(usuarioId, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAsignarEjerciciosActionPerformed

    /**
     * Maneja el evento de clic en la tabla de entrenamientos. 
     * Ak hacer clic en una fila de `tblEntrenamientos`, obtiene ID del entrenamiento seleccionado y actualiza la tabla de ejercicios asociados a ese entrenamiento.
     *
     * @param evt Evento de clic del ratón.
     */
    private void tblEntrenamientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntrenamientosMouseClicked
        int selectRow = tblEntrenamientos.getSelectedRow();

        if (selectRow >= 0 && selectRow < workoutList.size()) {

            int workoutId = workoutList.get(selectRow);

            tblEjerciciosUsuario(workoutId);
        }
    }//GEN-LAST:event_tblEntrenamientosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarEjercicios;
    private javax.swing.JButton btnEditarEjercicio;
    private javax.swing.JButton btnEliminarEjercicio;
    private javax.swing.JButton btnEliminarWorkout;
    private javax.swing.JButton btnNuevoEjercicio;
    private javax.swing.JButton btnNuevoWorkout;
    private javax.swing.JLabel lblEjercicios;
    private javax.swing.JLabel lblUsuarioIcon;
    private javax.swing.JLabel lblWorkouts;
    private javax.swing.JList<Usuari> lstUsuarios;
    private javax.swing.JPanel pnlDarkGrey;
    private javax.swing.JScrollPane scrlLstUsuarios;
    private javax.swing.JScrollPane scrlTblEjercicios;
    private javax.swing.JScrollPane scrlTblWorkouts;
    private javax.swing.JTable tblEjercicios;
    private javax.swing.JTable tblEntrenamientos;
    // End of variables declaration//GEN-END:variables
}
