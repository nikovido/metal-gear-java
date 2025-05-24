// Clase abstracta que representa Mision
public abstract class Mision {

    protected Juego juego;

    public Mision(Juego juego) {
        this.juego = juego;
    }

    public abstract void iniciar(); // Iniciar la mision

    public abstract boolean verificarVictoria(); // Verifica si ganamos

    public abstract boolean verificarDerrota(); // Verifica si perdimos toda la vida
}
