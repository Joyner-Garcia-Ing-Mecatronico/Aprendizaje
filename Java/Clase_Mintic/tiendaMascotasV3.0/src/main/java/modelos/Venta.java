package modelos;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Entity;

@Entity
@Table(name="ventas")
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
    @ManyToOne
    @JoinColumn(name="vendedor_cedula")
    Vendedor vendedor;

    public Venta(Date fecha, int numVenta, String cliente, String producto, Double precio, int cantidad, Vendedor vendedor) {
        this.fecha = fecha;
        this.numVenta = numVenta;
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
    }

    public int getId() {
        return id;
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha=" + fecha + ", numVenta=" + numVenta + ", cliente=" + cliente + ", producto=" + producto + ", precio=" + precio + ", cantidad=" + cantidad + ", vendedor=" + vendedor.getNombre() + '}';
    }
    
    
    
}
