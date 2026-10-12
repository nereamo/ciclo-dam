package org.example;

/**
 * Clase Caballos que extiende de Thread para la creación de hilos
 */

public class Caballos extends Thread{

    //==== Variables ====
    private int numCaballo;//id Caballo
    private int distanciaRecorrida;
    private int distanciaTotal;
    private long tiempoInicial;
    private long tiempoFinal;
    private Carrera carrera;
    private int velocidadActual = 50;
    private volatile String mensajeEvento = ""; //Comunica mensajes de evento entre hilos
    private boolean abandono = false;

    //==== Constructor ====
    public Caballos(int numCaballo, int distanciaTotal) {
        this.numCaballo = numCaballo;
        this.distanciaTotal = distanciaTotal;
    }

    //==== Getters y Setters ====
    public synchronized void setMensajeEvento(String mensaje) { //Guarda los mensajes de evento de los hilos
        this.mensajeEvento = mensaje;
    }

    public void setCarrera(Carrera carrera) { //Guarda la referencia de la clase "Carrera"
        this.carrera = carrera;
    }

    public int getDistanciaRecorrida() { //Devuelve distancia recorrida
        return distanciaRecorrida;
    }

    public long getTiempoInicial() { //Devuelve el tiempo inicial
        return tiempoInicial;
    }

    public long getTiempoFinal() { //Devuelve el tiempo final
        return tiempoFinal;
    }

    public int getVelocidadActual() {
        return velocidadActual;
    }

    public boolean isAbandono() { //Devuelve si ha abandonado o no
        return abandono;
    }

    /**
     * ==== Metodo Run() de la Class Thread ====
     * Inicia un hilo por cada caballo.
     */

    public void run() {

        tiempoInicial = System.currentTimeMillis(); //guarda el tiempo de inicio

        //Bucle principal que se ejecuta mientras distancia recorrida es menor que distancia total
        while(distanciaRecorrida < distanciaTotal) {

            try{
                Thread.sleep(1000);

                //Si el caballo abandonó, sale del bucle
                if (abandono || Thread.currentThread().isInterrupted()) {
                    return;
                }

                //Pausa en carrera al obtener los tres primeros
                synchronized (carrera) {
                    while (carrera.isPausa()){
                        try {
                            carrera.wait(); //A la espera de reanudación
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }

                //Metodos extra
                avanceEnCarrera();
                entrarComedor();
                encontrarObstaculo();
                lesionAbandono();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    //Metodo que sincroniza los mensajes de eventos de los hilos
    public synchronized String getAndClearMensajeEvento() {
        String evento = this.mensajeEvento;
        this.mensajeEvento = "";
        return evento;
    }

    //Simula el movimiento del caballo (velocidad, distancia y finalización)
    public void avanceEnCarrera() {
        int velocidadRandom = (int) (Math.random() * 11)-5;
        velocidadActual += velocidadRandom;

        // Mensajes de evento
        if (velocidadRandom > 0) {
            setMensajeEvento("Aumentando velocidad");
        } else if (velocidadRandom < 0) {
            setMensajeEvento("Disminuyendo velocidad");
        }

        //Comprobación de los límites de velocidad
        if(velocidadActual > 70) {
            velocidadActual = 70;
        } else if (velocidadActual < 15) {
            velocidadActual = 15;
        }

        //Avance en metros (km/h → m/s)
        distanciaRecorrida += velocidadActual * 1000 / 3600;

        if(distanciaRecorrida >= distanciaTotal) {
            tiempoFinal = System.currentTimeMillis();
            carrera.registrarLlegada(this);
            setMensajeEvento("Carrera finalizada");

        }
    }

    //==== Métodos auxiliares ====

    //De forma aleatoria usando Semaphore para que un caballo entre a comer 5s
    private void entrarComedor() {

        if (Math.random() < 0.1) { //10% de probabilidad

            try {
                setMensajeEvento("Esperando Comedor");
                carrera.comedor.acquire(); //Solicita acceso a comedor
                setMensajeEvento("Comiendo");
                Thread.sleep(5000); //5s comiendo
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            } finally {
                carrera.comedor.release(); //Libera comedor
                setMensajeEvento("");
            }
        }
    }

    //De forma aleatoria el caballo puede encontrar un obstáculo en la pista
    private void encontrarObstaculo() {
        if (Math.random() < 0.1) { //10% de probabilidad
            setMensajeEvento("Obstáculo en pista");
            try {
                Thread.sleep(2000); //2s de espera
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //De forma aleatoria el caballo puede lesionarse y no finalizar la carrera
    private void lesionAbandono(){
        if (Math.random() < 0.05) { //5% de probabilidad
            setMensajeEvento("Lesión, abandonando carrera");
            abandono = true;
            this.interrupt();//Interrumpe el hilo
        }
    }
}