package com.codingideas.cashkeeper.service;


import com.codingideas.cashkeeper.interfaces.IProductService;
import com.codingideas.cashkeeper.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Repository
public class ProductService implements IProductService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List getProduct(boolean auth) {

        //if (!auth) return null;
        return entityManager.createQuery("FROM Product ").getResultList();
    }

    @Override
    public String modifyProduct(Product productEdited, boolean auth) {

        if (!auth) return null;

        Product product = entityManager.find(Product.class,productEdited.getId());

        product.setDescripcion(productEdited.getDescripcion());
        product.setPrecio(productEdited.getPrecio());

        entityManager.merge(product);

        return "OK";
    }

    @Override
    public boolean addProduct(Product product, boolean auth) {

        if (!auth) return false;

        if(entityManager.find(Product.class,product.getId())!=null) return false;

        entityManager.merge(product);

        return true;
    }

}
