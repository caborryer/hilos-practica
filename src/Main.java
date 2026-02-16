import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------ PROGRAMA DE EJECUCIÓN DE HILOS ------");

        // Validación del número para el conteo regresivo
        int numero;
        do {
            System.out.print("Ingrese un número entero positivo para el conteo regresivo: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.next();
                System.out.print("Ingrese un número entero positivo: ");
            }
            numero = scanner.nextInt();

            if (numero <= 0) {
                System.out.println("El número debe ser mayor que cero.");
            }

        } while (numero <= 0);

        // Validación de letra
        char letraFinal;
        do {
            System.out.print("Ingrese una letra mayúscula entre A y Z: ");
            letraFinal = scanner.next().toUpperCase().charAt(0);

            if (letraFinal < 'A' || letraFinal > 'Z') {
                System.out.println("Error: Letra inválida. Intente nuevamente.");
            }

        } while (letraFinal < 'A' || letraFinal > 'Z');

        // Creación de los hilos
        Thread hiloConteo = new Thread(new ConteoRegresivo(numero), "Conteo");
        Thread hiloLetras = new Thread(new MostrarLetras(letraFinal), "Letras");

        // Inicio de los hilos
        hiloConteo.start();
        hiloLetras.start();

        scanner.close();
    }
}

// Clase que implementa el primer hilo
class ConteoRegresivo implements Runnable {

    private int numero;

    public ConteoRegresivo(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println("\n--- Inicio del hilo Conteo ---");

        for (int i = numero; i >= 0; i--) {
            System.out.println("Hilo Conteo: " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo Conteo.");
            }
        }

        System.out.println("Trabajo del hilo Conteo terminado");
    }
}

// Clase que implementa el segundo hilo
class MostrarLetras implements Runnable {

    private char letraFinal;

    public MostrarLetras(char letraFinal) {
        this.letraFinal = letraFinal;
    }

    @Override
    public void run() {
        System.out.println("\n--- Inicio del hilo Letras ---");

        for (char c = 'A'; c <= letraFinal; c++) {
            System.out.println("Hilo Letras: " + c);

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo Letras.");
            }
        }

        System.out.println("Trabajo del hilo Letras terminado");
    }
}
