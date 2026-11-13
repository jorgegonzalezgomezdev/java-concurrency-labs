package es.dam.psp.executor;

import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainWriteCSV {
    public static void main(String[] args) {
        Path outputDirectory = Path.of("reports");

        // Only two reports can be generated at the same time.
        // -> un pool de 2 hilos: como mucho 2 tareas a la vez, el resto esperan en cola.
        // try-with-resources: al salir del bloque se llama a executor.close(),
        // que espera a que acaben las tareas y apaga los hilos del pool.
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            // submit() entrega la tarea al pool y devuelve AL INSTANTE un Future:
            // un "resguardo" del resultado que todavia no existe.
            Future<Path> future = executor.submit(new WriteCSV(outputDirectory));
            System.out.println("Report request submitted");

            // Mientras el pool genera el informe, el main sigue a lo suyo
            System.out.println("The main program continues working...");

            try {
                // get() BLOQUEA al main hasta que call() termina y devuelve la ruta
                Path report = future.get();
                System.out.println("NOTIFICATION: report ready at " + report.toAbsolutePath());

            } catch (InterruptedException e) {
                // Han interrumpido al main mientras esperaba en get()
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                // call() lanzo una excepcion (p. ej. IOException): viene envuelta aqui.
                // e.getCause() es la excepcion original.
                throw new RuntimeException("Error generando el informe", e.getCause());
            }
        }
    }
}
