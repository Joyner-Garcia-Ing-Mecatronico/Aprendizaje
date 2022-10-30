package vista;

import static controlador.Controlador.listaVentas;
import modelos.Venta;

public class Vista {
    
    public static void menuPrincipal(){
        System.out.println("Tienda nuestros animalito\n");
        System.out.println("Seleccione la tarea a realizar: (1 al 6)");
        System.out.println("1. Ingresar nueva venta");
        System.out.println("2. Buscar una venta registrada");
        System.out.println("3. Modificar una venta registrada");
        System.out.println("4. Eliminar una venta registrada");
        System.out.println("5. Ver listado de venta registrada");
        System.out.println("4. Salir");
        
    }
    
    public static void verVenta(){

        for(int i=0;i<listaVentas.size();i++){
            System.out.println("\n INFORMACION DE LA VENTA: ");
            System.out.println("\n Fecha de la venta: " +listaVentas.get(i).getFecha());
            System.out.println("\n Numero de la venta: " +listaVentas.get(i).getNumVenta());
            System.out.println("\n Nombre del cliente: " +listaVentas.get(i).getCliente());
            System.out.println("\n Producto vendido: " +listaVentas.get(i).getProducto());
            System.out.println("\n Precio de la venta: " +listaVentas.get(i).getPrecio());
            System.out.println("\n Cantidad comprada: " +listaVentas.get(i).getCantidad());
            System.out.println("\n Nombre del vendedor: " +listaVentas.get(i).getVendendor());
            System.out.println("Precio total de la venta: " +listaVentas.get(i).getPrecio()*listaVentas.get(i).getCantidad());
            
        }
    }
    
    public static void verBuscado(Venta venta){
        System.out.println(venta.toString());
    }
    
}
