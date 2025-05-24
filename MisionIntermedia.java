// Clase que representa las Misiones Intermedias (1 o 2)
public class MisionIntermedia extends Mision {

    private int numero;

    public MisionIntermedia(Juego juego, int numero) {
        super(juego);
        this.numero = numero;
    }

    @Override
    public void iniciar() {
        if (numero == 1) {
            System.out.println("== INICIANDO MISIÓN 1 ==");

            // Crear mapa 7x7
            Mapa mapa = new Mapa(7, 7); // 7 filas, 7 columnas
            juego.setMapa(mapa);

            // Crear y colocar Snake en (0,6)
            Snake snake = new Snake("Snake", 100, new Posicion(0, 6));
            juego.setJugador(snake);
            mapa.colocarPersonaje(snake);
            
            // Colocar hangar
            Item hangar = new Item("hangar", "Puerta del Hangar", new Posicion(3, 0), 0);
            juego.agregarItem(hangar);

            // Crear enemigos
            juego.agregarEnemigo(new Guardia("Guardia 1", 50, new Posicion(1, 1)));
            juego.agregarEnemigo(new Guardia("Guardia 2", 50, new Posicion(3, 2)));
            juego.agregarEnemigo(new Guardia("Guardia 3", 50, new Posicion(2, 4)));
            juego.agregarEnemigo(new Guardia("Guardia 4", 50, new Posicion(5, 4)));

            // Crear item llave del hangar en (0,3)
            Item tarjeta = new Item("tarjeta", "Tarjeta del Hangar", new Posicion(0, 3), 1);
            juego.agregarItem(tarjeta);

            System.out.println("Objetivo: recoger la tarjeta (L) y llegar al hangar (H). Evitá a los enemigos (*).");

        } else if (numero == 2) {
            System.out.println("== MISIÓN 2 NO IMPLEMENTADA TODAVÍA ==");
        }
    }
    // Sobreescribimos la victoria para mision intermedias
    @Override
    public boolean verificarVictoria() {
        Snake snake = juego.getJugador();
        int x = snake.getPosicion().getX();
        int y = snake.getPosicion().getY();

        if (numero == 1) {
            // Llegar al hangar con la tarjeta
            return x == 3 && y == 0 && snake.getNivelTarjeta() >= 1;
        }

        if (numero == 2) {
            // Otra lógica de victoria
            return false;
        }

        return false;
    }
    // Sobre escribimos con Override la derrota 
    @Override
    public boolean verificarDerrota() {
        Snake snake = juego.getJugador();

        if (snake.getSalud() <= 0) return true; // Si tenemos 0 o menos vida, estamos derrotados

        for (int i = 0; i < juego.getCantidadEnemigos(); i++) {
            Enemigo enemigo = juego.getEnemigos()[i];
            Personaje e = (Personaje) enemigo;

            int dx = Math.abs(e.getPosicion().getX() - snake.getPosicion().getX());
            int dy = Math.abs(e.getPosicion().getY() - snake.getPosicion().getY());

            // Capturado: enemigo esta en la misma posición
            if (dx == 0 && dy == 0) {
                System.out.println("¡Snake fue capturado por " + e.getNombre() + "!");
                return true; // si estan en la misma posicion, estamos derrotados
            }
        }
        // Caso contrario, no tiene 0 vida, ni esta en la misma posicion que un enemigo, no estamos derrotados
        return false;
    }

}
