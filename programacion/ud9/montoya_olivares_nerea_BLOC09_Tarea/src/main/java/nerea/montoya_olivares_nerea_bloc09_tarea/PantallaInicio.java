package nerea.montoya_olivares_nerea_bloc09_tarea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Primer formulario que muestra una pantalla de introducción con una descripción de la aplicación, 
 * el nombre del autor y el botón para acceder a la pantalla de gestión de incidencias.
 *
 * @author Nerea Montoya
 */

public class PantallaInicio extends JFrame{

    public PantallaInicio() {
        setTitle("INCIDENCIAS NEREA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Aplicación de gestión de Incidencias");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea desc = new JTextArea("Aplicación que gestiona las incidencias organizandolas en prioridad alta y prioridad baja.");
        desc.setFont(new Font("Arial", Font.PLAIN, 14));
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setPreferredSize(new Dimension(400, 200));

        JLabel autor = new JLabel("Realizado por Nerea Montoya");
        autor.setFont(new Font("Arial", Font.ITALIC, 14));
        autor.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton bntGestionar = new JButton("GESTIONAR INCIDENCIAS");
        bntGestionar.setAlignmentX(Component.CENTER_ALIGNMENT);

        bntGestionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new GestionIncidencias(); 
            }
        });

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(desc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(autor);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(bntGestionar);

        setContentPane(new JLabel(new ImageIcon("background.jpg")));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaInicio();
            }
        });
    }
}
