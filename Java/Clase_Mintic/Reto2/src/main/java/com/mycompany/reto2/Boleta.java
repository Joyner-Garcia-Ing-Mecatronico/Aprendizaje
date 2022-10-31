package com.mycompany.reto2;

public class Boleta {
        //ATRIBUTOS
    String id;
    String nombrePelicula;
    String tipoBoleta;
    int costoBoleta;
    String documentoIdentidad;
    String nombreCliente;
    
    
    //CONSTRUCTOR
    public Boleta(String id, String nombrePelicula, String tipoBoleta, int costoBoleta, String documentoIdentidad, String nombreCliente) {
        this.id = id;
        this.nombrePelicula = nombrePelicula;
        this.tipoBoleta = tipoBoleta;
        this.costoBoleta = costoBoleta;
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCliente = nombreCliente;
    }
    
    //GETTERS Y SETTERS (COMPLETAR)
    public String getNombreCliente() {
       return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getTipoBoleta() {
        return tipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        this.tipoBoleta = tipoBoleta;
    }

    public int getCostoBoleta() {
        return costoBoleta;
    }

    public void setCostoBoleta(int costoBoleta) {
        this.costoBoleta = costoBoleta;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
}
