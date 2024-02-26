package org.example;

public class JuegoDeTablero {

    private final int TAMANO = 4; // Tamaño del tablero 4x4
    private char[][] tablero;
    private boolean objetivoDescubierto = false;

    // Constructor
    public JuegoDeTablero() {
        tablero = new char[TAMANO][TAMANO];
        inicializarTablero();
    }

    // Lógica de inicialización
    private void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = ' '; // Inicializa todas las celdas como vacías
            }
        }
        // Colocar un objetivo en una posición aleatoria
        int fila = (int) (Math.random() * TAMANO);
        int columna = (int) (Math.random() * TAMANO);
        tablero[fila][columna] = 'O';
    }

    // Lógica para mostrar el tablero sin revelar el objetivo
    private void mostrarTablero() {
        System.out.print("  ");
        for (int i = 0; i < TAMANO; i++) {
            System.out.print((char)('A' + i) + " ");
        }
        System.out.println();

        for (int i = 0; i < TAMANO; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] == 'O' && !objetivoDescubierto) {
                    System.out.print("  "); // Oculta el objetivo
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private int introducirEnteroEntreLimites(int minimo, int maximo) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int numero;
        do {
            System.out.println("Introduce un número de fila entre " + minimo + " y " + maximo + ": ");
            numero = scanner.nextInt();
        } while (numero < minimo || numero > maximo);
        return numero - 1;
    }

    private int introducirColumna() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        char columna;
        do {
            System.out.println("Introduce una columna (A-D): ");
            columna = scanner.next().toUpperCase().charAt(0);
        } while (columna < 'A' || columna > 'D');
        return columna - 'A';
    }

    private boolean disparar(int fila, int columna) {
        if (tablero[fila][columna] == 'O') {
            tablero[fila][columna] = '*'; // Tocado
            objetivoDescubierto = true;
            return true;
        } else if (tablero[fila][columna] == ' '){
            tablero[fila][columna] = 'X'; // Disparo fallido
        }
        return false;
    }

    public void jugar() {
        while (!objetivoDescubierto) {
            mostrarTablero();
            int fila = introducirEnteroEntreLimites(1, 4);
            int columna = introducirColumna();
            boolean resultado = disparar(fila, columna);
            if (resultado) {
                System.out.println("Tocado y hundido");
                mostrarTablero(); // Revela el tablero final con el objetivo descubierto
            } else {
                System.out.println("Agua, intenta nuevamente");
            }
        }
    }

    public static void main(String[] args) {
        JuegoDeTablero juego = new JuegoDeTablero();
        juego.jugar();
    }
}
