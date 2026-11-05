package modelo;
public class Moto extends Vehiculo {

public  Moto(String patente, Double capacidadKg, Double velocidadKmh, Double costoPorKm){
super(patente,velocidadKmh,capacidadKg,costoPorKm);

}
public Moto(String patenteRepartidor, int i) {
super(patenteRepartidor, 60.0, 150.0, 200.0);
}
@Override
public double calcularTiempoEntrega(double km){

double tiempoMin = (km/ getVelocidadKmh()) * 60;
return tiempoMin + 5.0;

}
}