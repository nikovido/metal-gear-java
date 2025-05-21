// Clase pricipal de Juego que orquesta todo
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

    // Constructor: prepara todo lo necesario, pero no carga mapa ni personajes todavía
    public Juego() {
        this.misionesCompletadas = 0; // Empezamos desde la misión 1
        this.turnoActual = 0;

        // Creamos el espacio para enemigos e items (tamaño fisico para inicializar)
        this.enemigos = new Enemigo[10];
        this.items = new Item[20];
        this.cantidadEnemigos = 0;
        this.cantidadItems = 0;
    }

    // Iniciar misión: según cuántas se completaron, carga la misión siguiente
    public void iniciarMision() {
        if (misionesCompletadas == 0) {
            System.out.println("Iniciando Misión 1...");
            //MisionIntermedia m1 = new MisionIntermedia();
            //m1.configurarMision(this, 1); // se le pasa el juego para que lo configure

        } else if (misionesCompletadas == 1) {
            System.out.println("Iniciando Misión 2...");
            //MisionIntermedia m2 = new MisionIntermedia();
            //m2.configurarMision(this, 2);

        } else if (misionesCompletadas == 2) {
            System.out.println("Iniciando Misión Final...");
            //MisionFinal finalBattle = new MisionFinal();
            //finalBattle.configurarMision(this);

        } else {
            System.out.println("¡Todas las misiones han sido completadas!");
        }
    }

    // Ejecutamos el turno del juego: controla patrullas, ataques y recogida de ítems
    public void ejecutarTurno() {
        turnoActual++; // Aumentamos el contador de turnos

        // Recorremos todos los enemigos cargados
        for (int i = 0; i < cantidadEnemigos; i++) {
            Enemigo enemigo = enemigos[i];

            // Si es un guardia, patrulla y ataca si detecta a Snake
            if (enemigo instanceof Guardia) {
                enemigo.patrullar();

                if (enemigo.detectar(jugador)) {
                    enemigo.atacar(jugador);
                }
            }
        }

        // Verificamos si Snake está en una celda con un ítem visible
        Celda celdaActual = mapa.getCelda(jugador.getPosicion().getX(), jugador.getPosicion().getY());

        if (celdaActual.tieneItem()) {
            Item item = celdaActual.getItem();
            String tipo = item.getTipo();
        
            if (tipo.equals("racion")) {
                jugador.sumarVida(item.getValor());
        
            } else if (tipo.equals("explosivo")) {
                System.out.println("Has obtenido un " + item.getNombre());
                jugador.setNivelC4(jugador.getNivelC4()+1);
        
            } else if (tipo.equals("tarjeta")) {
                System.out.println("Has obtenido una tarjeta: " + item.getNombre());
                jugador.setNivelTarjeta(item.getValor()); // el valor indica el nivel
            }
            item.recoger(); // lo ocultamos del mapa
        }        

        // Mostramos estado de Snake por consola
        jugador.mostrarEstado();
    }

    // Marca que una misión se completó (se llama desde la clase Mision cuando termina)
    public void completarMision() {
        misionesCompletadas++;
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
}
