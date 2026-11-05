package modelo;
public class Camioneta extends Vehiculo {

    public Camioneta(String patente, double velocidadkmh, double capacidadkg, double costoporkm) {
        super(patente, 30.0, 500.0, 300.0);
        
    }

    public Camioneta(String patenteRepartidor, double capKg) {
        super(patenteRepartidor, 30.0, capKg, 300.0);
    }
	

	@Override
    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmh()) * 60 + 10;
    }

}