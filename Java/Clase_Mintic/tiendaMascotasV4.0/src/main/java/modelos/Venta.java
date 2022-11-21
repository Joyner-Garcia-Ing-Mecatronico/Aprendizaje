package modelos;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Entity;

@Entity
public class Venta implements Serializable{
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    int id;
    Date fecha;
    int numVenta;
    String cliente;
    String producto;
    Double precio;
    int cantidad;
    String vendendor;

    public Venta(Date fecha, int numVenta, String cliente, String producto, Double precio, int cantidad, String vendendor) {
        this.fecha = fecha;
        this.numVenta = numVenta;
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendendor = vendendor;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getVendendor() {
        return vendendor;
    }

    public int getId() {
        return id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setVendendor(String vendendor) {
        this.vendendor = vendendor;
    }

    @Override
    public String toString() {
        return "Venta{" + "fecha=" + fecha + ", numVenta=" + numVenta + ", cliente=" + cliente + ", producto=" + producto + ", precio=" + precio + ", cantidad=" + cantidad + ", vendendor=" + vendendor + '}';
    }
    
}
