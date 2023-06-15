package com.codingideas.cashkeeper.repository;

import com.codingideas.cashkeeper.models.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class SupplierRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List getSuppliersRepository(){
        return entityManager.createQuery("FROM Supplier ").getResultList();
    }

    public Supplier findSupplier(String id){
        return entityManager.find(Supplier.class,id);
    }

    public  void removeSupplier(Supplier supplier){
        entityManager.remove(supplier);
    }

    public void mergeSupplier(Supplier supplier){
        entityManager.merge(supplier);
    }
}
