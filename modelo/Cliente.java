package modelo;
public class Cliente {
    private String nombre;
    private int codigo;
    private int tell;
    private String zona;

//constructor 
    public Cliente() {
    this.nombre = nombre; 
    this.codigo = codigo; 
    this.tell = tell;
    this.zona = zona;
    }
    

    public Cliente(String nombre, int codigo, int tell, String zona){
        this.nombre = nombre; 
        this.codigo = codigo;
        this.tell = tell;
        this.zona =zona;
    }

//GETTERS
public String getNombre() {
    return nombre;
}

public int getCodigo() {
    return codigo;
}

public int getTell() {
    return tell;
}
public String getZona() {
    return zona;
}

   //SETTERS
public void setNombre (String nombre) {
    if (nombre == null ) {
        System.out.println ("El nombre no puede estar vacio");
    } else {
    this.nombre = nombre;
 }
 }

public void setCodigo(int codigo) {
    if (codigo > 0) {
        this.codigo = codigo;
}    else {
    
       System.out.println ("El codigo debe ser un numero entero positivo");
}
}

public void setTell(int tell) {
    if (tell <= 0) {
        System.out.println("El numero de telefono debe ser positivo");
    } else {
        this.tell = tell;
    }
}

public void setZona(String zona) {
    if (zona == null) {
        System.out.println ("La zona no puede estar vacia");
    } else {
        this.zona = zona;
    }    
}
@Override
public String toString() {
    return ("Cliente [" + "Codigo: " + codigo + ", Nombre: " + nombre + ", Telefono: " + tell + ", Zona: " + zona + "]");
}
}



