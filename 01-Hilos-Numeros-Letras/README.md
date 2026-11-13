# 01 - Hilos: Numeros y Letras

Proyecto Maven sin dependencias externas: se abre en IntelliJ (File ->
Open, seleccionando esta carpeta) y se ejecuta `Main.java`.

## Que hace
- `Main` crea dos hilos pasando una lambda a `new Thread(() -> ...)`:
  uno imprime "1 2 3" y el otro "A B C" (cada uno en un solo `println`).
- Llama a `start()` en cada uno, asi que se ejecutan de forma
  concurrente: no se sabe de antemano que linea sale antes. El `main`
  no hace `join()`, asi que tampoco los espera.
- Para ver el entrelazado numero a numero (1, A, 2, B...) hay que
  imprimir en un bucle con `Thread.sleep` (Parte 8 de los apuntes).

## Como ejecutarlo
Abre `Main.java` y pulsa el boton de Run junto a `public static void
main`. Ejecutalo varias veces: veras que el orden de las lineas
cambia de una ejecucion a otra. Esa es la
prueba de que son hilos reales y no una simple llamada secuencial.

## Para entender el porque
`00-Apuntes/00-teoria-y-apuntes.md`, en la carpeta de la asignatura,
explica la diferencia entre ejecucion secuencial, concurrencia y
paralelismo, y por que `start()` no es lo mismo que llamar a `run()`
directamente (mira el comentario al final de `Main.java`).
