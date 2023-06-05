package com.latam.alura.tienda.modelo;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {


    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dni;


    public Cliente(String nombre, String dni){
        this.dni = dni;
        this.nombre = nombre;
    }

    public Cliente() {}

    // Getters - Setters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

}
