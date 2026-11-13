package es.dam.psp.ejerciciosdebug.template;

public class Serie extends Contenido {
    private final int temporada;
    private final int episodio;

    public Serie(int id, String titulo, String genero, int duracionMinutos,
                 int temporada, int episodio) {
        super(id, titulo, genero, duracionMinutos);
        this.temporada = temporada;
        this.episodio = episodio;
    }

    @Override
    public String toString() {
        return "SERIE | " + super.toString()
                + " | T" + temporada + "E" + episodio;
    }
}
