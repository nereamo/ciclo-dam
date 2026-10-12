package nerea.protrainer.views;

import javax.swing.JOptionPane;
import nerea.componentcalendar.CalendarEventListener;
import nerea.componentcalendar.WorkoutCalendarEvent;

/**
 * Clase DialogCalendar muestra un calendario con los dias marcados si hay un workout {@code DialogCalendar}.
 * La clase extiende {@code javax.swing.JDialog}, permite que esta clase sea un diálogo modal.
 * 
 * @author Nerea
 */
public class DialogCalendar extends javax.swing.JDialog {
    
    /**
     * Constructor que inicializa el componente del calendario.
     * 
     * @param parent JFrame principal (ventana padre) del JDiálog.
     * @param modal Si es {@code true}, el foco se mantiene en el JDiálog hasta que se cierra.
     */
    public DialogCalendar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(580, 320);
        setLocationRelativeTo(this);

        calendarPanel1.addCalendarEventListener(new CalendarEventListener() {
            @Override
            public void onDayClicked(WorkoutCalendarEvent event) {
                JOptionPane.showMessageDialog(null, event.toString(), "Información del Entrenamiento", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        calendarPanel1 = new nerea.componentcalendar.CalendarPanel();
        jLblMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 269));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(580, 320));
        jPanel1.setPreferredSize(new java.awt.Dimension(580, 320));
        jPanel1.setLayout(null);

        calendarPanel1.setActiveButtonColor(new java.awt.Color(153, 204, 255));
        calendarPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.add(calendarPanel1);
        calendarPanel1.setBounds(0, 0, 580, 280);

        jLblMsg.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLblMsg);
        jLblMsg.setBounds(0, 280, 580, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 580, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private nerea.componentcalendar.CalendarPanel calendarPanel1;
    private javax.swing.JLabel jLblMsg;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
