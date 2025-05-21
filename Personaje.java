// Clase abstracta para todos los personajes:
public abstract class Personaje {
    
    protected String tipo; // Tipo: del personaje Snake, Guardia, MetalGear,etc.
    protected String nombre; // Nombre: de fantasía (Solid Snake, Guardia N°1, etc.
    protected int salud; // Salud: Si es menor a 0, esta muerto.
    protected Posicion posicion; // Posición: coordenadas X e Y de ubicacion en la matrix

    // Constructor: inicializamos nombre, salud y posición con this...
    public Personaje(String tipo, String nombre, int salud, Posicion posicion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.salud = salud;
        this.posicion = posicion;
    }

    // Getter: Obtenemos el tipo
    public String getTipo() {
        return tipo;
    }
    // Getter: Obtenemos el nombre
    public String getNombre() {
        return nombre;
    }

    // Getter: obtenemos la salud
    public int getSalud() {
        return salud;
    }

    // Getter: obtenemos la posicion en la matrix
    public Posicion getPosicion() {
        return posicion;
    }

    // Método: para mover al personaje (Se mueve en X y en Y)
    public void mover(int dx, int dy) {
        posicion.mover(dx, dy);
    }

    // Método: para que el personaje reciba daño y pierda salud
    public void recibirDanio(int cantidad) {
        salud -= cantidad;
        if (salud < 0) salud = 0; // Para que no sea negativa, la pasamos a 0
    }

    // Metodo: para devolver true si el personaje sigue con vida, false si esta muerto
    public boolean estaVivo() {
        return salud > 0;
    }

    // Método abstracto que cada subclase reescriba con @Override con su version propia
    public abstract void mostrarEstado();
}
