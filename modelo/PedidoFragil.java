package modelo;
import java.time.LocalDateTime;

public class PedidoFragil extends Pedido {

    public PedidoFragil(int codigo, Cliente cliente, String destino, LocalDateTime fechaHora, double pesoKg, double distanciaKm) {
        super(codigo, cliente, destino, fechaHora, pesoKg, distanciaKm);
    }

    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        return (1000 + (getDistanciaKm() * vehiculo.getCostoPorKm())) + 800;
    }

    @Override
    public boolean esFragil(){
        return true;
    }
}