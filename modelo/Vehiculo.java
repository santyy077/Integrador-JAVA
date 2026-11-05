package modelo;
public abstract class Vehiculo{
    private String patente;
    private double velocidadKmh;
    private double capacidadKg;
    private double costoPorKm;

    public Vehiculo(String patente, double velocidadKmh, double capacidadKg, double costoPorKm) {
        this.patente = patente;
        this.velocidadKmh = velocidadKmh;
        this.capacidadKg = capacidadKg;
        this.costoPorKm = costoPorKm;
    }

    public abstract double calcularTiempoEntrega(double km);

    public boolean puedeTransportar(Pedido pedido) {
        return pedido.getPesoKg() <= capacidadKg;
    }


    

public String getPatente() {
    return patente;
}

public double getVelocidadKmh() {
    return velocidadKmh;
}

 public double getCapacidadKg() {
    return capacidadKg;
}

public double getCostoPorKm() {
    return costoPorKm;
}

   //SETTERS
public void setPatente (String patente) {
    if (patente == null ) {
        System.out.println ("El nombre no puede estar vacio");
    } else {
    this.patente = patente;
 }
 }

public void setVelocidadKmh(double velocidadKmh) {
    if (velocidadKmh > 0) {
        this. velocidadKmh = velocidadKmh;
}    else {
    
       System.out.println ("El codigo debe ser un numero mayor que 0");
}
}

public void setCapacidadKg(double capacidadKg) {
    if (capacidadKg < 0) {
        System.out.println ("La capacidad no puede ser menor que 0");
    } else {
        this.capacidadKg = capacidadKg;
}
}

public void setCostoPorKm(double costoPorKm) {
    if (costoPorKm < 0) {
        System.out.println ("El costo no puede ser menor a 0");
    } else {
        this.costoPorKm = costoPorKm;
    }    
}
}
