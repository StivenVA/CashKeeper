package com.codingideas.cashkeeper.repository;

import com.codingideas.cashkeeper.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SaleRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Product> getProducts(int id_factura){
        return entityManager.createQuery("select id_producto From Sale  where id_factura.id_factura=:factura")
                .setParameter("factura",id_factura+1).getResultList();
    }

    public List<Integer> getAmount(int id_factura){
        return  entityManager.createQuery("select cantidad from Sale where id_factura.id_factura=:factura")
                .setParameter("factura",id_factura+1).getResultList();
    }

}
