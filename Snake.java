// Clase que representa a Personaje : Snake
public class Snake extends Personaje {
    private int nivelC4; // 0 = sin c4, 1 = C4, etc..
    private int nivelTarjeta; // 0 = sin tarjeta, 1 = nivel 1, etc.
    // Constructor: el tipo se fija como "Snake"
    // Usamos super para usar el constructor padre
    public Snake(String nombre, int salud, Posicion posicion) {
        super("Snake", nombre, salud, posicion);
        this.nivelC4 = 0; // empieza sin C4
        this.nivelTarjeta = 0; // empieza sin tarjeta
    }

    // Getter: nivel de C4
    public int getNivelC4() {
        return nivelC4;
    }
    // Setter: nivel de C4
    public void setNivelC4(int nivelC4) {
        this.nivelC4 = nivelC4;
    }

    // Getter: nivel de tajeta
    public int getNivelTarjeta() {
        return nivelTarjeta;
    }
    // Setter: nivel de tarjeta
    public void setNivelTarjeta(int nivelTarjeta) {
        this.nivelTarjeta = nivelTarjeta;
    }

    // Reescribimos el método mostrarEstado para Snake con @Override
    @Override
    public void mostrarEstado() {
        System.out.println("[" + tipo + "] " + nombre + " - Salud: " + salud + " - Posición: (" + posicion.getX() + ", " + posicion.getY() + ")");
        System.out.println("[" + tipo + "] " + "Explosivo C4: " + nivelC4 + " - Tarjeta Nivel: " + nivelTarjeta);
    }

    // Metodo para sumar vida cuando se levanta un item de salud
    public void sumarVida(int cantidad) {
        salud += cantidad;
        System.out.println("[" + tipo + "] " + nombre + " recupera " + cantidad + " puntos de salud.");
    }
    
}
