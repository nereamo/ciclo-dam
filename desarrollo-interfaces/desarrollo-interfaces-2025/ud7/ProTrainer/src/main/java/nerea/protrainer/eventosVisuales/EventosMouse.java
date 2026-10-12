package nerea.protrainer.eventosVisuales;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase EventosMouse que maneja eventos visuales cuando el cursor pasa por encima de un jButton, jList o jTable {@code EventosMouse}.
 * Configura cada componente con un elemento visual.
 * 
 * @author Nerea
 */
public class EventosMouse {
    
    /**
     * Constructor por defecto.
     */
    public EventosMouse(){
        
    }
    
    /**
     * Establece una configuración propia para los JButtons ubicados en el Main ProTrainer.
     * 
     * @param botones JButton para todos los botones del Main ProTrainer.
     */
    public static void resaltarBotonesMain(JButton... botones) {
        for (JButton boton : botones) {
            final Color colorOriginal = boton.getBackground();
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    boton.setBackground(new Color(51, 51, 51)); 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    boton.setBackground(colorOriginal); 
                }
            });
        }
    }
    
    /**
     * Establece una configuración propia para los JButtons ubicados en los JDialogs (Añadir-eliminar workouts  y añadir-eliminar-editar y asignar ejercicios).
     * 
     * @param botones JButton para todos los botones del package JDialogs.
     */
    public static void resaltarBotonesJDialog(JButton... botones) {
        for (JButton boton : botones) {
            final Color colorOriginal = boton.getBackground();
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    boton.setBackground(new Color(51, 51, 51)); 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    boton.setBackground(colorOriginal); 
                }
            });
        }
    }

    /**
     * Establece una configuración propia para los JButtons ubicados en el JPanel Menu.
     *
     * @param botones JButton para todos los botones del JPanel Menu.
     */
    public static void resaltarBotonesPnlMenu(JButton... botones) {
        for (JButton boton : botones) {
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    boton.setBackground(new Color(153, 204, 255)); 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    boton.setBackground(UIManager.getColor("Button.background")); 
                }
            });
        }
    }

    /**
     * Establece una configuración propia del cursor para las JTable.
     * Al seleccionar una fila, se muestra marcada con un fondo oscura y las letras en blanco.
     * @param tablas JTable para todas las tablas de la aplicación.
     */
    public static void cambiarCursorEnTablas(JTable... tablas) {
        final Point[] mousePos = {null};

        for (JTable tabla : tablas) {
            tabla.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mousePos[0] = e.getPoint();
                    tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    tabla.repaint();
                }
            });

            tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    mousePos[0] = null;
                    tabla.repaint();
                }
            });

            tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (isSelected) {
                        comp.setBackground(Color.DARK_GRAY);
                        comp.setForeground(Color.WHITE);
                    } else if (mousePos[0] != null) {
                        int hoverRow = table.rowAtPoint(mousePos[0]);
                        Rectangle rect = table.getCellRect(hoverRow, 0, true);

                        if (hoverRow == row && rect != null && rect.contains(mousePos[0])) {
                            comp.setBackground(Color.LIGHT_GRAY);
                            comp.setForeground(Color.BLACK);
                        } else {
                            comp.setBackground(table.getBackground());
                            comp.setForeground(table.getForeground());
                        }
                    } else {
                        comp.setBackground(table.getBackground());
                        comp.setForeground(table.getForeground());
                    }

                    return comp;
                }
            });

            tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = tabla.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        tabla.setRowSelectionInterval(row, row);
                        System.out.println("Fila seleccionada: " + row);
                    }
                }
            });
        }
    }

    /**
     * Establece una configuración propia del cursor para las JList.
     * 
     * @param list JList para todas las JList de la aplicación.
     */
    public static void cambiarCursorEnJList(JList<?> list) {
        final Point[] mousePos = {null};

        list.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePos[0] = e.getPoint();
                list.setCursor(new Cursor(Cursor.HAND_CURSOR));
                list.repaint();
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                mousePos[0] = null;
                list.repaint();
            }
        });

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(Color.DARK_GRAY);
                    c.setForeground(Color.WHITE); 
                } else if (mousePos[0] != null) {
                    int hoverIndex = list.locationToIndex(mousePos[0]);
                    Rectangle rect = list.getCellBounds(hoverIndex, hoverIndex);

                    if (hoverIndex == index && rect != null && rect.contains(mousePos[0])) {
                        c.setBackground(Color.LIGHT_GRAY);
                        c.setForeground(Color.BLACK); 
                    } else {
                        c.setBackground(list.getBackground());
                        c.setForeground(list.getForeground());
                    }
                } else {
                    c.setBackground(list.getBackground());
                    c.setForeground(list.getForeground());
                }

                return c;
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = list.locationToIndex(e.getPoint());
                if (selectedIndex > -1) {
                    System.out.println("Elemento seleccionado: " + list.getModel().getElementAt(selectedIndex));
                }
            }
        });
    }
}
