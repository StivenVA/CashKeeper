package com.codingideas.cashkeeper.repository;


import com.codingideas.cashkeeper.models.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Order> getLastOrderForSupplier(String id_proveedor){
        return entityManager.createQuery("select o from (select max(fecha) as maxfecha from Order) f,Order o where f.maxfecha = o.fecha and id_proveedor.id_proveedor='"+id_proveedor+"'").getResultList();
    }

    public List<Long> getNumberOfProductsForSupplier(){
        return entityManager.createQuery("select count(p.id_producto) from(select max(fecha) as maxfecha from Order)f,Order p where f.maxfecha=fecha GROUP BY id_proveedor").getResultList();
    }

    public List<String> getSuppliersNameForLastOrders(){
        return entityManager.createQuery("select id_proveedor.id_proveedor from (select max(fecha) as m from Order) f,Order o where f.m=fecha group by id_proveedor.id_proveedor").getResultList();
    }

    public List<Order> getLastOrders(){
        return entityManager.createQuery("select o from (select max(fecha) as maxfecha from Order) f,Order o where f.maxfecha = o.fecha order by  id_proveedor.id_proveedor").getResultList();
    }
}
