

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
       
        Cliente c1 = new Cliente("Juan Perez", 1, 11223344, "Centro");
        Cliente c2 = new Cliente("Maria Gomez", 2, 55667788, "Norte");
        
        sistema.registrarCliente(c1);
        sistema.registrarCliente(c2);

        System.out.println(" Datos de prueba cargados correctamente en CityLink.");
    } catch (AlmacenamientoLlenoException e) {
        System.err.println("Error al cargar datos de prueba: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error inesperado en la carga inicial: " + e.getMessage());
    }
}
    }
