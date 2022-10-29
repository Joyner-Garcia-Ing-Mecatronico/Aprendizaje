package com.mycompany.chucherias;

public class Chucherias {//SUPERCLASE
    
    public static class empleado{//Creo una clase, que pertenece a la superclase chucheria
        //Atributos del empleado
        private String nombre;
        private String identificacion;
        private String correo;
        private String telefono;       

        //Metodo constructor de objetos.
        public empleado(String nombre, String identificacion, String correo, String telefono) {
            this.nombre = nombre;
            this.identificacion = identificacion;
            this.correo = correo;
            this.telefono = telefono;
        }
        
        //GETTERS
        public String getNombre() {
            return nombre;
        }

        public String getIdentificacion() {
            return identificacion;
        }

        public String getCorreo() {
            return correo;
        }

        public String getTelefono() {
            return telefono;
        }
        
        //SETTERS

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
        
        public String comidaFavorita(empleado empl){
            String mensaje=empl.getNombre() + " Tiene por comida favorita el churrasco.";
            return mensaje;
        }

        @Override
        public String toString() {
            return "empleado{" + "Se llama= " + nombre + ", su identificacion es= " + identificacion + ", su correo es= " + correo + ", su telefono es=" + telefono + '}';
        }
        
    }
    
    public static void main(String[] args) {
        /*empleado joyner = new empleado("joyner","1127056438","joy@gmail,com","3053355675");
        
        System.out.println(joyner.getCorreo());
        System.out.println(joyner.getTelefono());
        
        joyner.setTelefono("30254545");
        System.out.println(joyner.getTelefono());*/
        empleado comida = new empleado("Daniel","1127056438","joy@gmail,com","3053355675");
        //System.out.println(comida.comidaFavorita(comida));//Usando metodo comida favorita
        
        System.out.println(comida.toString());
    }
}
