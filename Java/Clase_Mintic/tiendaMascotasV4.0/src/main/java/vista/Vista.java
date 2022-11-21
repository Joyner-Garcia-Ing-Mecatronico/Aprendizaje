package vista;

import controlador.ControladorVentas;
import controlador.ControladorVendedor;
import modelos.Vendedor;
import modelos.Venta;

public class Vista {
    
    public static void menuPrincipal(){
        System.out.println("Tienda nuestros animalito\n");
        System.out.println("Seleccione la tarea a realizar: (1 al 10)");
        System.out.println("1. Ingresar nueva venta");
        System.out.println("2. Buscar una venta registrada");
        System.out.println("3. Modificar una venta registrada");
        System.out.println("4. Eliminar una venta registrada");
        System.out.println("5. Ver listado de venta registrada");
        System.out.println("6. Ver ventas por vendedor");
        System.out.println("7. Ver area de los vendedores");
        System.out.println("8. Exportar información de Ventas en archivo plano");
        System.out.println("9. Exportar información de Ventas en archivo CSV(EXCEL) como formato plano");
        System.out.println("10. Salir");
        
    }
    
    public static void verVenta(){

        ControladorVentas.Directorio();
    }
    
    public static void verBuscado(Venta venta){
        System.out.println(venta.toString());
    }
    
    public static void menuAreaVendedor(){
        System.out.println("Tienda nuestros animalito\n");
        System.out.println("Seleccione la tarea a realizar: (1 al 6)");
        System.out.println("1. Ingresar nueva vendedor");
        System.out.println("2. Buscar un vendedor registrado");
        System.out.println("3. Modificar un vendedor registrado");
        System.out.println("4. Eliminar el registro de un vendedor");
        System.out.println("5. Ver listado de vendedores registrados");
        System.out.println("6. Salir");
        
    }
    

    public static void verVendedorBuscado(Vendedor vendedor){
        System.out.println(vendedor.toString());
    }
    
    public static void verVendedores(){

        ControladorVendedor.Directorio();
    }
    
}
