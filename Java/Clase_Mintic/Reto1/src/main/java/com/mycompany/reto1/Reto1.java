/*CONTEXTO
Usted ha sido contratado por una sociedad de inversionistas para realizar el análisis del comportamiento de las acciones Ethereum, la criptomoneda.
Su solución le permitirá conocer a la sociedad la siguiente información:
Valor promedio
Menor valor.
Mayor valor.

TAREAS
Realizar un método en Java (Llamado reporte) que le permita a la sociedad, conocer unas estadísticas básicas sobre su la criptomoneda, teniendo en cuenta las siguientes especificaciones:
El historial de valores de la criptomoneda se va a representar como un Array de números decimales (double), cada elemento del Array es el valor que con el que Ethereum cerró en un día; a partir de este Array, usted deberá calcular el promedio de los valores (La suma de todos los elementos del Array dividida por la cantidad de elementos), calcular cuál fue el valor más bajo y alto.

ENTRADAS
Su método recibirá como parámetro un Array de números decimales (double), donde cada elemento representa el valor que con el que Ethereum cerró en un día. NO CREE LOS DATOS DE ENTRADA, DEBE USAR LOS QUE SE RECIBEN COMO PARÁMETRO.

SALIDAS
Su método debe retornar un Array de números decimales (double) de 3 posiciones, donde:
En la primera posición se guardará el promedio del valor de las acciones.
En la segunda posición irá el menor valor.
En la tercera posición irá el mayor valor.
*/

package com.mycompany.reto1;

public class Reto1 {
    
    //public static double[] Valores = {4.1, 4.8, 1.5, 2.3, 0.1, 0.9, 1.4, 1.8, 1.5, 3.0};
    
    public static void main(String[] args) {
        double[] Valores = {4.1, 4.8, 1.5, 2.3, 0.1, 0.9, 1.4, 1.8, 1.5, 3.0};
        
        double[] imprimir = reporte(Valores);
        System.out.println(imprimir[0] + " , "+ imprimir[1] +" , "+ imprimir[2]);
    }

    public static double[] reporte(double[] valores) {
        //EN ESTE ESPACIO PONER SU LÓGICA
        double vMenor = menorValor(valores); 
        double vMayor = valorPromedio(valores);
        double vPromedio = mayorValor(valores);
        
        double[] respuesta = new double[3];
        
        respuesta[0] = vMenor;
        respuesta[1] = vMayor;
        respuesta[2] = vPromedio;
        
        //System.out.println(respuesta[0] + " , "+ respuesta[1] +" , "+ respuesta[2]);
        
        return respuesta;
        
    }
    
    public static Double valorPromedio(double[] datos){
        double valor = 0;
        
        for(int i=0;i<datos.length;i++){
            valor = valor + datos[i];
        }
        
        valor = valor/(datos.length);
        
        return valor;
        
    }
    public static Double menorValor(double[] datas){
        double valor = datas[0];
        
        for(int i=0;i<datas.length;i++){
            if(datas[i]<valor){
                valor = datas[i];
            }
        }
        
        return valor;
        
    }
    public static Double mayorValor(double[] dotes){
        
        double valor = dotes[0];
        
        for(int i=0;i<dotes.length;i++){
            if(dotes[i]>valor){
                valor = dotes[i];
            }
        }
        
        return valor;
        
    }

}
