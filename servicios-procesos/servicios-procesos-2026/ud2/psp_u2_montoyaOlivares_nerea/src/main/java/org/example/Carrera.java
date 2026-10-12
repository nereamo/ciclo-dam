package org.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Clase Carrera encargada de la representación visual.
 * Mostrar mensajes de eventos, obtener los tres primeros en llegar y la clasificación final
 */

public class Carrera {

    //==== Variables ====
    public Semaphore comedor = new Semaphore(1);
    private List<Caballos> clasificacion = new ArrayList<>();
    private List<Caballos> caballosList = new ArrayList<>();
    private int distancia;
    private boolean pausa = false;

    //==== Constructor ====
    public Carrera(int numCaballo, int distancia){

        this.distancia = distancia;
        iniciarCaballos(numCaballo);
    }

    //==== Métodos sincronizados de pausa y reinicio de los hilos ====
    public synchronized boolean isPausa() {
        return pausa;
    }

    public synchronized void pausarCaballos(){ //Activar pausa
        pausa = true;
    }

    public synchronized void reiniciarCaballos(){ //Desactivar pausa
        pausa = false;
        notifyAll();
    }

    //Crea tantos caballos como se indique desde el main
    public void iniciarCaballos(int numCaballos) {
        for (int i = 0; i < numCaballos; i++) {
            Caballos caballo = new Caballos(i + 1, distancia);
            caballo.setName(String.valueOf(i + 1));
            caballo.setCarrera(this);
            caballo.start();
            caballosList.add(caballo);
        }
    }

    //==== Formato de carrera (representación visual) ====
    public void formatoCarrera(){

        final int LONGITUD_PISTA = 70; //Longitud pista
        final long INTERVALO_ACTUALIZACION = 1000; //Cada 1s se actualiza
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String finalCaballo = "......................................................................X";

        while (clasificacion.size() + contarAbandonados() < caballosList.size()) {
            Date date = new Date();

            //Cada 1s muestra la hora y la distancia
            System.out.println("\n--- [Estado: " + dateFormat.format(date) + " | Distancia: " + distancia + "m] ---");

            //Obtención de los datos del caballo (distancia recorrida, nombre y velocidad)
            for (Caballos caballo : caballosList) {

                int posicionActual = caballo.getDistanciaRecorrida();
                String nombre = "Caballo " + caballo.getName();
                int velocidad = caballo.getVelocidadActual();

                //Forma en la que se muestran los mensajes de evento de los hilos
                String evento = caballo.getAndClearMensajeEvento();
                String notificacion = evento.isEmpty() ? "" : " -> " + evento;

                //Si el caballo abandonó muestra ABANDONO
                if (caballo.isAbandono()) {
                    System.out.printf("%-10s %-75s %s\n", nombre, finalCaballo, "ABANDONO");
                    continue;
                }

                //si el caballo llegó a meta muestra FINALIZADO
                if (clasificacion.contains(caballo)) {
                    System.out.printf("%-10s %-75s %s\n", nombre, finalCaballo, "FINALIZADO");
                    continue;
                }

                //Cálculo del porcentaje de avance respecto la longitud de la pista
                double porcentaje = (double) posicionActual / distancia;
                int avance = (int) (porcentaje * LONGITUD_PISTA);
                avance = Math.min(avance, LONGITUD_PISTA);

                //Construcción de la carrera, el caballo es representado con ">"
                String pista = ".".repeat(avance) + ">" + ".".repeat(LONGITUD_PISTA - avance);

                //Muestra el estado del caballo en carrera
                System.out.printf("%-10s %s %d km/h %s\n", nombre, pista, velocidad, notificacion);
            }

            try {
                Thread.sleep(INTERVALO_ACTUALIZACION); //Espera de 1s entre actualizaciones
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            //Si la carrera está pausada espera hasta la reanudación
            while (isPausa()) {
                if (isPausa()) {
                    return;
                }
            }
        }
        System.out.println("\n¡CARRERA FINALIZADA!");
        mostrarClasificacionFinal();
    }

    //Cuenta los caballos que han abandonado
    private int contarAbandonados() {
        int count = 0;
        for (Caballos c : caballosList) {
            if (c.isAbandono()) {
                count++;
            }
        }
        return count;
    }

    //Añade los caballos que finalizan sin contar los que han abandonado
    public synchronized void registrarLlegada(Caballos caballo) {

        if (!caballo.isAbandono() && clasificacion.add(caballo) && clasificacion.size() == 3) {
            tresPrimeros();
        }
    }

    //Muestra los tres primeros caballos en llegar a meta y preguntamos si quiere continuar o finalizar
    public synchronized void tresPrimeros() {

        System.out.println("\n-- [ Los tres primeros son ] ---");
        System.out.printf("%-10s %-15s %-20s\n", "Posición", "Nombre", "Tiempo/Estado");
        System.out.println("------------------------------------------------------");

        for (int i = 0; i < 3; i++) {
            Caballos c = clasificacion.get(i);
            long tiempoTotal = (c.getTiempoFinal() - c.getTiempoInicial()) / 1000;
            System.out.printf("%-10s %-15s %-20s\n", (i + 1), "Caballo " + c.getName(), tiempoTotal + " s");
        }

        pausarCaballos(); //Pausamos la carrera a la espera de confirmación del usuario
    }

    //Muestra la clasificación final
    public void mostrarClasificacionFinal() {

        System.out.println("\n--- [ Clasificación final ] ---");
        System.out.printf("%-10s %-15s %-20s\n", "Posición", "Nombre", "Tiempo/Estado");
        System.out.println("------------------------------------------------------");
        int posicion = 1;

        // Si han finalizado la carrera mostrará la posición, el ID del caballo y el tiempo.
        for(Caballos c : clasificacion){
            long tiempoTotal = (c.getTiempoFinal() - c.getTiempoInicial()) / 1000;
            System.out.printf("%-10d %-15s %-20s\n", posicion++, "Caballo " + c.getName(), tiempoTotal + " s");
        }

        // Si han abandonado la carrera mostrará la posición, el ID del caballo el mensaje de "ABANDONO"
        for (Caballos c : caballosList) {
            if (c.isAbandono()) {
                System.out.printf("%-10d %-15s %-20s\n", posicion++, "Caballo " + c.getName(), "ABANDONO");
            }
        }

        // Si no han finalizado la carrera mostrará la posición, el ID del caballo el mensaje de "NO FINALIZADO".
        for (Caballos c : caballosList) {
            if (!clasificacion.contains(c) && !c.isAbandono()) {
                System.out.printf("%-10d %-15s %-20s\n", posicion++, "Caballo " + c.getName(), "NO FINALIZADO");
            }
        }
    }
}