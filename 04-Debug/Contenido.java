package es.dam.psp.ejerciciosdebug.template;

public class Contenido {
    private final int id;
    private final String titulo;
    private final String genero;
    private final int duracionMinutos;

    public Contenido(int id, String titulo, String genero, int duracionMinutos) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracionMinutos = duracionMinutos;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getDuracionMinutos() { return duracionMinutos; }

    @Override
    public String toString() {
        return id + " - " + titulo + " | " + genero
                + " | " + duracionMinutos + " min";
    }
}
