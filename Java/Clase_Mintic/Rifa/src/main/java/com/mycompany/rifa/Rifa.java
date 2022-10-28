package com.mycompany.rifa;


public class Rifa {
    
    public static String Saludar(String nombre){//Declaramos un metodo con un parametro de entrada
       String Saludo = "Hola " +nombre+ " ¿como estas?"; //Ejecutamos alguna acción con el parametro de entrada
       return Saludo;//Retornamos un String con el resultado.
    }

    public static void main(String[] args) {
        //Es bueno conservarlo para desde aqui llamar al metodo que prueba el ejercicio.
        String name = "Joyner";
        String imprimir = Saludar(name);
        System.out.println(name);
        System.out.println(Saludar(name));
        System.out.println(imprimir);
    }
}
