# 05 - Executor: generar informes CSV (ExecutorService + Callable + Future)

Proyecto Maven sin dependencias. File -> Open -> esta carpeta (la del `pom.xml`).
Teoria: Parte 11 de `00-Apuntes/00-teoria-y-apuntes.md`.

## Ficheros (`src/main/java/es/dam/psp/executor/`)
- `MainWriteCSV.java` -- el `main` del enunciado: pide **un** informe a un pool de hilos,
  sigue trabajando y luego recoge la ruta con `future.get()`.
- `WriteCSV.java` -- la tarea: `Callable<Path>` que crea la carpeta `reports/`, tarda 2 s
  (simulado), escribe un CSV con NIO y **devuelve** la ruta del fichero.
- `MainVariosInformes.java` -- ampliacion: 5 informes a un pool de 2 hilos para ver que
  solo se generan 2 a la vez (tarda unos 6 s, no 10 ni 2).

## Cambios respecto al enunciado
- `writeCSV` -> `WriteCSV`: en Java las clases van en mayuscula.
- `newFixedThreadPool(1)` -> `newFixedThreadPool(2)`: el comentario dice "solo dos a la vez".
- En `catch (ExecutionException e)` se usa `e.getCause()`: ahi esta la excepcion real de `call()`.

## Donde sale el CSV
En `reports/` dentro de la carpeta desde la que se ejecuta (en IntelliJ, la raiz de este
proyecto). La ruta absoluta la imprime el propio programa.
