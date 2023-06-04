package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class RegistroDeProducto {
    public static void main(String[] args) {

        Producto celular = new Producto();
        celular.setNombre("Xiaomi Redmi");
        celular.setDescripcion("Producto usado");
        celular.setPrecio(new BigDecimal("800"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda");

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }
}
