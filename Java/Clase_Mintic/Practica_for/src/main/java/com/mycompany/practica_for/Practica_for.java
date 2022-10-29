package com.mycompany.practica_for;

/**
 *
 * @author joyda
 */

public class Practica_for {
    
    public static int[] familia(int[] edades){
        
        //La edad del mas Joven.
        int joven = edades[0];// Guardo la edad de la primera posicion como si fuera la menor
        for(int i=0;i<edades.length;i++){//FOR [Desde donde, hasta donde, intervalo]
            if (edades[i]<joven){//Verificamos que, el que esta en esa posicion sea menor al que ya tengo registrado como menor
                joven=edades[i];
            }
        }
        //La edad del mas viejo.
        int mayor = edades[0];// Guardo la edad de la primera posicion como si fuera el mayor
        for(int i=0;i<edades.length;i++){//FOR [Desde donde, hasta donde, intervalo]
            if (edades[i]>mayor){//Verificamos que, el que esta en esa posicion sea mayor al que ya tengo registrado como mayor
                mayor=edades[i];
            }
        }     
        int suma = 0;
        //La suma de las edades
        for(int i=0;i<edades.length;i++){//FOR [Desde donde, hasta donde, intervalo]
            suma = suma + edades[0];//Obtenemos la suma de las edades
        }
        int[] respuesta = new int[3];//Crear un array con el tamaño conocido
        
        respuesta[0] = suma;
        respuesta[1] = joven;
        respuesta[2] = mayor;
        
        return respuesta;
    }

    public static void main(String[] args) {
        int[] misHijos ={23,11,5,18,24,25,26};//Crear un array para un arreglo de x datos
        
        //Imprimir posicion por posicion
        //System.out.println(familia(misHijos)[0]);
        //System.out.println(familia(misHijos)[1]);
        //System.out.println(familia(misHijos)[2]);
        
        int [] respuesta = familia(misHijos);//Llamamos al metodo alimentandolo de "misHijos" y guardamos el retorno en un Array
        
        for(int i=0;i<respuesta.length;i++){//Recorremos el Arreglo
            
            System.out.println((respuesta)[i]);//Se imprime lo que hay en cada posicion
        }
        
    }
}
