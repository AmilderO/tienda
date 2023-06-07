package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }
    public void guardar(Cliente cliente){
        this.em.persist(cliente);
    }
    public void actualizar(Cliente cliente){
        this.em.merge(cliente);
    }
    public void remover(Cliente cliente){
        cliente = this.em.merge(cliente);
        this.em.remove(cliente);
    }
    public Cliente consultaPorId(long id){
        return em.find(Cliente.class, id);
    }
    public List<Cliente> consultarTodos(){
        String jpql = "SELECT P FROM Cliente AS P";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }
    public List<Cliente> consultarPorNombre(String nombre){
        String jpql = "SELECT P FROM Cliente AS P WHERE P.nombre =: nombre";
        return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }


}
