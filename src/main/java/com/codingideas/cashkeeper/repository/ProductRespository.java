package com.codingideas.cashkeeper.repository;

import com.codingideas.cashkeeper.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class ProductRespository {

    @PersistenceContext
    private final EntityManager entityManager;

    public void mergeProduct(Product product){
        entityManager.merge(product);
    }

    public void removeProduct(Product product){
        entityManager.remove(product);
    }

    public List<Product> getProducts(){
        return entityManager.createQuery("FROM Product").getResultList();
    }

    public Product findProduct(String id_Product){
        return entityManager.find(Product.class,id_Product);
    }
}
