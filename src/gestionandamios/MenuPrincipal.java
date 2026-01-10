package gestionandamios;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE ANDAMIOS ===");
            System.out.println("1. Gestión Clientes");
            System.out.println("2. Gestión Proveedores");
            System.out.println("3. Gestión Alquileres");
            System.out.println("4. Gestión Secciones Andamio");
            System.out.println("5. Gestión Pagos");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> GestionClientes.menu();
                case 2 -> GestionProveedores.menu();
                case 3 -> GestionAlquileres.menu();
                case 4 -> GestionAndamios.menu();
                case 5 -> GestionPagos.menu();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }
}
