package com.mycompany.holamundo;

import java.util.Scanner; //Se importa o se llama la clase para poder usar sus metodos

public class Holamundo {

    public static void main(String[] args) { //Objeto o Metodo de otra clase hola mundo
        Scanner input = new Scanner(System.in);//Creamos un Obtejo de tipo Scanner (Scanner input), que se le alimenta de lo que el usuario ingresa al sistema
        String nombre;//Declaramos una variable de tipo String llamada nombre
        System.out.println("Cual es tu nombre?: ");//Mostramos un mensaje en pantalla quepide el nombre al usuario
        nombre=input.nextLine();//Usamos el método nextLine de la clase Scanner a través de su objeto input y guardamos la información en la variable nombre.
        System.out.println("\nHola!" +nombre+ " ¿Como estas?");//Imprimimos un saludo concatenando la variable nombre
    }
}
