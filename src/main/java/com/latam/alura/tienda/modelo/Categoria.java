package com.latam.alura.tienda.modelo;

import javax.persistence.*;

@Entity
@Table(name="categorias")
public class Categoria {

    // Atributos
    @EmbeddedId
    private CategoriaId categoriaId;


    public Categoria(String nombre){
        this.categoriaId = new CategoriaId(nombre, "1234");
    }
    public Categoria() {}

    // Getters - Setters
    public String getNombre() {
        return categoriaId.getNombre();
    }

    public void setNombre(String nombre) {
        this.categoriaId.setNombre(nombre);
    }
}
