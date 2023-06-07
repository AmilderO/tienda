package com.latam.alura.tienda.modelo;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoriaId implements Serializable {

    private String nombre;
    private String password;

    public CategoriaId(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    public CategoriaId() {}

    public String getNombre() {
        return nombre;
    }
    public String getPassword() {
        return password;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaId that = (CategoriaId) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, password);
    }
}
