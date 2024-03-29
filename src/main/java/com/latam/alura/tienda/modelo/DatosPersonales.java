package com.latam.alura.tienda.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class DatosPersonales {
    private String nombre;
    private String dni;


    public DatosPersonales(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    public DatosPersonales() {}


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
}
