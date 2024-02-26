package org.example;

import java.util.Random;

public class TablaAleatoria {

    // Función que crea una tabla aleatoria de enteros
    public static int[] crearTablaAleatoria(int tamaño, int mínimo, int maximo) {
        Random rand = new Random();
        int[] tabla = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            // Genera un entero aleatorio entre 1 y 100 y lo agrega la tabla
            //Para incluir el máximo, se agrega 1 a la diferencia.
            tabla[i] = rand.nextInt(maximo - mínimo + 1) + mínimo;
        }
        return tabla;
    }

    // Función para encontrar el valor mas alto de una tabla
    public static int encontrarMayor(int[] tabla) {
        int mayor = tabla[0];
        for (int valor : tabla) {
            if (valor > mayor) {
                mayor = valor;
            }
        }
        return mayor;
    }

    // Algoritmo principal
    public static void main(String[] args) {
        int[] tabla = crearTablaAleatoria(10, 1, 100);
        int mayor = encontrarMayor(tabla);

        // Imprimir la tabla y el mayor valor encontrado
        System.out.print("Tabla: ");
        for (int valor : tabla) {
            System.out.print(valor + " ");
        }
        System.out.println("- Mayor valor: " + mayor);
    }
}
