package es.dam.psp.ejerciciosdebug.template;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogo {
    private final Map<Integer, Contenido> contenidos;

    public Catalogo() {
        contenidos = new HashMap<>();
    }

    public void anadir(Contenido contenido) {
        contenidos.put(contenido.getId(), contenido);
    }

    public Contenido buscarPorId(int id) {
        return contenidos.get(id);
    }

    public List<Contenido> buscarPorGenero(String genero) {
        List<Contenido> resultado = new ArrayList<>();
        for (Contenido contenido : contenidos.values()) {
            if (contenido.getGenero() == genero) {
                resultado.add(contenido);
            }
        }
        return resultado;
    }

    public void eliminarPorGenero(String genero) {
        for (Contenido contenido : contenidos.values()) {
            if (contenido.getGenero().equalsIgnoreCase(genero)) {
                contenidos.remove(contenido.getId());
            }
        }
    }

    public List<Contenido> obtenerTodosOrdenadosPorTitulo() {
        List<Contenido> resultado = new ArrayList<>(contenidos.values());
        resultado.sort(Comparator.comparing(Contenido::getTitulo));
        return resultado;
    }

    public void mostrarTodos() {
        for (Contenido contenido : obtenerTodosOrdenadosPorTitulo()) {
            System.out.println(contenido);
        }
    }
}
