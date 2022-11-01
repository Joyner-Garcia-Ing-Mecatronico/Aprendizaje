package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Venta;
import static vista.Vista.menuPrincipal;
import static vista.Vista.verBuscado;
import static vista.Vista.verVenta;

public class Controlador {

    public static ArrayList<Venta> listaVentas;// = new ArrayList<>();
    
    public static Scanner input = new Scanner(System.in);
    
    public static final String URL = "jdbc:mysql://localhost:3306/tiendamascotas";
    
    public static final String USER = "root";
    
    public static final String CLAVE = "";

    public Controlador() {
        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
                Statement stmt = coon.createStatement();){
                    
                String sql = "CREATE TABLE IF NOT EXISTS ventas(id INT NOT NULL AUTO_INCREMENT,fecha DATE NOT NULL, numVenta INT(5) NOT NULL,"
                        + "cliente VARCHAR(120) NOT NULL, producto VARCHAR(100) NOT NULL, precio FLOAT NOT NULL,"
                        + " cantidad INT(10) NOT NULL, vendendor VARCHAR(120) NOT NULL, PRIMARY KEY(id))";
                stmt.executeUpdate(sql);
                System.out.println("Conectado a la base de datos, pudimos crear o conectar la base");
            
        }catch(SQLException e){
                System.out.println("No fue posible conectarnos a la base de datos");
                e.printStackTrace();
                }
    }
    
    

    public void trabajar() {

        OUTER:
        while (true) {
            menuPrincipal();
            int opcion = capturaOpcion();
            switch (opcion) {
                case 1:
                    System.out.println("Ingresar una venta.");
                    ingresar();
                    break;
                case 2:
                    System.out.println("\n Buscar Una venta.\n");
                    System.out.println("\nPor favor, ingrese la venta a buscar: ");
                    int numVenta = input.nextInt();
                    input.nextLine();
                    Venta buscado = buscar(numVenta);
                    if(buscado==null){
                        System.out.println("\nEsta venta no esta registrada.");
                    }
                    else{
                        verBuscado(buscado);
                    }
                    break;
                case 3:
                    System.out.println("\n Modificar Una venta.\n");
                    modificar();
                    System.out.println("\n Venta modificada.\n");
                    break;
                case 4:
                    System.out.println("\n Eliminar una venta.\n");
                    eliminar();
                    System.out.println("\n Venta eliminada.\n");
                    break;
                case 5:
                    verVenta();
                    break;
                default:
                    guardarArchivoVentas();
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
    
    public void ingresar(){
        long milisegundos=System.currentTimeMillis();
        Date fecha = new Date(milisegundos);
        
        System.out.println("Por favor, ingrese el numero de venta a registrar: ");
        int numVenta = input.nextInt();
        input.nextLine();
        
        System.out.println("Por favor, ingrese el nombre del cliente: ");
        String cliente = input.nextLine();
        
        System.out.println("Que producto esta comprando: ");
        String producto = input.nextLine();
        
        System.out.println("Cual es el precio de este producto: ");
        Double precio = input.nextDouble();
        
        System.out.println("Que cantidad lleva de este producto: ");
        int cantidad = input.nextInt();
        input.nextLine();
        
        System.out.println("Por favor, ingrese el nombre del vendedor: ");
        String vendedor = input.nextLine();
        
        Venta nuevaVenta = new Venta(fecha, numVenta, cliente, producto, precio, cantidad, vendedor);

        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
        Statement stmt = coon.createStatement();){

            String sql = "INSERT INTO ventas (id, fecha, numVenta, cliente, producto, precio, cantidad, vendendor) "
                    + "VALUES("+nuevaVenta.getId()+",'"+fecha+"',"+numVenta+",'"+cliente+"','"
                    +producto+"',"+precio+","+cantidad+",'"+vendedor+"');";
            stmt.executeUpdate(sql);
            System.out.println("Venta registrada correctamente \n");
            
        }catch(SQLException e){
            System.out.println("No se pudo registrar la venta \n");
            e.printStackTrace();
            }
        
    }
    
    public static Venta buscar(int numeroVenta){
        Venta resultado = null;//Por defecto, se garantiza el retorno
        
        for(int i=0; i<listaVentas.size();i++){
            if(listaVentas.get(i).getNumVenta() == numeroVenta){
                System.out.println("\n Venta Encontrada \n");
                resultado = listaVentas.get(i);
            }
        }
        return resultado;
    }
    
    public void modificar(){
        System.out.println("\nPor favor, ingrese la venta que desea modificar: ");
        int numVenta = input.nextInt();
        input.nextLine();
        Venta aModificar = buscar(numVenta);
        if(aModificar!=null){
            System.out.println("\nIngrese el nombre del cliente: ");
            String cliente = input.nextLine();
            
            System.out.println("\nIngrese el nombre del producto: ");
            String producto = input.nextLine();
            
            System.out.println("\nIngrese el precio del producto: ");
            Double precio = input.nextDouble();
            
            System.out.println("\nIngrese la cantidad que lleva: ");
            int cantidad = input.nextInt();
            input.nextLine();
            
            System.out.println("\nIngrese el nombre del responsable de la venta: ");
            String vendedor = input.nextLine();
            
            for(int i=0;i<listaVentas.size();i++){
                if(listaVentas.get(i).getNumVenta()==aModificar.getNumVenta()){
                    listaVentas.get(i).setCliente(cliente);
                    listaVentas.get(i).setPrecio(precio);
                    listaVentas.get(i).setProducto(producto);
                    listaVentas.get(i).setCantidad(cantidad);
                    listaVentas.get(i).setVendendor(vendedor);
                }
            }
        }
        else{
            System.out.println("\nEsta venta no esta registrada.");
        }
    }
    
    public void eliminar(){
        System.out.println("\nPor favor, ingrese el ID de la venta que desea eliminar: ");
        int numVenta = input.nextInt();
        input.nextLine();
        //ELIMINAR EN LA BASE DE DATOS
        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
        Statement stmt = coon.createStatement();){

            String sql = "DELETE FROM ventas WHERE id="+numVenta+";";
            stmt.executeUpdate(sql);
            System.out.println("Venta eliminada correctamente \n");
            
        }catch(SQLException e){
            System.out.println("No se pudo eliminar la venta, no existe\n");
            e.printStackTrace();
            }
        
    }
    
    public void guardarArchivoVentas(){
        try {
            FileOutputStream archivo = new FileOutputStream("ventas.dat");//Crear el archivo aunque no exista
            
            ObjectOutputStream lapiz = new ObjectOutputStream(archivo);
            
            lapiz.writeObject(listaVentas);
            lapiz.close();
            archivo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Ruta de Archivo no valida");//Si no se pudiera crear, entonces dara este mensaje, si por X causa pasara
        } catch (IOException e){
            System.out.println("No se puede escribir en el archivo");
            e.printStackTrace();
        }
    }
    
    public void RecuperarArchivoVentas(){
        try {
            FileInputStream archivo = new FileInputStream("ventas.dat");//Crear el archivo aunque no exista

            ObjectInputStream gafas = new ObjectInputStream(archivo);

            listaVentas = (ArrayList)gafas.readObject();

            gafas.close();
            archivo.close();
        
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo, no se puede cargar la información");//Si no se pudiera crear, entonces dara este mensaje, si por X causa pasara
        } catch (IOException e){
            System.out.println("No se puede leer en el archivo, no hay memoria previa");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("La información encontrada no corresponde al archivo de ventas");
            e.printStackTrace();
        }
    }
}
