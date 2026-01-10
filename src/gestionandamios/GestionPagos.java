package gestionandamios;

import com.gestionandamios.dao.PagoDAO;
import com.gestionandamios.modelo.Pago;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GestionPagos {

    public static void menu() {

        Scanner sc = new Scanner(System.in);
        PagoDAO dao = new PagoDAO();
        int opcion;

        do {
            System.out.println("\n=== MENÚ PAGOS ===");
            System.out.println("1. Insertar pago");
            System.out.println("2. Listar pagos");
            System.out.println("3. Actualizar pago");
            System.out.println("4. Eliminar pago");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("ID alquiler: ");
                    int idAlquiler = sc.nextInt();

                    System.out.print("Monto: ");
                    double monto = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Fecha pago (YYYY-MM-DD): ");
                    Date fecha = Date.valueOf(sc.nextLine());

                    System.out.print("Método de pago: ");
                    String metodo = sc.nextLine();

                    Pago pago = new Pago(idAlquiler, monto, fecha, metodo);
                    dao.insertar(pago);
                }

                case 2 -> {
                    List<Pago> pagos = dao.listar();
                    if (pagos.isEmpty()) {
                        System.out.println("⚠ No hay pagos registrados");
                    } else {
                        for (Pago p : pagos) {
                            System.out.println(
                                p.getIdPago() + " | Alquiler: " +
                                p.getIdAlquiler() + " | $" +
                                p.getMonto() + " | " +
                                p.getFechaPago() + " | " +
                                p.getMetodoPago()
                            );
                        }
                    }
                }

                case 3 -> {
                    Pago p = new Pago();

                    System.out.print("ID del pago a actualizar: ");
                    p.setIdPago(sc.nextInt());

                    System.out.print("Nuevo ID alquiler: ");
                    p.setIdAlquiler(sc.nextInt());

                    System.out.print("Nuevo monto: ");
                    p.setMonto(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Nueva fecha pago (YYYY-MM-DD): ");
                    p.setFechaPago(Date.valueOf(sc.nextLine()));

                    System.out.print("Nuevo método de pago: ");
                    p.setMetodoPago(sc.nextLine());

                    dao.actualizar(p);
                }

                case 4 -> {
                    System.out.print("ID del pago a eliminar: ");
                    int id = sc.nextInt();
                    dao.eliminar(id);
                }
            }

        } while (opcion != 0);
    }
}
