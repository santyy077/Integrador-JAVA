package app;

import java.time.LocalDateTime;
import modelo.*;
import excepciones.*;

public class Main {
    public static void main(String[] args) {

        CityLink sistema = new CityLink();

        cargarDatosDePrueba(sistema);

        Menu.mostrarMenu(sistema);
    }

    private static void cargarDatosDePrueba(CityLink sistema) {
        try {
            
            //CARGA DE 5 CLIENTES
            
            Cliente c1 = new Cliente("Juan Perez", 1, 11223344, "Centro");
            Cliente c2 = new Cliente("Maria Gomez", 2, 55667788, "Norte");
            Cliente c3 = new Cliente("Carlos Lopez", 3, 44332211, "Sur");
            Cliente c4 = new Cliente("Ana Martinez", 4, 99887766, "Este");
            Cliente c5 = new Cliente("Lucas Rodriguez", 5, 11992288, "Oeste");

            sistema.registrarCliente(c1);
            sistema.registrarCliente(c2);
            sistema.registrarCliente(c3);
            sistema.registrarCliente(c4);
            sistema.registrarCliente(c5);

            
            // CARGA DE 3 VEHiCULOS Y 3 REPARTIDORES
            
            // Repartidor 1: Bicicleta 
            Vehiculo v1 = new Bicicleta("BICI-01");
            Repartidor r1 = new Repartidor(101, "Roberto Cadete", v1);
            sistema.registrarVehiculo(v1);
            sistema.registrarRepartidor(r1);

            // Repartidor 2: Moto 
            Vehiculo v2 = new Moto("MOTO-02", 150);
            Repartidor r2 = new Repartidor(102, "Esteban Chofer", v2);
            sistema.registrarVehiculo(v2);
            sistema.registrarRepartidor(r2);

            // Repartidor 3: Camioneta
            Vehiculo v3 = new Camioneta("CAM-03", 500.0);
            Repartidor r3 = new Repartidor(103, "Gonzalo Flete", v3);
            sistema.registrarVehiculo(v3);
            sistema.registrarRepartidor(r3);

            
            // 3. CARGA DE 8 PEDIDOS (Normales, Urgentes y Fragiles)
           
            LocalDateTime ahora = LocalDateTime.now();

            Pedido p1 = new PedidoNormal(1001, c1, "Av. Mitre 500", ahora, 4.5, 3.0);
            Pedido p2 = new PedidoUrgente(1002, c2, "Calle San Martin 120", ahora, 2.0, 5.5);
            Pedido p3 = new PedidoFragil(1003, c3, "Av. España 890", ahora, 1.5, 4.0);
            Pedido p4 = new PedidoNormal(1004, c4, "Calle Lavalle 430", ahora, 12.0, 7.0);
            Pedido p5 = new PedidoUrgente(1005, c5, "Calle Belgrano 210", ahora, 8.0, 12.0);
            Pedido p6 = new PedidoFragil(1006, c1, "Av. Roca 750", ahora, 5.0, 2.5);
            Pedido p7 = new PedidoNormal(1007, c2, "Calle Junin 340", ahora, 45.0, 15.0);
            Pedido p8 = new PedidoUrgente(1008, c3, "Av. Pringles 1100", ahora, 250.0, 20.0);

            sistema.crearPedido(p1);
            sistema.crearPedido(p2);
            sistema.crearPedido(p3);
            sistema.crearPedido(p4);
            sistema.crearPedido(p5);
            sistema.crearPedido(p6);
            sistema.crearPedido(p7);
            sistema.crearPedido(p8);

            // Asignaciones de prueba iniciales
            sistema.asignarPedido(1001, "BICI-01");
            sistema.asignarPedido(1004, "MOTO-02");

            System.out.println(" Datos de prueba cargados correctamente:");
            System.out.println("   - 5 Clientes registrados.");
            System.out.println("   - 3 Repartidores y Vehiculos registrados.");
            System.out.println("   - 8 Pedidos precargados.");

        } catch (AlmacenamientoLlenoException e) {
            System.err.println("Error de almacenamiento al cargar datos: " + e.getMessage());
        } catch (CapacidadExcedidaException | PedidoInvalidoException | RepartidorNoDisponibleException e) {
            System.err.println("Error de validacion al cargar datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en la carga inicial: " + e.getMessage());
        }
    }
}