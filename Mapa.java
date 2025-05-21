// Clase que representa el mapa en forma de matriz / grilla
public class Mapa {

    private int filas; // Para filas
    private int columnas; // Para columnas
    private Celda[][] grilla; // La matriz / grilla

    // Constructor: filas columnas y grilla (que contiene celdas)
    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grilla = new Celda[filas][columnas];

        // Inicializamos cada celda recorriendo toda la matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                grilla[i][j] = new Celda();
            }
        }
    }

    // Método: para que verifiquemos si una posición es válida dentro de la grilla
    public boolean posicionValida(int x, int y) {
        return x >= 0 && x < columnas && y >= 0 && y < filas;
    }

    // Método: para que verifiquemos si una celda está libre de otro personaje
    public boolean celdaLibre(int x, int y) {
        return posicionValida(x, y) && !grilla[y][x].tienePersonaje();
    }

    // Método: para fijar un personaje (antes validamos que sea una posicion valida)
    public void colocarPersonaje(Personaje p) {
        int x = p.getPosicion().getX();
        int y = p.getPosicion().getY();
        if (posicionValida(x, y)) {
            grilla[y][x].setPersonaje(p);
        }
    }

    // Método: para mover un personaje de una celda a otra (a las coordenadas que recibimos)
    public void moverPersonaje(Personaje p, int dx, int dy) {
        int xActual = p.getPosicion().getX();
        int yActual = p.getPosicion().getY();

        int xNuevo = xActual + dx;
        int yNuevo = yActual + dy;
        // verificamos que la celda este libre de personajes y procedemos
        if (celdaLibre(xNuevo, yNuevo)) {
            grilla[yActual][xActual].removerPersonaje();
            p.mover(dx, dy);
            grilla[yNuevo][xNuevo].setPersonaje(p);
        }
    }

    // Getter: Obtenemos celda (por si se necesita acceder directamente, sirve???)
    public Celda getCelda(int x, int y) {
        if (posicionValida(x, y)) {
            return grilla[y][x];
        }
        return null;
    }
}
