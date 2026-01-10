package gestionandamios;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GestionClientes {

    public static void menu() {

        ClienteDAO dao = new ClienteDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ CLIENTES ===");
            System.out.println("1. Insertar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    Cliente c = new Cliente();

                    System.out.print("Nombre: ");
                    c.setNombre(sc.nextLine());

                    System.out.print("Apellido: ");
                    c.setApellido(sc.nextLine());

                    System.out.print("Cédula: ");
                    c.setCedula(sc.nextLine());

                    System.out.print("Teléfono: ");
                    c.setTelefono(sc.nextLine());

                    System.out.print("Dirección: ");
                    c.setDireccion(sc.nextLine());

                    System.out.print("Correo electrónico: ");
                    c.setCorreoElectronico(sc.nextLine());

                    System.out.print("Fecha nacimiento (YYYY-MM-DD): ");
                    c.setFechaNacimiento(Date.valueOf(sc.nextLine()));

                    System.out.print("Contraseña: ");
                    c.setContrasena(sc.nextLine());

                    dao.insertar(c);
                    break;

                case 2:
                    List<Cliente> lista = dao.listar();
                    lista.forEach(cli ->
                        System.out.println(
                            cli.getIdCliente() + " | " +
                            cli.getNombre() + " " + cli.getApellido()
                        )
                    );
                    break;

                case 3:
                    Cliente cu = new Cliente();

                    System.out.print("ID cliente: ");
                    cu.setIdCliente(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Nuevo nombre: ");
                    cu.setNombre(sc.nextLine());

                    System.out.print("Nuevo apellido: ");
                    cu.setApellido(sc.nextLine());

                    System.out.print("Nueva cédula: ");
                    cu.setCedula(sc.nextLine());

                    System.out.print("Nuevo teléfono: ");
                    cu.setTelefono(sc.nextLine());

                    System.out.print("Nueva dirección: ");
                    cu.setDireccion(sc.nextLine());

                    System.out.print("Nuevo correo: ");
                    cu.setCorreoElectronico(sc.nextLine());

                    System.out.print("Nueva fecha nacimiento: ");
                    cu.setFechaNacimiento(Date.valueOf(sc.nextLine()));

                    System.out.print("Nueva contraseña: ");
                    cu.setContrasena(sc.nextLine());

                    dao.actualizar(cu);
                    break;

                case 4:
                    System.out.print("ID a eliminar: ");
                    dao.eliminar(sc.nextInt());
                    break;
            }

        } while (opcion != 0);
    }
}
