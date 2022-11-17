package controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import modelos.Venta;
import controlador.ControladorVendedor;
import static vista.Vista.menuPrincipal;
import static vista.Vista.verBuscado;
import static vista.Vista.verVenta;

public class ControladorVentas {

    public static ArrayList<Venta> listaVentas;// = new ArrayList<>();
    
    public static Scanner input = new Scanner(System.in);
    
    public static final String URL = "jdbc:mysql://localhost:3306/tiendamascotas";
    
    public static final String USER = "root";
    
    public static final String CLAVE = "";
    
    public ControladorVentas() {
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
                    System.out.println("\nPor favor, ingrese el id de la venta: ");
                    int numeroID = input.nextInt();
                    input.nextLine();
                    Venta buscado = buscar(numeroID);
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
                    break;
                case 4:
                    System.out.println("\n Eliminar una venta.\n");
                    eliminar();
                    break;
                case 5:
                    verVenta();
                    break;
                case 6:
                    getByVendedor();
                    break;
                case 7:
                    ControladorVendedor controlVendedor = new ControladorVendedor();
                    controlVendedor.Vendedores();
                    break;
                case 8:
                    exportarPlano();
                    break;
                case 9:
                    exportarCSV();
                    break;
                default:
                    //guardarArchivoVentas();
                    System.out.println("Muchas gracias por usar nuestros servicio.");
                    break OUTER;
            }
        }
    }

    public static int capturaOpcion() {
        int opcion = 0;
        
        while (opcion < 1 || opcion > 10) {//Descartamos que el usuario nos de valores incorrectos
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
    
    public static Venta buscar(int id){
        Venta resultado = null;//Por defecto, se garantiza el retorno
        
        try (Connection coon = DriverManager.getConnection(URL,USER,CLAVE);
        Statement stmt = coon.createStatement();){

            String sql = "SELECT * FROM ventas WHERE id="+id+";";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()==true){
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendendor = rs.getString("vendendor");
                
                resultado = new Venta(fecha, numVenta, cliente, producto, precio, cantidad, vendendor);
            }
        }catch(SQLException e){
            System.out.println("No se pudo conectar y buscar en la base de datos\n");
            e.printStackTrace();
            }
        
        return resultado;
    }
    
    public void modificar(){
        System.out.println("\nPor favor, ingrese el ID de la venta que desea modificar: ");
        int numeroID = input.nextInt();
        input.nextLine();
        Venta aModificar = buscar(numeroID);
        if(aModificar!=null){
            System.out.println("\nIngrese el numero de la venta: ");
            int numVenta = input.nextInt();
            input.nextLine();
            
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
            
            try (Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                    Statement stmt = coon.createStatement();){
                        ///Consulta SQL para actualizar un objeto o registro
                    String sql = "UPDATE ventas SET numVenta="+numVenta+", cliente='"+cliente+"'"
                            + ", producto='"+producto+"', precio="+precio+", cantidad="+cantidad+","
                            + " vendendor='"+vendedor+"' WHERE id="+numeroID+";";
                    stmt.executeUpdate(sql);
                    System.out.println("Venta actualizada correctamente");
                    stmt.close();
                    coon.close();
            }catch(SQLException e){
                    System.out.println("La venta no se encuentra en la base de datos");
                    }
        }
        else{
            System.out.println("\nEsta venta no esta registrada.");
        }
    }
    
    public void eliminar(){
        System.out.println("\nPor favor, ingrese el ID de la venta que desea eliminar: ");
        int numeroID = input.nextInt();
        input.nextLine();
        Venta aEliminar = buscar(numeroID);
        if(aEliminar!=null){
            //ELIMINAR EN LA BASE DE DATOS
            try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                    Statement stmt = coon.createStatement();){
                
                String sql = "DELETE FROM ventas WHERE id="+numeroID+";";
                
                stmt.executeUpdate(sql);
                
                System.out.println("La venta fue eliminada");
                
                stmt.close();
                coon.close();
            }catch(SQLException e){
                System.out.println("Error, no se pudo eliminar la base de datos");
            }
        }else{
            System.out.println("La venta no esta registrada");
        }
    }
    
    public static String Directorio(){
        String resultado = "";
        
        try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                Statement stmt = coon.createStatement();){
            String sql = "SELECT * FROM ventas";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendendor = rs.getString("vendendor");
                
                resultado ="\nINFORMACIÓN DE LA VENTA\nFecha: "+fecha+"\nNumero de Venta: "+numVenta+"\n"
                        + "Cliente: "+cliente+"\nProducto vendido: "+producto+"\nPrecio del Producto: "+precio+"\n"
                        + "Cantidad Comprada: "+cantidad+"\nVendedor: "+vendendor+"\n"
                        + "Total de la Venta: $"+cantidad*precio+"\n";
                
                System.out.println(resultado);
            }
        }catch(SQLException e){
            System.out.println("No es posible acceder a la base de datos");
            e.printStackTrace();
        }
        
        
        return resultado;
    }
    
    public static String getByVendedor(){
        String resultado = "";
        System.out.println("Por favor, digite el nombre del vendedor: ");
        String nomVendedor = input.nextLine();
        
        try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                Statement stmt = coon.createStatement();){
            String sql = "SELECT * FROM ventas WHERE vendendor='"+nomVendedor+"';";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                
                resultado ="\nINFORMACIÓN DE LA VENTA\nFecha: "+fecha+"\nNumero de Venta: "+numVenta+"\n"
                        + "Cliente: "+cliente+"\nProducto vendido: "+producto+"\nPrecio del Producto: "+precio+"\n"
                        + "Cantidad Comprada: "+cantidad+"\nVendedor: "+nomVendedor+"\n"
                        + "Total de la Venta: $"+cantidad*precio+"\n";
                
                System.out.println(resultado);
            }
        }catch(SQLException e){
            System.out.println("No es posible acceder a la base de datos");
            e.printStackTrace();
        }
        
        
        return resultado;
    }
    
    public void exportarPlano(){
        String pathArchivo = ("informacion.txt");
        
        try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                Statement stmt = coon.createStatement();){
            
            String sql = "SELECT * FROM ventas";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            BufferedWriter escritoArchivo = new BufferedWriter(new FileWriter(pathArchivo));

            //Escribo la lineas que contiene los header y delimitadores de columna
            
            escritoArchivo.write("ID, Fecha, No. Venta, Cliente, Producto, Precio, Cantidad, Vendendor, Venta Total");//Titulo
            
            while(rs.next()){
                int id = rs.getInt("id");
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendendor = rs.getString("vendendor");
                Double ventaTotal = precio*cantidad;
                
                //Escribamos en el documento
                String linea = id+","+fecha+","+numVenta+","+cliente+","+producto+","+precio+","+cantidad+","+vendendor+","+ventaTotal;
                escritoArchivo.newLine();
                escritoArchivo.write(linea);
            }
            System.out.println("Archivo externo creado y/o actualizado");
            escritoArchivo.close();
            stmt.close();
            rs.close();
            coon.close();
        }catch(SQLException e){
            System.out.println("No pudimos contactar con la base de datos");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Tuvimos problemas con el archivo externo (Creacion o consulta)");
            e.printStackTrace();
        }
    }
    
    public void exportarCSV(){//Los archivos CSV (EXCEL) son los mas usados por lo que son mas livianas de peso
        String pathArchivo = ("informacion.csv");//Este sigue siendo un archivo plano, pero, usando la conversion de fila a columna por , se soluciona.
        
        try(Connection coon = DriverManager.getConnection(URL, USER, CLAVE);
                Statement stmt = coon.createStatement();){
            
            String sql = "SELECT * FROM ventas";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            BufferedWriter escritoArchivo = new BufferedWriter(new FileWriter(pathArchivo));

            //Escribo la lineas que contiene los header y delimitadores de columna
            
            escritoArchivo.write("ID, Fecha, No. Venta, Cliente, Producto, Precio, Cantidad, Vendendor, Venta Total");//Titulo
            
            while(rs.next()){
                int id = rs.getInt("id");
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendendor = rs.getString("vendendor");
                Double ventaTotal = precio*cantidad;
                
                //Escribamos en el documento
                String linea = id+","+fecha+","+numVenta+","+cliente+","+producto+","+precio+","+cantidad+","+vendendor+","+ventaTotal;
                escritoArchivo.newLine();
                escritoArchivo.write(linea);
            }
            System.out.println("Archivo externo creado y/o actualizado");
            escritoArchivo.close();
            stmt.close();
            rs.close();
            coon.close();
        }catch(SQLException e){
            System.out.println("No pudimos contactar con la base de datos");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Tuvimos problemas con el archivo externo (Creacion o consulta)");
            e.printStackTrace();
        }
    }

}
