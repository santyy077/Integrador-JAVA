package modelo;
import java.time.LocalDateTime;
import excepciones.EstadoInvalidoException; //clases incluidas en java para poder usarlas mas tarde

public abstract class Pedido implements Facturable, Rastreable {
    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado;

    // constructor
    public Pedido(int codigo, Cliente cliente, String destino,LocalDateTime fechaHora, double pesoKg, double distanciaKm) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.fechaHora = fechaHora;
        this.estado = "PENDIENTE";

    }

public boolean esFragil(){
return false;
}   

    public int getCodigo() {
        return codigo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public String getDestino() {
        return destino;
    }
    public double getPesoKg() {
        return pesoKg;
    }
    public double getDistanciaKm() {
        return distanciaKm;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public String getEstado() {
        return estado;
    }

    //SETTERS
    public void setDestino(String destino) {
        if (destino == null) {
            System.out.println("El destino no puede estar vacio.");
        } else {
            this.destino = destino;
        }
    }
    public void setPesoKg(double pesoKg) {
        if (pesoKg <= 0) {
            System.out.println("El peso debe ser mayor a 0.");
        } else {
            this.pesoKg = pesoKg;
        }
    }

    public void setDistanciaKm(double distanciaKm) {
        if (distanciaKm <= 0) {
            System.out.println ("La distancia debe ser mayor a 0.");
        } else {
            this.distanciaKm = distanciaKm;
        
        }
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            System.out.println("La fecha y hora no puede estar vacia.");
        } else {
            this.fechaHora = fechaHora;
        }
    }

    public void setEstado(String estado) {
        if (estado == null) {
            System.out.println("El estado no puede estar vacio.");
        } else {
            this.estado = estado;
        }
    }

    //metodo abstracto
    public abstract double calcularCosto (Vehiculo vehiculo);

    @Override
    public String obtenerEstadoActual() {
        return this.estado;
    }

    @Override
    public double calcularImporte() {
        return 0;
        //esto cuando hagamos el costo de envio
    }

    public boolean cambiarEstado(String nuevoEstado) {
        if (nuevoEstado.equals("PENDIENTE") || nuevoEstado.equals("CANCELADO")
                || (this.estado.equals("PENDIENTE") && nuevoEstado.equals("EN_CAMINO"))) {
            this.estado = nuevoEstado;
            return true;
        } else if (this.estado.equals("EN_CAMINO") && nuevoEstado.equals("ENTREGADO")) {
            this.estado = nuevoEstado;
            return true;
        } else {
            throw new EstadoInvalidoException("Transicion invalida de" + this.estado + "a" + nuevoEstado); 
        }
    }

    
}
