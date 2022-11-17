package controlador;

import controlador.ControladorVentas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelos.Vendedor;
import modelos.Venta;
import vista.Vista;

public class ControladorVendedor {
       
    public static ArrayList<Venta> listaVentas;// = new ArrayList<>();
    
    public static Scanner input = new Scanner(System.in);
    
    public static final String URL = "jdbc:mysql://localhost:3306/tiendamascotas";
    
    public static final String USER = "root";
    
    public static final String CLAVE = "";
    
    public ControladorVendedor() {
        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
                Statement stmt = coon.createStatement();){
                    
                String sql = "CREATE TABLE IF NOT EXISTS vendedor(id INT NOT NULL AUTO_INCREMENT,cedula VARCHAR(12) NOT NULL UNIQUE, nombre VARCHAR(120) NOT NULL,"
                        + "correo VARCHAR(30) NOT NULL, telefono VARCHAR(15), PRIMARY KEY(id))";
                stmt.executeUpdate(sql);
                System.out.println("Conectado a la base de datos, pudimos crear o conectar la base");
            
        }catch(SQLException e){
                System.out.println("No fue posible conectarnos a la base de datos");
                e.printStackTrace();
                }
    }
    
    public void Vendedores() {

        OUTER:
        while (true) {
            Vista.menuAreaVendedor();
            int opcion = capturaOpcion();
            switch (opcion) {
                case 1:
                    System.out.println("Ingresar un vendedor.");
                    ingresarVendedor();
                    break;
                case 2:
                    System.out.println("\n Buscar un VENDEDOR.\n");
                    System.out.println("\nPor favor, ingrese la cedula del vendedor: ");
                    String numCedula = input.nextLine();
                    Vendedor buscado = buscarVendedor(numCedula);
                    if(buscado==null){
                        System.out.println("\nEste vendedor no esta registrado.");
                    }
                    else{
                        Vista.verVendedorBuscado(buscado);
                    }
                    break;
                case 3:
                    System.out.println("\n Modificar un vendedor.\n");
                    modificarVendedor();
                    break;
                case 4:
                    System.out.println("\n Eliminar un vendedor.\n");
                    eliminarVendedor();
                    break;
                case 5:
                    Vista.verVendedores();
                    break;
                default:
                    System.out.println("Muchas gracias por usar nuestros servicio.");
                    break OUTER;
            }
        }
    }

    public static int capturaOpcion() {
        int opcion = 0;
        

        while (opcion < 1 || opcion > 6) {//Descartamos que el usuario nos de valores incorrectos
            System.out.println("Por favor, ingrese una opción: \n ");

            try {//Descarta que el usuario se equivoque en tipo de variable
                opcion = input.nextInt();
                input.nextLine();//Evita el error de Scanner despues de recibir numeros.
            } catch (InputMismatchException exception) {
                System.out.println("Opcion invalida. \n");
                input.nextLine();//Evita el error de Scanner despues de recibir numeros.
            }
        }
        return opcion;
    }
    
    public static void ingresarVendedor(){      
        int resultado = 0;
        
        while(resultado == 0){
            
            System.out.println("Por favor, ingrese la cedula del vendedor a registrar: ");
            String cedula = input.nextLine();

            resultado = validarCedula(cedula);
            
            if(resultado == 1){

                System.out.println("Por favor, ingrese el nombre del vendedor a registrar: ");
                String nombre = input.nextLine();

                System.out.println("Por favor, ingrese el correo del vendedor a registrar: ");
                String correo = input.nextLine();

                System.out.println("Por favor, ingrese el telefono del vendedor a registrar: ");
                String telefono = input.nextLine();

                Vendedor nuevoVendedor = new Vendedor(cedula, nombre, correo, telefono);

                try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
                Statement stmt = coon.createStatement();){

                    String sql = "INSERT INTO vendedor (id, cedula, nombre, correo, telefono) "
                            + "VALUES("+nuevoVendedor.getId()+",'"+nuevoVendedor.getCedula()+"','"+nuevoVendedor.getNombre()+
                            "','"+nuevoVendedor.getCorreo()+"','"+nuevoVendedor.getTelefono()+"');";
                    stmt.executeUpdate(sql);
                    System.out.println("Vendedor registrado correctamente \n");

                }catch(SQLException e){
                    System.out.println("No se pudo registrar el vendedor\n");
                    e.printStackTrace();
                    }
            }
        }
        
    }
    
    public static int validarCedula(String cedulaValidar){
        int validaCedula = 0;
        
        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
        Statement stmt = coon.createStatement();){

            String sql = "SELECT * FROM vendedor WHERE cedula='"+cedulaValidar+"';";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()){
                String nombre = rs.getString("nombre");

                System.out.println("Error, el numero de cedula pertenece al vendedor "+nombre+"\n");
            }else{
                validaCedula = 1;
            }

        }catch(SQLException e){
            System.out.println("No se pudo registrar el vendedor\n");
            e.printStackTrace();
        }
            
        return validaCedula;

        }
        
    
    public static Vendedor buscarVendedor(String cedula){
        Vendedor resultado = null;//Por defecto, se garantiza el retorno
        
        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
        Statement stmt = coon.createStatement();){

            String sql = "SELECT * FROM vendedor WHERE cedula='"+cedula+"';";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()==true){
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                
                resultado = new Vendedor(cedula, nombre, correo, telefono);
            }
        }catch(SQLException e){
            System.out.println("No se pudo conectar y buscar en la base de datos\n");
            e.printStackTrace();
            }
        
        return resultado;
    }
    
    public void modificarVendedor(){
        System.out.println("\nPor favor, ingrese la cedula del vendedor que se desea modificar: ");
        String numCedula = input.nextLine();
        Vendedor aModificar = buscarVendedor(numCedula);
        if(aModificar!=null){
            System.out.println("Por favor, ingrese el nombre del vendedor a registrar: ");
            String nombre = input.nextLine();

            System.out.println("Por favor, ingrese el correo del vendedor a registrar: ");
            String correo = input.nextLine();

            System.out.println("Por favor, ingrese el telefono del vendedor a registrar: ");
            String telefono = input.nextLine();
            
            try (Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                    Statement stmt = coon.createStatement();){
                        ///Consulta SQL para actualizar un objeto o registro
                    String sql = "UPDATE vendedor SET nombre='"+nombre+"'"
                            + ", correo='"+correo+"', telefono='"+telefono+"' WHERE cedula="+numCedula+";";
                    stmt.executeUpdate(sql);
                    System.out.println("Vendedor actualizado correctamente");
                    stmt.close();
                    coon.close();
            }catch(SQLException e){
                    System.out.println("El vendedor no se encuentra en la base de datos");
                    }
        }
        else{
            System.out.println("\nEste vendedor no esta registrado.");
        }
    }
    
    public void eliminarVendedor(){
        System.out.println("\nPor favor, ingrese el numero de cedula del vendedor que desea eliminar: ");
        String numCedula = input.nextLine();
        Vendedor aEliminar = buscarVendedor(numCedula);
        if(aEliminar!=null){
            //ELIMINAR EN LA BASE DE DATOS
            try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                    Statement stmt = coon.createStatement();){
                
                String sql = "DELETE FROM vendedor WHERE cedula='"+numCedula+"';";
                
                stmt.executeUpdate(sql);
                
                System.out.println("El vendedor fue eliminado");
                
                stmt.close();
                coon.close();
            }catch(SQLException e){
                System.out.println("Error, no se pudo eliminar la base de datos");
            }
        }else{
            System.out.println("El vendedor no esta registrado");
        }
    }
    
    public static String Directorio(){
        String resultado = "";
        
        try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                Statement stmt = coon.createStatement();){
            String sql = "SELECT * FROM vendedor";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                
                resultado ="\nINFORMACIÓN DE LOS VENDEDORES\nNumero de cedula: "+cedula+"\nNombre del vendedor: "+nombre+"\n"
                        + "Correo: "+correo+"\nTelefono: "+telefono+"\n";
                
                System.out.println(resultado);
            }
        }catch(SQLException e){
            System.out.println("No es posible acceder a la base de datos");
            e.printStackTrace();
        }
        
        
        return resultado;
    }
}
