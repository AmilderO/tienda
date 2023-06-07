package com.latam.alura.tienda.modelo;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {


    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private DatosPersonales datosPersonales;

    public Cliente(String nombre, String dni){
        this.datosPersonales = new DatosPersonales(nombre, dni);
    }

    public Cliente() {}

    // Getters - Setters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return datosPersonales.getNombre();
    }
    public void setNombre(String nombre) {
        this.datosPersonales.setNombre(nombre);
    }
    public String getDni() {
        return datosPersonales.getDni();
    }
    public void setDni(String dni) {
        this.datosPersonales.setDni(dni);
    }

}
