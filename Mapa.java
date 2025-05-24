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
    if (!posicionValida(x, y)) return false;

    Celda celda = getCelda(x, y);

    // Una celda no es libre si tiene personaje o tiene un ítem visible (como L o H)
    return !celda.tienePersonaje() && (!celda.tieneItem() || !celda.getItem().esVisible());
    }

    // Método: para fijar un personaje (antes validamos que sea una posicion valida)
    public void colocarPersonaje(Personaje p) {
        int x = p.getPosicion().getX();
        int y = p.getPosicion().getY();
        if (posicionValida(x, y)) {
            grilla[y][x].setPersonaje(p);
        }
    }

    // Metodo: para mostrar el mapa (matriz) x pantalla en cada turno y cuando se requiera
    public void mostrarMapa() {
    System.out.println("\nEstado del mapa:\n");

    // Imprimir encabezado de columnas
    System.out.print("    ");
    for (int col = 0; col < columnas; col++) {
        System.out.printf("  %2d  ", col);
    }
    System.out.println();

    // Separador superior
    System.out.print("    ");
    for (int col = 0; col < columnas; col++) {
        System.out.print("______");
    }
    System.out.println();

    // Imprimir filas
    for (int fila = 0; fila < filas; fila++) {
        System.out.printf("%2d |", fila); // número de fila

        for (int col = 0; col < columnas; col++) {
            Celda celda = grilla[fila][col];
            String contenido = " _ ";

            if (celda.tienePersonaje()) {
                contenido = celda.getPersonaje() instanceof Snake ? " S " : " * ";
            } else if (celda.tieneItem() && celda.getItem().esVisible()) {
            String tipo = celda.getItem().getTipo();
            switch (tipo) {
                case "tarjeta" -> contenido = " L ";
                case "hangar"  -> contenido = " H ";
                default        -> contenido = " ? ";
            }
}

            System.out.print(" " + contenido + " |");
        }
        System.out.println();

        // Separador entre filas
        System.out.print("   ");
        for (int col = 0; col < columnas; col++) {
            System.out.print("------");
        }
        System.out.println();
    }

    System.out.println();
    }

    
    // Metodo para mover personaje, boolean para facilitar la logica, devuelve true si pudo moverlo
    public boolean moverPersonaje(Personaje personaje, int dx, int dy) {
    int xActual = personaje.getPosicion().getX();
    int yActual = personaje.getPosicion().getY();

    int xNuevo = xActual + dx;
    int yNuevo = yActual + dy;

    // Verificamos si la posición nueva es válida y está libre
    if (!posicionValida(xNuevo, yNuevo)) return false;

    // Si no es Snake, usamos la regla estricta (no pisar ítems)
    if (!(personaje instanceof Snake) && !celdaLibre(xNuevo, yNuevo)) {
        return false;
    }

    // Si es Snake, permitimos pisar ítems visibles
    Celda destino = getCelda(xNuevo, yNuevo);
    if (destino.tienePersonaje()) return false; // no pisar personajes nunca

    // Removemos al personaje de la celda actual
    grilla[yActual][xActual].removerPersonaje();

    // Movemos el personaje
    personaje.mover(dx, dy);

    // Lo colocamos en la nueva celda
    grilla[yNuevo][xNuevo].setPersonaje(personaje);

    return true;
    }



    // Getter: Obtenemos celda (por si se necesita acceder directamente, sirve??? si jaja)
    public Celda getCelda(int x, int y) {
        if (posicionValida(x, y)) {
            return grilla[y][x];
        }
        return null;
    }
}
