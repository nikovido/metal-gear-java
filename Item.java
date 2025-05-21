// Clase que representa a Item (alias Objeto)
public class Item {

    private String tipo;         // Tipo de ítem: tarjeta, salud, explosivo
    private String nombre;       // Nombre: "Tarjeta de Hangar", "Explosivo C4", etc
    private Posicion posicion;   // Ubicación en la matrix
    private int valor;           // Valor asociado (tarjeta=nivel?, salud=cuanto incrementa, explosivo=daño)
    private boolean visible;     // Si el ítem está visible en el mapa

    // Constructor del item
    public Item(String tipo, String nombre, Posicion posicion, int valor) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.posicion = posicion;
        this.valor = valor;
        this.visible = true; // Por defecto, el ítem es visible al crearse
    }

    // Getter: obtenemos el tipo
    public String getTipo() {
        return tipo;
    }
    // Getter: obtenemos el nombre
    public String getNombre() {
        return nombre;
    }
    // Getter: obtenemos la posicion
    public Posicion getPosicion() {
        return posicion;
    }
    // Getter: obtenemos el valor / cantidad
    public int getValor() {
        return valor;
    }
    // Getter: obtenemos si es visible o no, true/false
    public boolean esVisible() {
        return visible;
    }

    // Cuando se recoge el ítem, lo ocultamos
    public void recoger() {
        this.visible = false;
    }

    // Mostrar el ítem en consola (sirve realmente?)
    public void mostrarItem() {
        if (visible) {
            System.out.println("Item: " + nombre + " (" + tipo + "), valor: " + valor + ", posición: (" + posicion.getX() + ", " + posicion.getY() + ")");
        }
    }
}
