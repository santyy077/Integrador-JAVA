package modelo;
import java.util.List;
import java.util.ArrayList;

public class Repartidor implements Rastreable {
private int legajo;
private String nombre;
private Vehiculo vehiculo; 
private List<Pedido> pedidosAsignados;


public Repartidor(int legajo, String nombre, Vehiculo vehiculo ){
this.legajo = legajo;
this.nombre = nombre;
this.vehiculo = vehiculo;
this.pedidosAsignados = new ArrayList<>();
}

//metodos
@Override
public String obtenerEstadoActual(){
    if(this.pedidosAsignados.isEmpty()) { 
        return "Disponible";
    }
    else{
        return "El repartidor esta ocupado.";
    }

}
public void asignarPedido(Pedido pedido){
    this.pedidosAsignados.add(pedido);
    pedido.cambiarEstado("EN_CAMINO");
}

public double calcularComision() {
    double totalComision = 0;
    for (Pedido p : pedidosAsignados) {
        if (p.getEstado().equals("ENTREGADO")) {
            totalComision += p.calcularCosto(this.vehiculo) * 0.10;
        }
    }
    return totalComision;
}

public void registrarEntrega(Pedido pedido){
if(this.pedidosAsignados.contains(pedido)){
    pedido.cambiarEstado("ENTREGADO");
    System.out.println("Pedido" + pedido.getCodigo() + " ha sido entregado con éxito.");
}
else{
    System.out.println("Tu pedido no esta asignado a este repartidor.");
}

}
//getters

public int getLegajo(){
    return legajo;
}

public String getNombre(){
    return nombre;
}
public Vehiculo getVehiculo(){
    return vehiculo;
}
public List<Pedido> getPedidosAsignados(){
    return pedidosAsignados;
}
//Setters
public void setLegajo(int legajo){
    this.legajo = legajo;
}
public void setNombre(String nombre){
    this.nombre = nombre;
}
public void setVehiculo(Vehiculo vehiculo){
    this.vehiculo = vehiculo;
}
public void setPedidosAsignados(List<Pedido> pedidosAsignados){
    this.pedidosAsignados = pedidosAsignados;
}
}
