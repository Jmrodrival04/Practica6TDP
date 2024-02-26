package org.example;

import java.util.Scanner;

public class ComparacionNumeros {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Ingrese el primer número: ");
            double numero1 = scanner.nextDouble();

            System.out.println("Ingrese el segundo número: ");
            double numero2 = scanner.nextDouble();

            double mayor = mayorDeDos(numero1, numero2);
            int clasificacion = clasificarNumeros(numero1, numero2);

            System.out.println("El mayor es: " + mayor + " y la clasificación es: " + clasificacion);

            // Preguntar si quiere continuar o salir
            System.out.println("¿Quieres probar otros números? (sí/no): ");
            String respuesta = scanner.next(); // Leer la respuesta

            if (respuesta.equalsIgnoreCase("no")) {
                continuar = false;
            }
        }

        System.out.println("Ejercicio 1");
    }

    public static double mayorDeDos(double numero1, double numero2) {
        if (numero1 > numero2) {
            return numero1;
        }
        return numero2;
    }
//En caso de que sea mayor que el primer número será 1 en el caso contrario -1
    public static int clasificarNumeros(double numero1, double numero2) {
        if (numero1 == numero2) {
            return 0;
        } else if (numero1 > numero2) {
            return 1;
        } else {
            return -1;
        }
    }
}
