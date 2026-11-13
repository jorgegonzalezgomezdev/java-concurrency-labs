package es.dam.psp.executor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Ampliacion para ver de verdad el "solo dos a la vez":
// se piden 5 informes a un pool de 2 hilos.
public class MainVariosInformes {
    private static final int NUM_INFORMES = 5;

    public static void main(String[] args) {
        Path outputDirectory = Path.of("reports");
        long inicio = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            List<Future<Path>> futures = new ArrayList<>();

            for (int i = 1; i <= NUM_INFORMES; i++) {
                futures.add(executor.submit(new WriteCSV(outputDirectory)));
                System.out.println("Report request " + i + " submitted");
            }
            System.out.println("The main program continues working...");

            for (int i = 0; i < futures.size(); i++) {
                try {
                    Path report = futures.get(i).get();
                    System.out.println("NOTIFICATION " + (i + 1) + ": " + report.getFileName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } catch (ExecutionException e) {
                    System.out.println("Informe " + (i + 1) + " fallido: " + e.getCause());
                }
            }
        }

        // 5 informes de 2 s con 2 hilos -> 3 "tandas" -> unos 6 s (no 10, ni 2)
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - inicio) + " ms");
    }
}
