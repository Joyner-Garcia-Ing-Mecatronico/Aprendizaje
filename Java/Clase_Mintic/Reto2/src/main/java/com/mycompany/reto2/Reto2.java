package com.mycompany.reto2;
import java.util.ArrayList;

/*Cantidad total de boletas vendidas
Cantidad total de dinero recaudado en una función
Cantidad total de boletas general vendidas
Cantidad total de dinero recaudado por boletas general
Cantidad total de boletas VIP vendidas
Cantidad total de dinero recaudado por boletas VIP
*/

public class Reto2 {
    
    public static ArrayList<Boleta> t1 = new ArrayList<>();
    
    public static void main(String[] args) {
        
        t1.add(new Boleta("000", "Cars 3", "VIP", 24000, "1063987245", "Mar Rivera"));
        t1.add(new Boleta("001", "Cars 3", "VIP", 24000, "1063741243", " Johan 2"));
        t1.add(new Boleta("002", "Cars 3", "general", 15000, "71588659", "Paula H"));
        t1.add(new Boleta("003", "Cars 3", "general", 15000, "98547632", "Mauro"));
        t1.add(new Boleta("004", "Cars 31", "general", 15000, "147896301", "David M"));
        
        int[] resultado = statisticsCinema(t1);
        
        System.out.println(resultado[0] + " , "+ resultado[1] +" , "+ resultado[2] + " , "+ resultado[3] +" , "+ resultado[4] +" , "+ resultado[5]);
    }
    
    public static int[] statisticsCinema(ArrayList <Boleta> funcion){
        //EN ESTE ESPACIO PONER SU LÓGICA
        int [] resultado = new int[6];
        
        resultado[0] = cantidadBoletas(funcion);
        
        resultado[1] = dineroTotal(funcion);
        
        resultado[2] = boletasGeneral(funcion);
        
        resultado[3] = dineroTotalGeneral(funcion);
        
        resultado[4] = boletasVIP(funcion);
        
        resultado[5] = dineroTotalVIP(funcion);
        
        return resultado;
    }
    
    public static int cantidadBoletas(ArrayList <Boleta> numBoletas){
        int cantiBoletas = numBoletas.size();
        
        return cantiBoletas;
    }
    
    public static int dineroTotal(ArrayList <Boleta> numBoletas){
        int dinero = 0;
        
        for(int i=0; i<numBoletas.size(); i++){
            dinero = dinero + numBoletas.get(i).getCostoBoleta();
        }
        
        return dinero;
    }
    
    public static int boletasGeneral(ArrayList <Boleta> numBoletas){
        int general = 0;
        
        for(int i=0; i<numBoletas.size(); i++){
            if("general".equals(numBoletas.get(i).getTipoBoleta())){
               general = general + 1; 
            }
        }
        
        return general;
    }

    public static int dineroTotalGeneral(ArrayList <Boleta> numBoletas){
        int dineroGeneral = 0;
        
        for(int i=0; i<numBoletas.size(); i++){
            if("general".equals(numBoletas.get(i).getTipoBoleta())){
                dineroGeneral = dineroGeneral + numBoletas.get(i).getCostoBoleta();
            }
            
        }
        
        return dineroGeneral;
    }
    
    public static int boletasVIP(ArrayList <Boleta> numBoletas){
        int VIP = 0;
        
        for(int i=0; i<numBoletas.size(); i++){
            if("VIP".equals(numBoletas.get(i).getTipoBoleta())){
               VIP = VIP + 1; 
            }
        }
        
        return VIP;
    }
    
    public static int dineroTotalVIP(ArrayList <Boleta> numBoletas){
        int dineroVIP = 0;
        
        for(int i=0; i<numBoletas.size(); i++){
            if("VIP".equals(numBoletas.get(i).getTipoBoleta())){
                dineroVIP = dineroVIP + numBoletas.get(i).getCostoBoleta();
            }
            
        }
        
        return dineroVIP;
    }
    
}
