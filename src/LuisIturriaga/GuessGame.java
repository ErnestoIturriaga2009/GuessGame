package LuisIturriaga;

import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class GuessGame {

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private int numeroUsuario;
    private int numeroRandom;
    private int intentos;
    private int numeroIntentos = 5;
    private Timer timer;

    public void startGame() {
        numeroRandom = random.nextInt(1, 10);
        intentos = 0;
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Se ha acabado el tiempo");
                endGame();
            }
        };
        timer.schedule(timerTask, 15000);

        playGame();
    }

    private void playGame() {
        System.out.println("Tienes 5 intentos para acertar el numero");

        while (intentos < numeroIntentos) {
            try {
                System.out.println("Ingresa un numero del 1 al 10: ");
                numeroUsuario = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (numeroUsuario == numeroRandom) {
                    System.out.printf("El numero que ingresaste es correcto: %d", numeroRandom);
                    endGame();
                    return;
                } else {
                    intentos++;
                    System.out.println("Incorrecto, te quedan " + (numeroIntentos - intentos) + " intentos");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduzca un numero entero valido.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        System.out.println("Se han acabado tus intentos");
        endGame();
    }

    private void endGame() {
        timer.cancel();
        timer.purge();
        scanner.close();
    }

    public static void main(String[] args) {
        GuessGame game = new GuessGame();
        game.startGame();
    }
}
