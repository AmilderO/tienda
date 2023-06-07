package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDAO;
import com.latam.alura.tienda.dao.ClienteDAO;
import com.latam.alura.tienda.dao.PedidoDAO;
import com.latam.alura.tienda.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.*;
import com.latam.alura.tienda.utils.JPAUtils;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDePedido {
    public static void main(String[] args) {
        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Producto producto = productoDAO.consultaPorId(1l);

        Cliente cliente = new Cliente("Amilder", "10235");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemsPedido(5, producto, pedido));

        em.getTransaction().begin();

        clienteDAO.guardar(cliente);
        pedidoDAO.guardar(pedido);

        em.getTransaction().commit();

        BigDecimal valorTotal = pedidoDAO.valorTotalVendido();
        System.out.println("Valor Total" + valorTotal);

        List<RelatorioDeVenta> relatorio = pedidoDAO.relatorioDeVentasVO();

        relatorio.forEach(System.out::println);


    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto(
                "Xiaomi Redmi",
                "Celular usado",
                new BigDecimal("800"),
                celulares
        );

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.guardar(celulares);
        productoDAO.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
