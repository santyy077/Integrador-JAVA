import java.util.Scanner;
import java.time.LocalDateTime;
import excepciones.*;
import modelo.*;

public class Menu {

    public static void mostrarMenu(CityLink sistema) {
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n===============================================");
            System.out.println("                 --CityLink--                 ");
            System.out.println("===============================================");
            System.out.println("[1]  - Registrar cliente");
            System.out.println("[2]  - Registrar repartidor con su vehiculo");
            System.out.println("[3]  - Registrar pedido");
            System.out.println("[4]  - Asignar pedido a repartidor");
            System.out.println("[5]  - Cambiar estado de un pedido");
            System.out.println("[6]  - Buscar cliente");
            System.out.println("[7]  - Listar pedidos (costo y tiempo)");
            System.out.println("[8]  - Consultar estado (Rastreable)");
            System.out.println("[9]  - Ver importe y comision (Facturable)");
            System.out.println("[10] - Salir");
            System.out.println("===============================================");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1: {
                        System.out.println("\n========== REGISTRAR CLIENTE ==========");
                        System.out.print("Ingrese el codigo del cliente: ");
                        int codigoClienteRegistro = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombreCliente = scanner.nextLine();
                        System.out.print("Ingrese el numero de telefono del cliente: ");
                        int tellCliente = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese la zona del cliente: ");
                        String zonaCliente = scanner.nextLine();

                        Cliente nuevoCliente = new Cliente(nombreCliente, codigoClienteRegistro, tellCliente, zonaCliente);
                        sistema.registrarCliente(nuevoCliente);
                        System.out.println("-> ¡Cliente registrado exitosamente!");
                        break;
                    }
                    case 2: {
                        System.out.println("\n========== REGISTRAR REPARTIDOR Y VEHICULO ==========");
                        System.out.print("Ingrese el legajo del repartidor: ");
                        int legajoRepartidor = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese el nombre del repartidor: ");
                        String nombreRepartidor = scanner.nextLine();
                        System.out.print("Ingrese la patente del vehiculo: ");
                        String patenteRepartidor = scanner.nextLine();

                        System.out.print("Seleccione tipo de vehiculo (1-Bicicleta, 2-Moto, 3-Camioneta): ");
                        int tipoV = Integer.parseInt(scanner.nextLine());
                        Vehiculo nuevoVehiculo = null;

                        if (tipoV == 1) {
                            nuevoVehiculo = new Bicicleta(patenteRepartidor);
                        } else if (tipoV == 2) {
                            nuevoVehiculo = new Moto(patenteRepartidor, 150);
                        } else {
                            System.out.println("Opcion de vehiculo invalida.");
                        }

                        if (nuevoVehiculo != null) {
                            sistema.registrarVehiculo(nuevoVehiculo);
                            Repartidor nuevoRepartidor = new Repartidor(legajoRepartidor, nombreRepartidor, nuevoVehiculo);
                            sistema.registrarRepartidor(nuevoRepartidor);
                            System.out.println("-> ¡Repartidor y vehiculo registrados exitosamente!");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\n========== REGISTRAR PEDIDO ==========");
                        System.out.print("Ingrese el codigo del pedido: ");
                        int codigoPedido = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese el codigo del cliente: ");
                        int codigoClientePedido = Integer.parseInt(scanner.nextLine());

                        Cliente clienteBuscar = sistema.buscarCliente(codigoClientePedido);

                        System.out.print("Ingrese el destino del pedido: ");
                        String destinoPedido = scanner.nextLine();
                        System.out.print("Ingrese el peso del pedido (kg): ");
                        System.out.print("Bici (Max 10kg) Moto (Max 30kg) Camioneta(Max 500kg)");
                        double pesoPedido = Double.parseDouble(scanner.nextLine());
                        System.out.print("Ingrese la distancia del pedido (km): ");
                        double distanciaPedido = Double.parseDouble(scanner.nextLine());

                        System.out.print("Seleccione tipo de pedido (1-Normal, 2-Urgente, 3-Fragil): ");
                        int tipoPedido = Integer.parseInt(scanner.nextLine());

                        Pedido nuevoPedido = null;
                        LocalDateTime fechaActual = LocalDateTime.now();

                        if (tipoPedido == 1) {
                            nuevoPedido = new PedidoNormal(codigoPedido, clienteBuscar, destinoPedido, fechaActual, pesoPedido, distanciaPedido);
                        } else if (tipoPedido == 2) {
                            nuevoPedido = new PedidoUrgente(codigoPedido, clienteBuscar, destinoPedido, fechaActual, pesoPedido, distanciaPedido);
                        } else if (tipoPedido == 3) {
                            nuevoPedido = new PedidoFragil(codigoPedido, clienteBuscar, destinoPedido, fechaActual, pesoPedido, distanciaPedido);
                        } else {
                            System.out.println("Tipo de pedido invalido.");
                        }

                        if (nuevoPedido != null) {
                            sistema.crearPedido(nuevoPedido);
                            System.out.println("-> ¡Pedido registrado exitosamente!");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\n========== ASIGNAR PEDIDO ==========");
                        System.out.print("Ingrese el codigo del pedido: ");
                        int codAsig = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese la patente del vehiculo: ");
                        String patenteAsig = scanner.nextLine();

                        sistema.asignarPedido(codAsig, patenteAsig);
                        System.out.println("-> ¡Pedido asignado y estado cambiado a EN_CAMINO!");
                        break;
                    }
                    case 5: {
                        System.out.println("\n========== CAMBIAR ESTADO DE PEDIDO ==========");
                        System.out.print("Ingrese el codigo del pedido: ");
                        int codEst = Integer.parseInt(scanner.nextLine());
                        Pedido pEst = sistema.buscarPedido(codEst);

                        System.out.print("Ingrese nuevo estado (PENDIENTE, EN_CAMINO, ENTREGADO, CANCELADO): ");
                        String nuevoEst = scanner.nextLine().toUpperCase();
                        pEst.cambiarEstado(nuevoEst);
                        System.out.println("-> Estado actualizado a: " + nuevoEst);
                        break;
                    }
                    case 6: {
                        System.out.println("\n========== BUSCAR CLIENTE ==========");
                        System.out.print("Ingrese el codigo del cliente: ");
                        int codBus = Integer.parseInt(scanner.nextLine());
                        Cliente cBus = sistema.buscarCliente(codBus);
                        System.out.println("-> " + cBus.toString());
                        break;
                    }
                    case 7: {
                        System.out.println("\n========== LISTAR PEDIDOS ==========");
                        System.out.print("Ingrese el codigo del pedido a consultar: ");
                        int codList = Integer.parseInt(scanner.nextLine());
                        Pedido pList = sistema.buscarPedido(codList);
                        System.out.println("-> Pedido #" + pList.getCodigo() + " | Destino: " + pList.getDestino() + " | Estado: " + pList.getEstado());
                        break;
                    }
                    case 8: {
                        System.out.println("\n========== CONSULTAR ESTADO (RASTREABLE) ==========");
                        System.out.print("Ingrese el codigo del pedido: ");
                        int codRastreo = Integer.parseInt(scanner.nextLine());
                        Pedido pRastreo = sistema.buscarPedido(codRastreo);
                        System.out.println("-> Estado actual: " + pRastreo.obtenerEstadoActual());
                        break;
                    }
                    case 9: {
                        System.out.println("\n========== VER IMPORTE (FACTURABLE) ==========");
                        System.out.print("Ingrese el codigo del pedido: ");
                        int codFact = Integer.parseInt(scanner.nextLine());
                        Pedido pFact = sistema.buscarPedido(codFact);
                        System.out.println("-> Importe base del pedido: $" + pFact.calcularImporte());
                        break;
                    }
                    case 10: {
                        System.out.println("Saliendo de CityLink... ¡Hasta luego!");
                        break;
                    }
                    default: {
                        System.out.println("Opcion no valida. Seleccione un numero del 1 al 10.");
                        break;
                    }
                }

            } catch (AlmacenamientoLlenoException e) {
                System.err.println("Error de Almacenamiento: " + e.getMessage());
            } catch (ClienteNoEncontradoException e) {
                System.err.println("Error de Busqueda: " + e.getMessage());
            } catch (PedidoInvalidoException e) {
                System.err.println("Error en Pedido: " + e.getMessage());
            } catch (CapacidadExcedidaException e) {
                System.err.println("Error de Capacidad: " + e.getMessage());
            } catch (RepartidorNoDisponibleException e) {
                System.err.println("Error de Repartidor: " + e.getMessage());
            } catch (EstadoInvalidoException e) {
                System.err.println("Error de Estado: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error: Ingrese un numero valido.");
            } catch (Exception e) {
                System.err.println("Ocurrio un error inesperado: " + e.getMessage());
            }

        } while (opcion != 10);
    }
}