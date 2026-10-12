
package nerea.psp_u2_montoyaolivaresnerea;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
* @author Nerea
*/

public class Carrera {
    private List<Cavall> caballos = new ArrayList();
    private double distancia;
    int caballosFinalizados;
    private boolean pausado = false;
    private static final String formato_hora = "HH:mm:ss";

    public Carrera(int numCaballos, double distancia) {
        this.distancia = distancia;

        for(int i = 1; i <= numCaballos; ++i) {
            this.caballos.add(new Cavall("Cavall " + i, distancia, this));
        }

    }

    public void iniciar() throws InterruptedException {
        Iterator var1 = this.caballos.iterator();

        while(var1.hasNext()) {
            Cavall cavall = (Cavall)var1.next();
            cavall.start();
        }

        boolean carreraFinalizada = false;

        for(List<Cavall> top3 = new ArrayList(); !carreraFinalizada; carreraFinalizada = this.caballos.stream().allMatch((cavallx) -> {
            return cavallx.getDistancia() >= this.distancia;
        })) {
            this.mostrarRuta();
            Iterator var3 = this.caballos.iterator();

            while(var3.hasNext()) {
                Cavall cavall = (Cavall)var3.next();
                if (cavall.getDistancia() >= this.distancia && !top3.contains(cavall)) {
                    top3.add(cavall);
                    ++this.caballosFinalizados;
                    if (top3.size() == 3) {
                        top3.sort(Comparator.comparingLong(Cavall::getTiempoTotal));
                        System.out.println("\n¡Los tres primeros caballos han llegado a la meta!");

                        for(int i = 0; i < top3.size(); ++i) {
                            Cavall caballo = (Cavall)top3.get(i);
                            System.out.printf("Posición %d: %s - Tiempo: %.2f segundos\n", i + 1, caballo.getNombreCavall(), (double)caballo.getTiempoTotal());
                        }

                        synchronized(this) {
                            Scanner scanner = new Scanner(System.in);
                            System.out.print("\n¿Quieres continuar con la carrera? (s/n): \n");
                            String respuesta = scanner.next();
                            if (respuesta.equalsIgnoreCase("s")) {
                                this.pausado = false;
                                this.notifyAll();
                            } else if (respuesta.equalsIgnoreCase("n")) {
                                this.mostrarResultados();
                                this.detenerCarrera();
                                return;
                            }
                            break;
                        }
                    }
                }
            }

            Thread.sleep(1000L);
        }

        this.mostrarResultados();
    }

    public void detenerCarrera() {
        Iterator var1 = this.caballos.iterator();

        while(var1.hasNext()) {
            Cavall cavall = (Cavall)var1.next();
            cavall.interrupt();
        }

    }

    public void pausa() throws InterruptedException {
        while(this.pausado) {
            if (Thread.interrupted()) {
                System.out.println("Carrera ha sido interrumpida.");
                throw new InterruptedException();
            }

            this.wait();
        }

    }

    private void mostrarRuta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaActual = LocalTime.now().format(formatter);
        StringBuilder estado = (new StringBuilder("\nEstado actual de la carrera: ")).append(horaActual).append("\n");
        int posiciones = 50;
        Iterator var5 = this.caballos.iterator();

        while(var5.hasNext()) {
            Cavall cavall = (Cavall)var5.next();
            int posicionActual = (int)(cavall.getDistancia() / this.distancia * (double)posiciones);
            posicionActual = Math.min(posicionActual, posiciones);
            StringBuilder linia = new StringBuilder();

            for(int i = 0; i < posiciones; ++i) {
                if (i == posicionActual) {
                    linia.append("H");
                } else {
                    linia.append("_");
                }
            }

            estado.append(String.format("%s %s %.0fkm/h\n", cavall.getNombreCavall(), linia, cavall.getVelocidadInicial()));
        }

        System.out.println(estado);
    }

    public void mostrarResultados() {
        System.out.println("\nCLASSIFICACIÓN FINAL:");
        this.caballos.sort(Comparator.comparingLong(Cavall::getTiempoTotal));
        int posicion = 1;
        Iterator var2 = this.caballos.iterator();

        Cavall cavall;
        while(var2.hasNext()) {
            cavall = (Cavall)var2.next();
            if (cavall.getDistancia() >= this.distancia) {
                System.out.printf("Posición %d --> %s - Tiempo: %.2f segundos\n", posicion++, cavall.getNombreCavall(), (double)cavall.getTiempoTotal());
            }
        }

        var2 = this.caballos.iterator();

        while(var2.hasNext()) {
            cavall = (Cavall)var2.next();
            if (cavall.getDistancia() < this.distancia) {
                System.out.printf("Posición %d --> %s - Tiempo: No terminó\n", posicion++, cavall.getNombreCavall());
            }
        }

    }
}

