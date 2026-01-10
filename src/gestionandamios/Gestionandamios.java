package gestionandamios;

import com.gestionandamios.dao.SeccionesAndamioDAO;
import com.gestionandamios.modelo.SeccionAndamio;
import java.util.List;
import java.util.Scanner;

public class GestionAndamios {

    public static void menu() {

        SeccionesAndamioDAO dao = new SeccionesAndamioDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ SECCIONES ANDAMIO ===");
            System.out.println("1. Insertar sección");
            System.out.println("2. Listar secciones");
            System.out.println("3. Actualizar sección");
            System.out.println("4. Eliminar sección");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    SeccionAndamio s = new SeccionAndamio();

                    System.out.print("Código: ");
                    s.setCodigo(sc.nextLine());

                    System.out.print("Tipo: ");
                    s.setTipo(sc.nextLine());

                    System.out.print("Altura metros: ");
                    s.setAlturaMetros(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Estado: ");
                    s.setEstado(sc.nextLine());

                    System.out.print("Ubicación: ");
                    s.setUbicacion(sc.nextLine());

                    System.out.print("Precio: ");
                    s.setPrecio(sc.nextDouble());

                    dao.insertar(s);
                    break;

                case 2:
                    List<SeccionAndamio> lista = dao.listar();
                    for (SeccionAndamio sec : lista) {
                        System.out.println(
                            sec.getIdSeccion() + " | " +
                            sec.getCodigo() + " | " +
                            sec.getTipo() + " | " +
                            sec.getAlturaMetros() + "m | " +
                            sec.getEstado()
                        );
                    }
                    break;

                case 3:
                    SeccionAndamio upd = new SeccionAndamio();

                    System.out.print("ID a actualizar: ");
                    upd.setIdSeccion(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Nuevo código: ");
                    upd.setCodigo(sc.nextLine());

                    System.out.print("Nuevo tipo: ");
                    upd.setTipo(sc.nextLine());

                    System.out.print("Nueva altura: ");
                    upd.setAlturaMetros(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Nuevo estado: ");
                    upd.setEstado(sc.nextLine());

                    System.out.print("Nueva ubicación: ");
                    upd.setUbicacion(sc.nextLine());

                    System.out.print("Nuevo precio: ");
                    upd.setPrecio(sc.nextDouble());

                    dao.actualizar(upd);
                    break;

                case 4:
                    System.out.print("ID a eliminar: ");
                    dao.eliminar(sc.nextInt());
                    break;
            }

        } while (opcion != 0);
    }
}
