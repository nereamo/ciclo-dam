
package nerea.psp_u2_montoyaolivaresnerea;

import java.util.concurrent.Semaphore;

/**
* @author Nerea
*/

public class Cavall extends Thread {
    private String nombreCavall;
    private double velocidadInicial = 50.0;
    private double distancia = 0.0;
    private double distanciaTotal;
    private long tiempoTotal;
    private final Carrera carrera;
    private long startTime;
    private static final Semaphore paradaParaComer = new Semaphore(1);

    public Cavall(String nombreCavall, double meta, Carrera carrera) {
        this.nombreCavall = nombreCavall;
        this.distanciaTotal = meta;
        this.carrera = carrera;
    }

    public String getNombreCavall() {
        return this.nombreCavall;
    }

    public double getVelocidadInicial() {
        return this.velocidadInicial;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public long getTiempoTotal() {
        return this.tiempoTotal;
    }

    public void run() {
        this.startTime = System.currentTimeMillis();

        while(this.distancia < this.distanciaTotal) {
            try {
                Thread.sleep(1000L);
                this.carrera.pausa();
                this.avanzar();
                this.accionesExtras();
            } catch (InterruptedException var2) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }

    private void avanzar() throws InterruptedException {
        int variacion = (int)(Math.random() * 11.0) - 5;
        this.velocidadInicial += (double)variacion;
        if (this.velocidadInicial < 15.0) {
            this.velocidadInicial = 15.0;
        } else if (this.velocidadInicial > 70.0) {
            this.velocidadInicial = 70.0;
        }

        this.distancia += this.velocidadInicial / 3.6;
        if (this.distancia >= this.distanciaTotal) {
            this.distancia = this.distanciaTotal;
            long endTime = System.currentTimeMillis();
            this.tiempoTotal = (endTime - this.startTime) / 1000L;
        }

    }

    public void accionesExtras() throws InterruptedException {
        if (Math.random() < 0.05) {
            this.velocidadInicial += 10.0;
            System.out.println(this.nombreCavall + " ha aumentado su velocidad!");
            Thread.sleep(3000L);
            this.velocidadInicial -= 10.0;
        }

        if (Math.random() < 0.05) {
            System.out.println(this.nombreCavall + " ha disminuido su velocidad!");
            this.velocidadInicial -= 10.0;
            if (this.velocidadInicial < 15.0) {
                this.velocidadInicial = 15.0;
            }

            Thread.sleep(3000L);
            this.velocidadInicial += 10.0;
        }

        if (Math.random() < 0.1) {
            System.out.println(this.nombreCavall + " esperando para comer.");
            paradaParaComer.acquire();
            System.out.println(this.nombreCavall + " está comiendo.");
            Thread.sleep(2000L);
            paradaParaComer.release();
        }

    }
}

