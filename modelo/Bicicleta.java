package modelo;
public class Bicicleta extends Vehiculo {

    public Bicicleta(String patente) {
        super(patente, 15.0, 10.0, 50.0);
    }

    @Override
    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmh()) * 60;
    }

    @Override
    public boolean puedeTransportar(Pedido p) {
        return !p.esFragil() && super.puedeTransportar(p);
    }
}