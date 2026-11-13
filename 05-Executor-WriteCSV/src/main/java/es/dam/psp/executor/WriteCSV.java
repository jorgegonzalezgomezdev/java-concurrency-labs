package es.dam.psp.executor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Callable;

// La TAREA que se le da al ExecutorService.
// Es como un Runnable, pero con dos diferencias:
//   1. call() DEVUELVE algo (aqui, la ruta del CSV que ha creado).
//   2. call() puede lanzar excepciones (aqui, IOException si falla el disco).
// Por eso implementa Callable<Path> y no Runnable.
public class WriteCSV implements Callable<Path> {

    private static final DateTimeFormatter FORMATO_NOMBRE =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private final Path outputDirectory;

    public WriteCSV(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public Path call() throws Exception {
        String hilo = Thread.currentThread().getName();
        System.out.println("[" + hilo + "] Empiezo a generar el informe...");

        // 1. Crear la carpeta si no existe (si ya existe no hace nada)
        Files.createDirectories(outputDirectory);

        // 2. Simular que generar el informe tarda (consultas, calculos...)
        Thread.sleep(2000);

        // 3. Preparar el contenido del CSV: cabecera + filas separadas por ;
        List<String> lineas = List.of(
                "id;producto;unidades;precio",
                "1;Teclado;3;24.99",
                "2;Raton;5;12.50",
                "3;Monitor;1;189.00",
                "4;Auriculares;2;39.90"
        );

        // 4. Nombre unico (fecha + nombre del hilo) para que dos informes
        //    generados a la vez no se pisen el mismo fichero
        String nombre = "report-" + LocalDateTime.now().format(FORMATO_NOMBRE)
                + "-" + hilo + ".csv";
        Path fichero = outputDirectory.resolve(nombre);

        // 5. Escribir todas las lineas de golpe (NIO, como en Acceso a Datos)
        Files.write(fichero, lineas);

        System.out.println("[" + hilo + "] Informe escrito: " + fichero.getFileName());

        // 6. Lo que devuelve call() es lo que luego recoge future.get()
        return fichero;
    }
}
