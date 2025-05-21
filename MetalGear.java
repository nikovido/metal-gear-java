// Importamos random
import java.util.Random;
// Clase que representa a Personaje : MetalGear (Enemigo)
public class MetalGear extends Personaje implements Enemigo {
    // Declaaramos Random
    private Random rand;

    // Constructor, le asignamos MetalGear como tipo de Personaje
    // usamos (super) para acceder al padre
    public MetalGear(String nombre, int salud, Posicion posicion) {
        super("MetalGear", nombre, salud, posicion);
        this.rand = new Random();
    }
    // Mostramos el estado (sin coordenadas, por el modo batalla)
    @Override
    public void mostrarEstado() {
        System.out.println("[" + tipo + "] " + nombre + " - Salud: " + salud);
    }
    // Reescribimos la funcion patrullar
    @Override
    public void patrullar() {
        // Metal Gear no patrulla. Está fijo en la batalla final
    }
    //  Reescribimos funcion: Hacemos un random para elegir el tipo de ataque (misil/laser)
    @Override
    public void atacar(Personaje objetivo) {
        int tipoAtaque = rand.nextInt(2); // 0 o 1
        if (tipoAtaque == 0) {
            atacarConMisil(objetivo);
        } else {
            atacarConLaser(objetivo);
        }
    }
    // Ataque de misil... causa 20 daño
    private void atacarConMisil(Personaje objetivo) {
        System.out.println("[" + tipo + "] " + nombre + " dispara un misil a " + objetivo.getNombre());
        objetivo.recibirDanio(20); // Daño fijo o random?
    }
    // Ataque con laser, causa 30 de daño
    private void atacarConLaser(Personaje objetivo) {
        System.out.println("[" + tipo + "] " + nombre + " dispara su cañón láser a " + objetivo.getNombre());
        objetivo.recibirDanio(30); // Láser más poderoso
    }
    // Reescribimos funcion, siempre esta en modo batalla
    @Override
    public boolean detectar(Personaje objetivo) {
        // Asumimos que ya está en modo batalla...
        return true;
    }
}
