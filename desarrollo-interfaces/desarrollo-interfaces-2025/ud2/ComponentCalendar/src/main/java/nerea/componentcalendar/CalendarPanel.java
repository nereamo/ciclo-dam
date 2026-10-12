/*
 */
package nerea.componentcalendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import nerea.DataAccess.DataAccess;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Nerea
 */
public class CalendarPanel extends JPanel {

    private int month;
    private int year;
    private Color activeButtonColor;
    private List<CalendarEventListener> listeners;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;

    // Constructor
    public CalendarPanel() {
        this.month = Calendar.getInstance().get(Calendar.MONTH);  // Mes actual
        this.year = Calendar.getInstance().get(Calendar.YEAR);  // Año actual
        this.activeButtonColor = new Color(144, 238, 144);  // Soft green as default
        this.listeners = new ArrayList<>();
        setLayout(new MigLayout("wrap 7", "[grow][grow][grow][grow][grow][grow][grow]"));

        // Inicializar los JComboBox
        initializeComboBoxes();
        initializeDayButtons();
        addSwipeListenerToButton(); 
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Color getActiveButtonColor() {
        return activeButtonColor;
    }

    public void setActiveButtonColor(Color activeButtonColor) {
        this.activeButtonColor = activeButtonColor;
    }

    // Método para inicializar los JComboBox (mes y año)
    private void initializeComboBoxes() {
        // Crear los JComboBox para mes y año
        monthComboBox = new JComboBox<>(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
        monthComboBox.setSelectedIndex(month);
        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                month = monthComboBox.getSelectedIndex();
                updateCalendar();
            }
        });

        yearComboBox = new JComboBox<>();
        for (int i = 1900; i <= 2100; i++) {
            yearComboBox.addItem(i);
        }
        yearComboBox.setSelectedItem(year);
        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year = (int) yearComboBox.getSelectedItem();
                updateCalendar();

            }
        });

        // Crear panel para los JComboBox y ponerlos encima del calendario
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  // Usar FlowLayout para centrar
        controlPanel.add(monthComboBox);
        controlPanel.add(yearComboBox);

        // Agregar el control panel y el label al panel principal
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(controlPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);  // Agregar los controles en la parte superior
    }

    private void updateCalendar() {
        // Limpiar el panel de calendario actual
        removeAll();
        // Volver a agregar el panel de control (mes y año)
        initializeComboBoxes();
        // Volver a generar los botones de los días para el nuevo mes
        initializeDayButtons();
        // Actualizar la interfaz
        revalidate();
        repaint();
    }

    private void initializeDayButtons() {

        for (int i = 1; i < getComponentCount(); i++) {
            try {
                if (getComponent(i) instanceof JPanel) {
                    remove(i);
                    i--; // Ajustar el índice después de remover un componente
                }
            } catch (Exception e) {
                // Captura y muestra el error si ocurre al remover el componente
                e.printStackTrace();
            }
        }

        // Obtener los días con entrenamientos asignados para el mes actual
        List<LocalDate> workoutDates = null;
        try {
            workoutDates = DataAccess.getWorkoutDatesForMonth(year, month + 1);  // Meses en SQL son 1-12
        } catch (Exception e) {
            e.printStackTrace();

        }

        // Generar el calendario
        Calendar cal = new GregorianCalendar(year, month, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        JPanel calendarPanel = new JPanel(new MigLayout("wrap 7, fill"));

        try {
            // Nombres de los días
            String[] dayNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            for (String dayName : dayNames) {
                calendarPanel.add(new JLabel(dayName, SwingConstants.CENTER), "width 100%");
            }

            // Rellenar con días vacíos antes del primer día del mes
            int emptyDaysBeforeFirst = (firstDayOfWeek == 1) ? 6 : firstDayOfWeek - 2;
            for (int i = 0; i < emptyDaysBeforeFirst; i++) {
                calendarPanel.add(new JLabel(""), "width 100%");
            }

            // Crear los botones para cada día
            for (int day = 1; day <= daysInMonth; day++) {
                final int finalDay = day;  // Captura la variable day como final o efectivamente final
                JButton dayButton = new JButton(String.valueOf(day));
                // Verificar si el día tiene entrenamientos asignados
                LocalDate currentDate = LocalDate.of(year, month + 1, day);
                int workoutsForDay = 0; // Variable para contar los entrenamientos

                if (workoutDates != null && workoutDates.contains(currentDate)) {
                    workoutsForDay = DataAccess.getWorkoutDetailsForDay(year, month + 1, finalDay).size();
                    dayButton.setBackground(activeButtonColor);  // Cambiar el color a verde si tiene entrenamientos
                }

                // Asignar el tooltip con la cantidad de entrenamientos
                dayButton.setToolTipText("Workouts: " + workoutsForDay);

                dayButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener los detalles de los workouts para el día seleccionado
                        List<Workout> workouts = DataAccess.getWorkoutDetailsForDay(year, month + 1, finalDay);

                        // Crear un evento para los listeners
                        WorkoutCalendarEvent event = new WorkoutCalendarEvent(finalDay, workouts);

                        if (!workouts.isEmpty()) {
                            // Crear un StringBuilder para concatenar la información de los entrenamientos
                            StringBuilder workoutDetails = new StringBuilder();
                            workoutDetails.append("Entrenamientos para el " + finalDay + "/" + (month + 1) + "/" + year + ":\n\n");
                            // Notificar a los listeners
                            notifyDaySelected(event);

                            // Agregar los detalles de cada workout utilizando el toString()
                            for (Workout workout : workouts) {
                                workoutDetails.append(workout.toString()).append("\n\n");
                            }
                        }
                    }
                });

                calendarPanel.add(dayButton, "grow, push");
            }

            // Agregar el panel de calendario
            add(calendarPanel, "span, grow");
            revalidate();
            repaint();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Refrescar el calendario según el mes y año actuales
    private void addSwipeListenerToButton() {
        this.addMouseListener(new MouseAdapter() {
            private Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();  // Capturar la posición inicial cuando se presiona el botón
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int distance = e.getPoint().y - startPoint.y;
                if (Math.abs(distance) > 50) {  // Si el deslizamiento es suficientemente grande
                    if (distance < 0) {
                        showNextMonth(); // Deslizar hacia abajo (siguiente mes)
                    } else {
                        showPreviousMonth(); // Deslizar hacia arriba (mes anterior)
                    }
                }
            }
        });
    }

    // Mostrar el siguiente mes
    private void showNextMonth() {
        month = (month + 1) % 12;
        if (month == 0) {
            year++;
        }
        monthComboBox.setSelectedIndex(month);
        yearComboBox.setSelectedItem(year);
        initializeDayButtons();

    }

    // Mostrar el mes anterior
    private void showPreviousMonth() {
        month = (month - 1 + 12) % 12;
        if (month == 11) {
            year--;
        }
        monthComboBox.setSelectedIndex(month);
        yearComboBox.setSelectedItem(year);
        initializeDayButtons();
    }

    // Add a listener for the day-clicked event
    public void addCalendarEventListener(CalendarEventListener listener) {
        listeners.add(listener);
    }

    private void notifyDaySelected(WorkoutCalendarEvent event) {
        for (CalendarEventListener listener : listeners) {
            listener.onDayClicked(event);
        }
    }
}
