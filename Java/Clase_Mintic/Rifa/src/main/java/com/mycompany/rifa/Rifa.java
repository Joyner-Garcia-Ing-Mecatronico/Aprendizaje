package com.mycompany.rifa;

import java.util.Scanner;


public class Rifa {
    
    //public static String Saludar(String nombre){//Declaramos un metodo con un parametro de entrada
    //   String Saludo = "Hola " +nombre+ " ¿como estas?"; //Ejecutamos alguna acción con el parametro de entrada
    //   return Saludo;//Retornamos un String con el resultado.
    //}

    public static void rifaJava(int b){
        Scanner input = new Scanner(System.in);//Se crea un objeto llamado input para leer lo que el usuario escriba por teclado
        int intentos=0; //Variable contadora (Colaboradora)
        int n = (int)((Math.random())*b)+1;//Generar numero aleatorio desde el metodo
        
        while(true){//Ciclo obligatorio e infinito
            System.out.println("Por favor, ingrese un numero: ");
            int numero = input.nextInt();//Guardamos el numero
            
            if (numero <= 0 || numero > b){//|| significa el or logico y verificamos que este entre los limites
            
                System.out.println("¡Te saliste del intervalo!");
            
            }
            else if (numero > n){//Verificamos mayor que
                    System.out.println("¡El numero esta por encima del valor!");
                    intentos++;//Intentos = intentos +1;
            }
            
            else if (numero < n){//Verificamos menor que
                    System.out.println("¡El numero esta por debajo del valor!");
                    intentos +=1;//Intentos = intentos +1;
            }
            else if (numero == n){//Verificamos acertamos el numero ganador
                    intentos = intentos + 1;//Intentos = intentos +1;
                    System.out.println("¡EUREKA, LO LOGRASTE!, solo te llevo " +intentos+ " Intentos lograrlo");
                    break;
            }
            
        }
    }
    
    //public static Scanner input = new Scanner(System.in); // Se crea un objeto para usarlo donde se quiera.
    //Prueba y ejecución de los métodos
    public static void main(String[] args) {
        //rifaJava(20,50);//Sin numero aleatorio
        
        //int numSecreto = (int)((Math.random())*50)+1;//Primera forma para el random, desde el main
        //rifaJava(numSecreto,50);
        
        System.out.println("Por favor, ingrese el limite superior: ");
        Scanner input1 = new Scanner(System.in);
        int num = input1.nextInt();
        
        rifaJava(num);//Con numero aleatorio desde el metodo
    }
}
