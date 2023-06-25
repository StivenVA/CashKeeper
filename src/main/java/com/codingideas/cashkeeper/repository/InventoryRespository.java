package com.codingideas.cashkeeper.repository;

import com.codingideas.cashkeeper.dto.TotalInventoryDTO;
import com.codingideas.cashkeeper.models.Inventory;
import com.codingideas.cashkeeper.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class InventoryRespository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Inventory> getLastInventory(){
        return  entityManager.createQuery("select i FROM (select max(fecha) as maxfecha from Inventory) f,Inventory i where f.maxfecha=i.fecha").getResultList();
    }

    public void mergeInventory(Inventory inventory){
        entityManager.persist(inventory);
    }

    public Inventory findInventory(int congelador, Product product){
        List<Inventory> inventory = entityManager.createQuery("FROM Inventory where congelador="+congelador+" and id_producto="+product).getResultList();
        return inventory.get(0);
    }

}
