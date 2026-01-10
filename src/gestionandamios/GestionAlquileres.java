package gestionandamios;

import com.gestionandamios.dao.AlquilerDAO;
import com.gestionandamios.modelo.Alquiler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GestionAlquileres {

    public static void menu() {

        AlquilerDAO dao = new AlquilerDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ ALQUILERES ===");
            System.out.println("1. Insertar alquiler");
            System.out.println("2. Listar alquileres");
            System.out.println("3. Actualizar alquiler");
            System.out.println("4. Eliminar alquiler");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    Alquiler a = new Alquiler();

                    System.out.print("ID Cliente: ");
                    a.setIdCliente(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Fecha inicio (YYYY-MM-DD): ");
                    a.setFechaInicio(Date.valueOf(sc.nextLine()));

                    System.out.print("Fecha fin estimada (YYYY-MM-DD): ");
                    a.setFechaFinEstimada(Date.valueOf(sc.nextLine()));

                    System.out.print("Fecha fin real (YYYY-MM-DD): ");
                    a.setFechaFinReal(Date.valueOf(sc.nextLine()));

                    System.out.print("Costo total: ");
                    a.setCostoTotal(sc.nextDouble());

                    dao.insertar(a);
                    System.out.println("✔ Alquiler registrado");
                    break;

                case 2:
                    System.out.println("\n--- LISTADO DE ALQUILERES ---");

                    List<Alquiler> lista = dao.listar();

                    if (lista.isEmpty()) {
                        System.out.println("⚠ No hay alquileres registrados.");
                    } else {
                        for (Alquiler al : lista) {
                            System.out.println(
                                "ID: " + al.getIdAlquiler() +
                                " | Cliente: " + al.getIdCliente() +
                                " | Inicio: " + al.getFechaInicio() +
                                " | Total: $" + al.getCostoTotal()
                            );
                        }
                    }

                    System.out.println("-----------------------------");
                    System.out.print("Presione ENTER para continuar...");
                    sc.nextLine(); // PAUSA PARA QUE SE VEA
                    break;

                case 3:
                    Alquiler au = new Alquiler();

                    System.out.print("ID alquiler: ");
                    au.setIdAlquiler(sc.nextInt());

                    System.out.print("Nuevo ID cliente: ");
                    au.setIdCliente(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Nueva fecha inicio (YYYY-MM-DD): ");
                    au.setFechaInicio(Date.valueOf(sc.nextLine()));

                    System.out.print("Nueva fecha fin estimada (YYYY-MM-DD): ");
                    au.setFechaFinEstimada(Date.valueOf(sc.nextLine()));

                    System.out.print("Nueva fecha fin real (YYYY-MM-DD): ");
                    au.setFechaFinReal(Date.valueOf(sc.nextLine()));

                    System.out.print("Nuevo costo total: ");
                    au.setCostoTotal(sc.nextDouble());

                    dao.actualizar(au);
                    System.out.println("✔ Alquiler actualizado");
                    break;

                case 4:
                    System.out.print("ID alquiler a eliminar: ");
                    dao.eliminar(sc.nextInt());
                    System.out.println("✔ Alquiler eliminado");
                    break;
            }

        } while (opcion != 0);
    }
}
