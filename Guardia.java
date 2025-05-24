// Importamos el random
import java.util.Random;
// Clase que representa a Personaje : Guardia
public class Guardia extends Personaje implements Enemigo {
    // Constructor: el tipo se fija como "Guardia"
    // Usamos super para usar clase padre
    public Guardia(String nombre, int salud, Posicion posicion) {
        super("Guardia", nombre, salud, posicion);
    }

    // Reescribimos como mostramos el estado de Guardia (con coordenadas)
    @Override
    public void mostrarEstado() {
        System.out.println("[" + tipo + "] " + nombre + " - Salud: " + salud + " - Posición: (" + posicion.getX() + ", " + posicion.getY() + ")");
    }
    // Reescribimos la funcion de patrullar()
    @Override
    public void patrullar() {
        Random rand = new Random(); // Inicializamos el random
        int direccion = rand.nextInt(4) + 1; // nextInt para enteros, devuelve 1, 2, 3 o 4, las opciones para moverse en la matriz
        switch (direccion) { // Asignamos el movimiento segun el random
            case 1 -> mover(-1, 0); // IZQUIERDA
            case 2 -> mover(0, -1); // ARRIBA
            case 3 -> mover(1, 0);  // DERECHA
            case 4 -> mover(0, 1);  // ABAJO
        }
    }

    public int[] getMovimientoAleatorio() {
        Random rand = new Random();
        int direccion = rand.nextInt(4) + 1;

        return switch (direccion) {
            case 1 -> new int[]{-1, 0}; // Izquierda
            case 2 -> new int[]{0, -1}; // Arriba
            case 3 -> new int[]{1, 0};  // Derecha
            case 4 -> new int[]{0, 1};  // Abajo
            default -> new int[]{0, 0}; // Sin movimiento
        };
    }


    // Reescribimos la funcion de atacar()
    @Override
    public void atacar(Personaje objetivo) {
        // Ataque simple basico: resta 10 vida
        System.out.println("[" + tipo + "] " + nombre + " ataca a " +  "[" + objetivo.getTipo() + "] " +objetivo.getNombre() + "!");
        objetivo.recibirDanio(10);
    }
    // Reescribimos la funcion detectar()
    @Override
    public boolean detectar(Personaje objetivo) {
        int dx = Math.abs(objetivo.getPosicion().getX() - this.getPosicion().getX());
    int dy = Math.abs(objetivo.getPosicion().getY() - this.getPosicion().getY());

    // A distancia 1 en eje horizontal o vertical (no diagonal)
    return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }
}
