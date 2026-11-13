# Programación de Servicios y Procesos · DAM 2

**Alumno:** Jorge González Gómez
**Ciclo:** 2º DAM — Desarrollo de Aplicaciones Multiplataforma (Universidad Nebrija)
**Curso:** 2026-2027

---


Apuntes y ejercicios de PSP, con la misma estructura que el resto de
asignaturas de este curso: cada tema numerado es un proyecto Maven
independiente que se abre en IntelliJ con **File -> Open...**
seleccionando su carpeta (la que tiene el `pom.xml`).

## Estructura

```
Programacion de Servicios y Procesos/
├── 00-Apuntes/
│   ├── 00-teoria-y-apuntes.md      <- toda la teoria (Partes 0-10): hilos, concurrencia, colas thread-safe, synchronized, depurador
│   └── 01-guia-repaso.md           <- EMPIEZA AQUI: orden de repaso, rutas y en que Parte esta explicado cada ejercicio
├── 01-Hilos-Numeros-Letras/        <- Maven · src/main/java/com/jorge/hilos/Main.java
├── 02-Tienda-Dependientes-Clientes/<- Maven · src/main/java/com/jorge/tienda/Main.java + SynchronizedBlockCounter.java
├── 03-Ficheros/                    <- vacio (solo .idea)
├── 04-Debug/                       <- StreamBox Debug Lab (proyecto IntelliJ, sin pom.xml)
└── 05-Executor-WriteCSV/          <- Maven · ExecutorService + Callable + Future generando CSV
```

## 01 - Hilos: numeros y letras
Dos hilos creados con lambdas (`new Thread(() -> ...)`) y lanzados con
`start()`: uno imprime "1 2 3" y el otro "A B C", en un orden no
determinista. Es el
ejemplo mas simple para ver la diferencia entre ejecucion secuencial y
concurrente. Si el concepto no esta claro todavia, lee antes
`00-Apuntes/00-teoria-y-apuntes.md`.

## 02 - Tienda: dependientes y clientes
Basado en el ejemplo `CoffeeShop.java` de Blackboard: `Runnable` +
`ConcurrentLinkedQueue` + `interrupt()`/`join()` para cerrar, aplicado a
una tienda con dos zonas (3 dependientes de consumibles, 2 de ropa).
Cada cliente se reparte segun lo que busca; si no vendemos lo que busca,
no se le atiende. Explicado con mas detalle en el README de la carpeta y
en la Parte 7 de los apuntes.
Incluye ademas `SynchronizedBlockCounter.java` (ejemplo de clase del
contador compartido con `synchronized`, Parte 9 de los apuntes).

## 04 - Debug (StreamBox Debug Lab)
Menu de consola con bugs metidos a proposito para encontrarlos con el
depurador de IntelliJ. Teoria del depurador en la Parte 10 de los
apuntes; tipos de fallo a buscar en `00-Apuntes/01-guia-repaso.md` (Paso 7).

## 05 - Executor: informes CSV
`ExecutorService` con un pool de 2 hilos, una tarea `Callable<Path>` (`WriteCSV`)
que escribe un CSV en `reports/` y devuelve su ruta, y `Future.get()` para
recogerla. Teoria en la Parte 11 de los apuntes.
