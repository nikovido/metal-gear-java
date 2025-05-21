// Clase que representa la posición en la matriz (X, Y)
public class Posicion {
    private int x; // X: horizontal
    private int y;  // Y: vertical

    // Constructor: recibimos las coordenadas iniciales con this...
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter: Obtenemos X
    public int getX() {
        return x;
    }

    // Getter: Obtenemos Y
    public int getY() {
        return y;
    }

    // Setter: Fijamos X
    public void setX(int x) {
        this.x = x;
    }

    // Setter: Fijamos Y
    public void setY(int y) {
        this.y = y;
    }

    // Método para mover la posición en X e Y (con los valores dx y dy)
    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Método para comparar dos posiciones (para evitar superposiciones , etc )
    public boolean igualA(Posicion otra) {
        return this.x == otra.x && this.y == otra.y;
    }
}
