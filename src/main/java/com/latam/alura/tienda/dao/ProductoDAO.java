package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductoDAO {

    private EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }
    public void guardar(Producto producto){
        this.em.persist(producto);
    }
    public void actualizar(Producto producto){this.em.merge(producto);}
    public Producto consultaPorId(long id){
        return em.find(Producto.class, id);
    }
    public List<Producto> consultarTodos(){
        String jpql = "SELECT P FROM Producto AS P";
        return em.createQuery(jpql, Producto.class).getResultList();
    }
    public List<Producto> consultarPorNombre(String nombre){
        String jpql = "SELECT P FROM Producto AS P WHERE P.nombre =: nombre";
        return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }
    public List<Producto> consultarPorNombreDeCategoria(String nombre){
        String jpql = "SELECT P FROM Producto AS P WHERE P.categoria.nombre =: nombre";
        return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }
    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre){

        return em.createNamedQuery("Producto.consultaDePrecio",BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public List<Producto> consultarPorParametros(String nombre, BigDecimal precio, LocalDate fechaDeRegistro){
        StringBuilder jpql = new StringBuilder("SELECT P FROM Producto AS P WHERE 1=1");
        if(nombre != null && !nombre.trim().isEmpty()){
            jpql.append(" AND P.nombre =: nombre");
        }
        if(precio != null && !precio.equals(new BigDecimal(0))){
            jpql.append(" AND P.precio =: precio");
        }
        if (fechaDeRegistro != null){
            jpql.append(" AND P.fechaDeRegistro =: fechaDeRegistro");
        }
        TypedQuery<Producto> query = em.createQuery(jpql.toString(), Producto.class);
        if(nombre != null && !nombre.trim().isEmpty()){
            query.setParameter("nombre", nombre);
        }
        if(precio != null && !precio.equals(new BigDecimal(0))){
            query.setParameter("precio", precio);
        }
        if (fechaDeRegistro != null){
            query.setParameter("fechaDeRegistro", fechaDeRegistro);
        }
        return query.getResultList();
    }

    public List<Producto> consultarPorParametrosConAPICriteria(String nombre, BigDecimal precio, LocalDate fechaDeRegistro){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
        Root<Producto> from = query.from(Producto.class);

        Predicate filtro = builder.and();
        if(nombre != null && !nombre.trim().isEmpty()){
            filtro = builder.and(filtro, builder.equal(from.get("nombre"), nombre));
        }
        if(precio != null && !precio.equals(new BigDecimal(0))){
            filtro = builder.and(filtro, builder.equal(from.get("precio"), precio));
        }
        if (fechaDeRegistro != null){
            filtro = builder.and(filtro, builder.equal(from.get("fechaDeRegistro"), fechaDeRegistro));
        }
        query=query.where(filtro);
        return em.createQuery(query).getResultList();
    }



}
