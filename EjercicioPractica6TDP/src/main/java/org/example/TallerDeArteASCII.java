package org.example;

import java.util.Scanner;

public class TallerDeArteASCII {

    private char[][] lienzo;

    public TallerDeArteASCII(int alto, int ancho) {
        lienzo = new char[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                lienzo[i][j] = ' ';
            }
        }
    }

    public void dibujarCaracter(int fila, int columna, char caracter) {
        if (fila >= 0 && fila < lienzo.length && columna >= 0 && columna < lienzo[0].length) {
            lienzo[fila][columna] = caracter;
        }
    }

    public void dibujarRectangulo(int fila1, int columna1, int fila2, int columna2, char caracter) {
        for (int i = Math.min(fila1, fila2); i <= Math.max(fila1, fila2); i++) {
            for (int j = Math.min(columna1, columna2); j <= Math.max(columna1, columna2); j++) {
                dibujarCaracter(i, j, caracter);
            }
        }
    }

    public void cambiarColor(int fila, int columna, char nuevoCaracter) {
        dibujarCaracter(fila, columna, nuevoCaracter);
    }

    public void mostrarLienzo() {
        for (char[] fila : lienzo) {
            for (char c : fila) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TallerDeArteASCII taller = new TallerDeArteASCII(10, 40);

        int opcion;
        do {
            System.out.println("1. Dibujar Carácter");
            System.out.println("2. Dibujar Rectángulo");
            System.out.println("3. Cambiar Carácter");
            System.out.println("4. Mostrar Lienzo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Fila: ");
                    int fila = scanner.nextInt();
                    System.out.print("Columna: ");
                    int columna = scanner.nextInt();
                    System.out.print("Carácter: ");
                    char caracter = scanner.next().charAt(0);
                    taller.dibujarCaracter(fila, columna, caracter);
                    break;
                case 2:
                    System.out.print("Fila esquina 1: ");
                    int fila1 = scanner.nextInt();
                    System.out.print("Columna esquina 1: ");
                    int columna1 = scanner.nextInt();
                    System.out.print("Fila esquina 2: ");
                    int fila2 = scanner.nextInt();
                    System.out.print("Columna esquina 2: ");
                    int columna2 = scanner.nextInt();
                    System.out.print("Carácter para dibujar: ");
                    char rectCaracter = scanner.next().charAt(0);
                    taller.dibujarRectangulo(fila1, columna1, fila2, columna2, rectCaracter);
                    break;
                case 3:
                    System.out.print("Fila: ");
                    int cfila = scanner.nextInt();
                    System.out.print("Columna: ");
                    int ccolumna = scanner.nextInt();
                    System.out.print("Nuevo Carácter: ");
                    char nuevoCaracter = scanner.next().charAt(0);
                    taller.cambiarColor(cfila, ccolumna, nuevoCaracter);
                    break;
                case 4:
                    taller.mostrarLienzo();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
}
