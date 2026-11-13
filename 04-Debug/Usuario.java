package es.dam.psp.ejerciciosdebug.template;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String alias;
    private final List<String> historial;

    public Usuario(String alias) {
        this.alias = alias;
        this.historial = new ArrayList<>();
    }

    public void anadirAlHistorial(String titulo) {
        historial.add(titulo);
    }

    public boolean haVisto(String titulo) {
        for (String elemento : historial) {
            if (elemento == titulo) {
                return true;
            }
        }
        return false;
    }

    public void mostrarHistorial() {
        System.out.println("Historial de " + alias + ":");
        for (String titulo : historial) {
            System.out.println("- " + titulo);
        }
    }
}
