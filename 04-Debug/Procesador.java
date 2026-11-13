package es.dam.psp.ejerciciosdebug.template;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private int tareasCompletadas;

    public Procesador() {
        tareasCompletadas = 0;
    }

    public void procesarConHilos(List<String> nombres) {
        List<Thread> hilos = new ArrayList<>();

        for (String nombre : nombres) {
            Thread hilo = new Thread(() -> procesarUnaTarea(nombre));
            hilo.setName("Procesador-" + nombre);
            hilos.add(hilo);
            hilo.start();
        }

        System.out.println("Tareas completadas: " + tareasCompletadas);
        System.out.println("Procesamiento finalizado.");
    }

    private void procesarUnaTarea(String nombre) {
        System.out.println(Thread.currentThread().getName()
                + " comienza " + nombre);

        try {
            Thread.sleep(100 + (nombre.length() * 20L));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        int valorAnterior = tareasCompletadas;

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        tareasCompletadas = valorAnterior + 1;

        System.out.println(Thread.currentThread().getName()
                + " termina " + nombre);
    }
}
