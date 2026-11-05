package modelo;
import java.util.ArrayList;
import excepciones.*;

public class CityLink {
    private ArrayList<Cliente> clientes;
    private static final int MAX_CLIENTES = 50;
    private ArrayList<Vehiculo> vehiculos;
    private static final int MAX_VEHICULOS = 20;
    private ArrayList<Pedido> pedidos;
    private static final int MAX_PEDIDOS = 100;
    private ArrayList<Repartidor> repartidores;
    private static final int MAX_REPARTIDORES = 20;

    public CityLink() {
        clientes = new ArrayList<>();
        vehiculos = new ArrayList<>();
        pedidos = new ArrayList<>();
        repartidores = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) throws AlmacenamientoLlenoException {
        if (clientes.size() >= MAX_CLIENTES) {
            throw new AlmacenamientoLlenoException("Se alcanzo el limite maximo de clientes.");
        }
        clientes.add(cliente);
    }

    public void registrarVehiculo(Vehiculo vehiculo) throws AlmacenamientoLlenoException {
        if (vehiculos.size() >= MAX_VEHICULOS) {
            throw new AlmacenamientoLlenoException("Se alcanzo el limite de vehiculos.");
        }
        vehiculos.add(vehiculo);
    }

    public void registrarRepartidor(Repartidor repartidor) throws AlmacenamientoLlenoException {
        if (repartidores.size() >= MAX_REPARTIDORES) {
            throw new AlmacenamientoLlenoException("Se alcanzo el limite de repartidores.");
        }
        repartidores.add(repartidor);
    }

    public void crearPedido(Pedido pedido) throws AlmacenamientoLlenoException, PedidoInvalidoException {
        if (pedidos.size() >= MAX_PEDIDOS) {
            throw new AlmacenamientoLlenoException("Se alcanzo el limite de pedidos.");
        }
        if (pedido.getPesoKg() <= 0 || pedido.getDestino() == null) {
            throw new PedidoInvalidoException("El peso no debe ser menor o igual a 0 y el destino no puede estar vacio.");
        }
        for (Pedido pedi : pedidos) {
            if (pedi.getCodigo() == pedido.getCodigo()) {
                throw new PedidoInvalidoException("El codigo no puede estar duplicado.");
            }
        }
        pedidos.add(pedido);
    }

    public Cliente buscarCliente(int codigo) throws ClienteNoEncontradoException {
        for (Cliente clie : clientes) {
            if (clie.getCodigo() == codigo) {
                return clie;
            }
        }
        throw new ClienteNoEncontradoException("No se encontro ningun cliente con el codigo " + codigo);
    }

    public Pedido buscarPedido(int codigo) throws PedidoInvalidoException {
        for (Pedido p : pedidos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        throw new PedidoInvalidoException("No se encontro ningun pedido con ese codigo " + codigo);
    }

    public Vehiculo buscarVehiculo(String patente) {
        for (Vehiculo ve : vehiculos) {
            if (ve.getPatente().equalsIgnoreCase(patente)) {
                return ve;
            }
        }
        return null;
    }

    public void asignarPedido(int codigo, String patente) throws CapacidadExcedidaException, PedidoInvalidoException, RepartidorNoDisponibleException {
        Pedido p = buscarPedido(codigo);
        Vehiculo ve = buscarVehiculo(patente);

        if (ve == null) {
            throw new RepartidorNoDisponibleException("No se encontro ningun repartidor o vehiculo con la patente " + patente);
        }
        if (!ve.puedeTransportar(p)) {
            throw new CapacidadExcedidaException("El vehiculo no soporta el peso del pedido.");
        }
        p.cambiarEstado("EN_CAMINO");
    }
}