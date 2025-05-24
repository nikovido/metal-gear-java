// Clase main
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== Menú principal =====");
            System.out.println("Escape de la base");
            System.out.println("1 - Iniciar misión");
            System.out.println("2 - Guardar estado (no implementado)");
            System.out.println("3 - Cargar estado (no implementado)");
            System.out.println("0 - Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    juego.iniciarMision();
                    // realiza el bucle de turnos hasta completar la mision
                    while (!juego.estaMisionTerminada()) {
                        juego.turnoJugador(scanner);
                        juego.ejecutarTurno();
                    }
                }
                case "2" -> System.out.println("[Guardar] aún no implementado.");
                case "3" -> System.out.println("[Cargar] aún no implementado.");
                case "0" -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }
}
