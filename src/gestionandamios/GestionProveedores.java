package gestionandamios;

import com.gestionandamios.dao.ProveedorDAO;
import com.gestionandamios.modelo.Proveedor;
import java.util.Scanner;

public class GestionProveedores {

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        ProveedorDAO dao = new ProveedorDAO();
        int opcion;

        do {
            System.out.println("\n=== MENÚ PROVEEDORES ===");
            System.out.println("1. Insertar proveedor");
            System.out.println("2. Listar proveedores");
            System.out.println("3. Eliminar proveedor");
            System.out.println("0. Volver");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    dao.insertar(new Proveedor(nombre, telefono, direccion, correo));
                }
                case 2 -> dao.listar().forEach(p ->
                        System.out.println(p.getIdProveedor() + " - " + p.getNombre()));
                case 3 -> {
                    System.out.print("ID proveedor: ");
                    dao.eliminar(sc.nextInt());
                }
            }
        } while (opcion != 0);
    }
}
