package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDAO;
import com.latam.alura.tienda.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegistroDeProducto {
    public static void main(String[] args) {

        Categoria celulares = new Categoria("CELULARES");

        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();

        em.persist(celulares);

        celulares.setNombre("LIBROS");

        em.flush();
        em.clear();


        celulares = em.merge(celulares);
        celulares.setNombre("SOFTWARES");

        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        em.remove(celulares);
        em.flush();

    }
}
