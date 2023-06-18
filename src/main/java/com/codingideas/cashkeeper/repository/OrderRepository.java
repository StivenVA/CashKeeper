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

    public List<Order> getLastOrders(){
        return entityManager.createQuery("select o from (select max(fecha) as maxfecha from Order) f,Order o where f.maxfecha = o.fecha").getResultList();
    }
}
