// Clase que representa una celda de la matriz (mapa)
public class Celda {

    private Personaje personaje; // Puede ser Snake, Guardia, etc.
    private Item item;           // Puede ser tarjeta, explosivo, etc

    // Constructor: por defecto está vacía
    public Celda() {
        this.personaje = null;
        this.item = null;
    }

    // Método: para saber si la celda esta vacia
    public boolean estaVacia() {
        return personaje == null && (item == null || !item.esVisible());
    }
    // Método: para saber si tiene personaje
    public boolean tienePersonaje() {
        return personaje != null;
    }
     // Método: para saber si tiene item
    public boolean tieneItem() {
        return item != null && item.esVisible();
    }

    // Getter: obtenemos el personaje
    public Personaje getPersonaje() {
        return personaje;
    }
    // Setter: fijamos el personaje
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    // Getter: obtenemos el item
    public Item getItem() {
        return item;
    }
    // Setter: fijamos el item
    public void setItem(Item item) {
        this.item = item;
    }

    // Método para vaciar la celda del personaje (cuando se mueve)
    public void removerPersonaje() {
        this.personaje = null;
    }

    // Método para agarrar ítems (se ocultan)
    public void recogerItem() {
        if (item != null) {
            item.recoger();
        }
    }
}
