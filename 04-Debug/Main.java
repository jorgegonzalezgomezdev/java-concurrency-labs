package es.dam.psp.ejerciciosdebug.template;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = cargarCatalogo();
        Map<String, Usuario> usuarios = cargarUsuarios();
        Map<Integer, Integer> reproducciones = cargarReproducciones();
        Queue<Integer> cola = new LinkedList<>();

        cola.add(3);
        cola.add(1);
        cola.add(5);
        cola.add(2);

        Usuario usuarioActual = usuarios.get("alex");
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1 -> catalogo.mostrarTodos();
                case 2 -> buscarPorGenero(teclado, catalogo);
                case 3 -> reproducirSiguiente(cola, catalogo,
                        reproducciones, usuarioActual);
                case 4 -> usuarioActual.mostrarHistorial();
                case 5 -> mostrarRanking(catalogo, reproducciones);
                case 6 -> eliminarGenero(teclado, catalogo);
                case 7 -> ejecutarProcesamiento();
                case 0 -> System.out.println("Cerrando StreamBox.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        teclado.close();
    }

    private static Catalogo cargarCatalogo() {
        Catalogo catalogo = new Catalogo();
        catalogo.anadir(new Contenido(1, "Nebula", "ciencia-ficcion", 118));
        catalogo.anadir(new Serie(2, "Código Rojo", "thriller", 48, 1, 1));
        catalogo.anadir(new Contenido(3, "La última partida", "deporte", 102));
        catalogo.anadir(new Serie(4, "Madrid 2040", "ciencia-ficcion", 52, 1, 2));
        catalogo.anadir(new Contenido(5, "Frecuencia", "musica", 95));
        return catalogo;
    }

    private static Map<String, Usuario> cargarUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        usuarios.put("alex", new Usuario("alex"));
        usuarios.put("lucia", new Usuario("lucia"));
        return usuarios;
    }

    private static Map<Integer, Integer> cargarReproducciones() {
        Map<Integer, Integer> reproducciones = new HashMap<>();
        reproducciones.put(1, 240);
        reproducciones.put(2, 510);
        reproducciones.put(3, 820);
        reproducciones.put(4, 105);
        reproducciones.put(5, 390);
        return reproducciones;
    }

    private static void mostrarMenu() {
        System.out.println("\n=== STREAMBOX DEBUG LAB ===");
        System.out.println("1. Mostrar catálogo");
        System.out.println("2. Buscar por género");
        System.out.println("3. Reproducir siguiente");
        System.out.println("4. Mostrar historial");
        System.out.println("5. Mostrar ranking");
        System.out.println("6. Eliminar por género");
        System.out.println("7. Procesar tareas concurrentes");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private static int leerEntero(Scanner teclado) {
        while (!teclado.hasNextInt()) {
            System.out.println("Introduce un número.");
            teclado.nextLine();
        }
        int valor = teclado.nextInt();
        teclado.nextLine();
        return valor;
    }

    private static void buscarPorGenero(Scanner teclado, Catalogo catalogo) {
        System.out.print("Género: ");
        String genero = teclado.nextLine();
        List<Contenido> resultado = catalogo.buscarPorGenero(genero);

        if (resultado.isEmpty()) {
            System.out.println("No se han encontrado contenidos.");
            return;
        }

        for (Contenido contenido : resultado) {
            System.out.println(contenido);
        }
    }

    private static void reproducirSiguiente(Queue<Integer> cola,
                                            Catalogo catalogo,
                                            Map<Integer, Integer> reproducciones,
                                            Usuario usuario) {
        if (cola.isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }

        int id = cola.poll();
        Contenido contenido = catalogo.buscarPorId(id);

        if (contenido == null) {
            System.out.println("El contenido ya no existe.");
            return;
        }

        int total = reproducciones.getOrDefault(id, 0);
        reproducciones.put(id, 0);
        usuario.anadirAlHistorial(contenido.getTitulo());

        System.out.println("Reproduciendo: " + contenido.getTitulo());
        System.out.println("Total de reproducciones: " + (total + 1));
    }

    private static void mostrarRanking(Catalogo catalogo,
                                       Map<Integer, Integer> reproducciones) {
        List<Integer> ids = new ArrayList<>(reproducciones.keySet());
        ids.sort(Comparator.comparingInt(reproducciones::get));

        System.out.println("\nRanking de contenidos:");
        for (int id : ids) {
            System.out.println(catalogo.buscarPorId(id).getTitulo()
                    + " -> " + reproducciones.get(id));
        }
    }

    private static void eliminarGenero(Scanner teclado, Catalogo catalogo) {
        System.out.print("Género que eliminar: ");
        String genero = teclado.nextLine();
        catalogo.eliminarPorGenero(genero);
        System.out.println("Operación terminada.");
    }

    private static void ejecutarProcesamiento() {
        List<String> tareas = List.of(
                "video-01", "video-02", "subtitulos-01", "miniatura-01",
                "audio-01", "video-03", "miniatura-02", "subtitulos-02");
        new Procesador().procesarConHilos(tareas);
    }
}
