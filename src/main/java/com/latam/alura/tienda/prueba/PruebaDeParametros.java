package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDAO;
import com.latam.alura.tienda.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PruebaDeParametros {

    public static void main(String[] args) {
        cargarBancoDeDatos();

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);

        List<Producto> resultado = productoDAO.consultarPorParametrosConAPICriteria("FIFA 2020", null, null);

        System.out.println(resultado.get(0).getDescripcion());
    }

    private static void cargarBancoDeDatos(){
        Categoria celulares = new Categoria("CELULARES");
        Categoria videojuegos = new Categoria("VIDEO_JUEGOS");
        Categoria electronicos = new Categoria("ELECTRONICOS");

        Producto celular = new Producto("Xiaomi Redmi Note 8", "Celular de gama media", new BigDecimal("800"), celulares);
        Producto videojuego = new Producto("FIFA 2020", "Videojuego de fútbol", new BigDecimal("200"), videojuegos);
        Producto memoria = new Producto("Memoria USB 64GB", "Memoria USB de 64GB", new BigDecimal("100"), electronicos);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();
        categoriaDAO.guardar(celulares);
        categoriaDAO.guardar(videojuegos);
        categoriaDAO.guardar(electronicos);

        productoDAO.guardar(celular);
        productoDAO.guardar(videojuego);
        productoDAO.guardar(memoria);

        em.getTransaction().commit();
        em.close();
    }

}
