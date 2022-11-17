/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Entity;

@Entity
public class Vendedor {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    int id;
    String cedula;
    String nombre;
    String correo;
    String telefono;
    
    
    
    
    
    public Vendedor(String cedula, String nombre, String correo, String telefono){

    this.cedula = cedula;
    this.nombre = nombre;
    this.correo = correo;
    this.telefono = telefono;
    }

    public int getId() {
        return id;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public void setCedula(String cedula){
        this.cedula = cedula;
    } 
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    } 
        
    public void setCorreo(String correo){
        this.correo = correo;
    }
            
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "cedula=" + cedula + ", nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + '}';
    }
    
}

