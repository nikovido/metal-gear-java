// Interfaz que define el comportamiento de los enemigos
public interface Enemigo {

    // Método para que el enemigo patrulle el mapa (moverse aleatoriamente arriba-abajo-izquierda-derecha)
    void patrullar();

    // Método para atacar al jugador o reaccionar si lo detecta 
    void atacar(Personaje objetivo);

    // Método para detectar si hay alguien cerca (si esta en una celda arriba-abajo-izquierda-derecha)
    boolean detectar(Personaje objetivo);
}
