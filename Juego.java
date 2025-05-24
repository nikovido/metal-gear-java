// Clase pricipal de Juego que orquesta todo

import java.util.Scanner; // Importamos scanner: entrada de teclado

public class Juego {
    private Mapa mapa; // Mapa actual del juego (matriz de Celdas)
    private Snake jugador; // Snake: personaje principal que maneja el jugador
    private Enemigo[] enemigos; // Arreglo de enemigos activos en la misión actual
    private int cantidadEnemigos; // contador de enemigos cargados
    private Item[] items; // Arreglo de ítems activos en el mapa
    private int cantidadItems; // contador de ítems cargados

    // Turno actual del juego (se incrementa cada vez que se llama a ejecutarTurno)
    private int turnoActual;

    // Cuántas misiones fueron completadas (0: misión 1, 1: misión 2, 2: misión final, 3: Creditos finales?)
    private int misionesCompletadas;
    private boolean misionTerminada; // flag para marcar cuando se completo la mision

    // Cual es la mision actual (0: misión 1, 1: misión 2, 2: misión final, 3: Creditos finales?)
    private Mision misionActual;


    // Constructor: prepara todo lo necesario, pero no carga mapa ni personajes todavía
    public Juego() {
        this.misionesCompletadas = 0; // Empezamos desde la misión 1
        //this.misionActual = new Mision; // Empezamos desde la misión 1
        this.turnoActual = 0;

        // Creamos el espacio para enemigos e items (tamaño fisico para inicializar)
        this.enemigos = new Enemigo[10];
        this.items = new Item[20];
        this.cantidadEnemigos = 0;
        this.cantidadItems = 0;
    }

    // Iniciar misión: según cuántas se completaron, carga la misión siguiente
    public void iniciarMision() {
        misionTerminada = false; // reseteamos el flag de mision terminada

        if (misionesCompletadas == 0) {
            misionActual = new MisionIntermedia(this, 1);
        } else if (misionesCompletadas == 1) {
            misionActual = new MisionIntermedia(this, 2);
        } else if (misionesCompletadas == 2) {
            //misionActual = new MisionFinal(this);
        } else {
            System.out.println("¡Todas las misiones han sido completadas!");
            return;
    }
    misionActual.iniciar();  // la misión configura el juego
    mapa.mostrarMapa();      // mostramos el mapa inicial
}


    // Ejecutamos el turno de la maquina:
    public void ejecutarTurno() {
        
        // Verificamos si ganamos
        if (misionActual.verificarVictoria()) {
            System.out.println("¡Misión completada!");
            completarMision();
            return;
        }
        // Verificamos si perdimos
        if (misionActual.verificarDerrota()) {
            System.out.println("¡Misión fallida! Reiniciando...");
            misionTerminada = true; // sin avanzar misión
            return;
        }
        // los enemigos patrullan
        patrullarEnemigos();
        // mostramos como quedo el mapa
        mapa.mostrarMapa();
    }

    // Marca que una misión se completó (se llama desde la clase Mision cuando termina)
    public void completarMision() {
        misionesCompletadas++; // contador de misiones
        misionTerminada = true; // marcador (flag) de cuando se completa la mision 
    }

    // Setea el mapa actual del juego (usado desde las misiones)
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    // Setea el personaje principal del jugador
    public void setJugador(Snake jugador) {
        this.jugador = jugador;
    }

    // Agrega un enemigo al arreglo (también lo coloca en el mapa)
    public void agregarEnemigo(Enemigo enemigo) {
        enemigos[cantidadEnemigos++] = enemigo;
        mapa.colocarPersonaje((Personaje) enemigo);
    }

    // Agrega un ítem al arreglo y lo coloca en la celda correspondiente
    public void agregarItem(Item item) {
        items[cantidadItems++] = item;
        mapa.getCelda(item.getPosicion().getX(), item.getPosicion().getY()).setItem(item);
    }


    // Turno del jugador

    public void turnoJugador(Scanner scanner) {
        System.out.println("Turno de Snake. Ingrese dirección (W = arriba, A = izquierda, S = abajo, D = derecha): ");
        String input = scanner.nextLine().toUpperCase();

        int dx = 0;
        int dy = 0;

        switch (input) {
            case "W" -> dy = -1;
            case "S" -> dy = 1;
            case "A" -> dx = -1;
            case "D" -> dx = 1;
            default -> {
                System.out.println("Movimiento inválido. Intente nuevamente.");
                scanner.close(); // cerramos 
                return; // No hace nada si el input no es válido
            }
        }

        if (mapa.moverPersonaje(jugador, dx, dy)) {
            System.out.println("[Snake] se ha movido a (" + jugador.getPosicion().getX() + ", " + jugador.getPosicion().getY() + ")");
        } else {
            System.out.println("Movimiento inválido. No se puede mover a esa dirección.");
        }
        verificarRecoleccion(); // verificamos si recogio algun item

    }

    // metodo para que los enemigos del mapa patrullen
    public void patrullarEnemigos() {
        for (int i = 0; i < this.cantidadEnemigos; i++) {
            Enemigo enemigo = enemigos[i];

            if (enemigo instanceof Guardia) {
                Guardia guardia = (Guardia) enemigo;

                boolean seMovio = false;
                int intentos = 0;

                while (!seMovio && intentos < 10) {
                    int[] movimiento = guardia.getMovimientoAleatorio();
                    seMovio = mapa.moverPersonaje(guardia, movimiento[0], movimiento[1]);
                    intentos++;
                }

                if (guardia.detectar(jugador)) {
                    guardia.atacar(jugador);
                }
            }
        }
    }

    public void verificarRecoleccion() {
        Celda celdaActual = mapa.getCelda(jugador.getPosicion().getX(), jugador.getPosicion().getY());

        if (celdaActual != null && celdaActual.tieneItem()) {
            Item item = celdaActual.getItem();

            if (item.esVisible()) {
                String tipo = item.getTipo();
                switch (tipo) {
                    case "racion" -> jugador.sumarVida(item.getValor());
                    case "explosivo" -> jugador.setNivelC4(jugador.getNivelC4() + 1);
                    case "tarjeta" -> {
                        jugador.setNivelTarjeta(item.getValor());
                        System.out.println("Has recogido la tarjeta del hangar.");
                    }
                }

                item.recoger(); // Ocultamos el ítem del mapa
            }
        }
    }



    // Métodos públicos para acceder a los datos del juego

    public Snake getJugador() {
        return jugador;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public int getMisionesCompletadas() {
        return misionesCompletadas;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public int getCantidadEnemigos() {
        return cantidadEnemigos;
    }

    public Enemigo[] getEnemigos() {
        return enemigos;
    }

    public Item[] getItems() {
        return items;
    }

    public int getCantidadItems() {
        return cantidadItems;
    }

    public boolean estaMisionTerminada() {
        return misionTerminada;
    }

}
