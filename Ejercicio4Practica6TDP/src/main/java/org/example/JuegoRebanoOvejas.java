package org.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class JuegoRebanoOvejas {
    private static final char OVEJA = 'O';
    private static final char VACIO = ' ';
    private static char[][] tablero;
    private static final int TAMANO = 5; // Tamaño del tablero

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tablero = crearTableroInicial();

        System.out.println("¡Bienvenido al Juego del Rebaño de Ovejas!");

        while (!esConfiguracionFinal()) {
            mostrarTablero();
            try {
                System.out.println("\nIngrese su movimiento ,las casillas van de 0 a 4 : ");
                int filaInicial = scanner.nextInt();
                int columnaInicial = scanner.nextInt();
                int filaFinal = scanner.nextInt();
                int columnaFinal = scanner.nextInt();

                realizarMovimiento(filaInicial, columnaInicial, filaFinal, columnaFinal);
                System.out.println("¡Movimiento exitoso!");
            } catch (MovimientoInvalidoException e) {
                System.out.println(e.getMessage());
                System.out.println("Oops... ese movimiento no es válido. ¡Inténtalo de nuevo!");
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese números válidos para las coordenadas.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        System.out.println("¡Felicidades! Has logrado alcanzar la configuración objetivo. Las ovejas te lo agradecen.");
    }

    private static char[][] crearTableroInicial() {
        char[][] tablero = new char[TAMANO][TAMANO];
        // Inicializar el tablero con celdas vacías y algunas ovejas
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = VACIO; // Celda vacía
            }
        }
        // Colocar algunas ovejas en el tablero de manera arbitraria para el inicio del juego
        tablero[1][1] = OVEJA;
        tablero[2][3] = OVEJA;
        tablero[4][4] = OVEJA;
        return tablero;
    }

    private static void mostrarTablero() {
        System.out.print("  ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void realizarMovimiento(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) throws MovimientoInvalidoException {
        if (!esMovimientoValido(filaInicial, columnaInicial, filaFinal, columnaFinal)) {
            throw new MovimientoInvalidoException("El movimiento es inválido.");
        }
        // Mover la oveja
        tablero[filaFinal][columnaFinal] = OVEJA;
        tablero[filaInicial][columnaInicial] = VACIO;
    }

    private static boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        // Añadir validaciones específicas: rango del tablero, movimiento lógico de ovejas, etc.
        return filaInicial >= 0 && filaInicial < TAMANO &&
                columnaInicial >= 0 && columnaInicial < TAMANO &&
                filaFinal >= 0 && filaFinal < TAMANO &&
                columnaFinal >= 0 && columnaFinal < TAMANO &&
                tablero[filaInicial][columnaInicial] == OVEJA &&
                tablero[filaFinal][columnaFinal] == VACIO;
    }

    private static boolean esConfiguracionFinal() {
        boolean[][] visitado = new boolean[TAMANO][TAMANO];
        boolean encontrado = false;
        int contadorOvejas = 0;
        int ovejasVisitadas = 0;

        // Contar ovejas y encontrar la primera oveja para empezar la búsqueda
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] == OVEJA) {
                    contadorOvejas++;
                    if (!encontrado) {
                        // Iniciar DFS o BFS desde la primera oveja encontrada
                        ovejasVisitadas = contarOvejasConectadas(i, j, visitado);
                        encontrado = true;
                    }
                }
            }
        }

        // Verificar si el número de ovejas visitadas es igual al número total de ovejas
        return contadorOvejas == ovejasVisitadas;
    }

    // Función auxiliar para contar ovejas conectadas usando DFS
    private static int contarOvejasConectadas(int fila, int columna, boolean[][] visitado) {
        if (fila < 0 || fila >= TAMANO || columna < 0 || columna >= TAMANO || visitado[fila][columna] || tablero[fila][columna] != OVEJA) {
            return 0;
        }

        visitado[fila][columna] = true;
        int cuenta = 1; // Contar esta oveja

        // Explorar en todas las direcciones: arriba, abajo, izquierda, derecha
        cuenta += contarOvejasConectadas(fila + 1, columna, visitado);
        cuenta += contarOvejasConectadas(fila - 1, columna, visitado);
        cuenta += contarOvejasConectadas(fila, columna + 1, visitado);
        cuenta += contarOvejasConectadas(fila, columna - 1, visitado);

        return cuenta;
    }

}

class MovimientoInvalidoException extends Exception {
    public MovimientoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
